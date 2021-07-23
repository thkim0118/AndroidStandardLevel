package com.terry.tinder

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.terry.common.base.BaseActivity
import com.terry.common.util.FirebaseDBKey
import com.terry.tinder.databinding.ActivityMatchBinding

/*
 * Created by Taehyung Kim on 2021-07-19
 */
class MatchedUserActivity : BaseActivity<ActivityMatchBinding>(ActivityMatchBinding::inflate) {

    private val auth = FirebaseAuth.getInstance()
    private lateinit var userDB: DatabaseReference

    private val adapter = MatchedUserAdapter()
    private val cardItems = mutableListOf<CardItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userDB = Firebase.database.reference.child("Users")

        initMatchedUserRecyclerView()
        getMatchUsers()
    }

    private fun initMatchedUserRecyclerView() {
        val recyclerView = binding.matchedUserRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun getMatchUsers() {
        val matchedDB = userDB.child(getCurrentUserId()).child(FirebaseDBKey.LIKED_BY).child(FirebaseDBKey.MATCH)

        matchedDB.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                if (snapshot.key?.isNotEmpty() == true) {
                    getUserByKey(snapshot.key.orEmpty())
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    private fun getUserByKey(userId: String) {
        userDB.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cardItems.add(CardItem(userId, snapshot.child(FirebaseDBKey.NAME).value.toString()))
                adapter.submitList(cardItems)
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    private fun getCurrentUserId(): String {
        if (auth.currentUser == null) {
            Snackbar.make(binding.root, "로그인이 되어있지 않습니다.", Snackbar.LENGTH_SHORT).show()
            finish()
        }
        return auth.currentUser?.uid.orEmpty()
    }

}