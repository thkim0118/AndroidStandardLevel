package com.terry.tinder

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.terry.common.base.BaseActivity
import com.terry.tinder.databinding.ActivityLikeBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

/*
 * Created by Taehyung Kim on 2021-07-19
 */
class LikeActivity : BaseActivity<ActivityLikeBinding>(ActivityLikeBinding::inflate),
    CardStackListener {

    private val auth = FirebaseAuth.getInstance()
    private lateinit var userDB: DatabaseReference

    // TODO : CardStackView 를 커스터마이징하여 jcenter를 사용하지 않도록 변경하기
    private val adapter = CardItemAdapter()
    private val cardItem = mutableListOf<CardItem>()
    private val manager by lazy {
        CardStackLayoutManager(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userDB = Firebase.database.reference.child(DBKey.USERS)
        val currentUserDB = userDB.child(getCurrentUserId())
        currentUserDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.child(DBKey.NAME).value == null) {
                    showNameInputPopup()
                    return
                }

                getUnSelectedUsers()
            }

            override fun onCancelled(error: DatabaseError) {}

        })

        initCardStackView()
        initSignOutButton()
        initMatchedListButton()
    }

    private fun initCardStackView() {
        val cardStackView = binding.cardStackView

        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
    }

    private fun initSignOutButton() {
        val signOutButton = binding.signOutButton

        signOutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, TinderMainActivity::class.java))
            finish()
        }
    }

    private fun initMatchedListButton() {
        val matchedListButton = binding.matchListButton

        matchedListButton.setOnClickListener {
            startActivity(Intent(this, MatchedUserActivity::class.java))
        }
    }

    private fun getUnSelectedUsers() {
        userDB.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                if (snapshot.child(DBKey.USER_ID).value != getCurrentUserId()
                    && snapshot.child(DBKey.LIKED_BY).child(DBKey.LIKE).hasChild(getCurrentUserId()).not()
                    && snapshot.child(DBKey.LIKED_BY).child(DBKey.DISLIKE).hasChild(getCurrentUserId()).not()
                ) {
                    val userId = snapshot.child(DBKey.USER_ID).value.toString()
                    var name = "undecided"
                    if (snapshot.child(DBKey.NAME).value != null) {
                        name = snapshot.child(DBKey.NAME).value.toString()
                    }

                    cardItem.add(CardItem(userId, name))
                    adapter.submitList(cardItem)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                cardItem.find { it.userId == snapshot.key }?.let {
                    it.name = snapshot.child(DBKey.NAME).value.toString()
                }

                adapter.submitList(cardItem)
                adapter.notifyDataSetChanged()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {}

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(error: DatabaseError) {}

        })

    }

    private fun showNameInputPopup() {
        val editText = EditText(this)

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.write_name))
            .setView(editText)
            .setPositiveButton(getString(R.string.save)) { _, _ ->
                if (editText.text.isEmpty()) {
                    showNameInputPopup()
                } else {
                    saveUserName(editText.text.toString())
                }
            }
            .setCancelable(false)
            .show()
    }

    private fun saveUserName(name: String) {
        val userId = getCurrentUserId()
        val currentUserDB = userDB.child(userId)
        val user = mutableMapOf<String, Any>()
        user[DBKey.USER_ID] = userId
        user[DBKey.NAME] = name
        currentUserDB.updateChildren(user)

        getUnSelectedUsers()
    }


    private fun getCurrentUserId(): String {
        if (auth.currentUser == null) {
            Snackbar.make(binding.root, "로그인이 되어있지 않습니다.", Snackbar.LENGTH_SHORT).show()
            finish()
        }
        return auth.currentUser?.uid.orEmpty()
    }

    private fun like() {
        val card = cardItem[manager.topPosition - 1]
        cardItem.removeFirst()

        userDB.child(card.userId)
            .child(DBKey.LIKED_BY)
            .child(DBKey.LIKE)
            .child(getCurrentUserId())
            .setValue(true)

        saveMatchIfOtherUserLikedMe(card.userId)

        Snackbar.make(binding.root, "${card.name}님을 Like 하였습니다.", Snackbar.LENGTH_SHORT).show()
    }

    private fun disLike() {
        val card = cardItem[manager.topPosition - 1]
        cardItem.removeFirst()

        userDB.child(card.userId)
            .child(DBKey.LIKED_BY)
            .child(DBKey.DISLIKE)
            .child(getCurrentUserId())
            .setValue(true)

        Snackbar.make(binding.root, "${card.name}님을 disLike 하였습니다.", Snackbar.LENGTH_SHORT).show()
    }

    private fun saveMatchIfOtherUserLikedMe(otherUserId: String) {
        val otherUserDB =
            userDB.child(getCurrentUserId()).child(DBKey.LIKED_BY).child(DBKey.LIKE).child(otherUserId)
        otherUserDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value == true) {
                    userDB.child(getCurrentUserId())
                        .child(DBKey.LIKED_BY)
                        .child(DBKey.MATCH)
                        .child(otherUserId)
                        .setValue(true)

                    userDB.child(otherUserId)
                        .child(DBKey.LIKED_BY)
                        .child(DBKey.MATCH)
                        .child(getCurrentUserId())
                        .setValue(true)
                }
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardSwiped(direction: Direction?) {
        when (direction) {
            Direction.Right -> like()

            Direction.Left -> disLike()

            else -> {

            }
        }
    }

    override fun onCardRewound() {}

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardDisappeared(view: View?, position: Int) {}

}