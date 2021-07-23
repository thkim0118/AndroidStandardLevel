package com.terry.transaction.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.terry.common.LogT
import com.terry.common.base.BaseFragment
import com.terry.common.util.FirebaseDBKey
import com.terry.transaction.chatlist.ChatListItem
import com.terry.transaction.databinding.FragmentHomeBinding

/*
 * Created by Taehyung Kim on 2021-07-20
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var articleDB: DatabaseReference
    private lateinit var userDB: DatabaseReference
    private lateinit var articleAdapter: ArticleAdapter

    private val articleList = mutableListOf<ArticleModel>()

    private val listener = object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            // TODO: 2021-07-23 나의 아이템은 보이지 않고 다른 페이지에서 내가 등록한 아이템을 볼 수 있는 기능
            val articleModel = snapshot.getValue(ArticleModel::class.java)
            articleModel ?: return

            articleList.add(articleModel)
            articleAdapter.submitList(articleList)
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}

    }

    private val auth by lazy { Firebase.auth }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleDB = Firebase.database.reference.child(FirebaseDBKey.DB_ARTICLES)
        userDB = Firebase.database.reference.child(FirebaseDBKey.DB_USERS)

        articleAdapter = ArticleAdapter(onItemClicked = { articleModel ->
            auth.currentUser?.let { firebaseUser ->
                if (firebaseUser.uid != articleModel.sellerId) {
                    val chatRoom = ChatListItem(
                        buyerId = firebaseUser.uid,
                        sellerId = articleModel.sellerId,
                        itemTitle = articleModel.title,
                        key = System.currentTimeMillis()
                    )

                    userDB.child(firebaseUser.uid)
                        .child(FirebaseDBKey.CHILD_CHAT)
                        .push()
                        .setValue(chatRoom)

                    userDB.child(articleModel.sellerId)
                        .child(FirebaseDBKey.CHILD_CHAT)
                        .push()
                        .setValue(chatRoom)

                    Snackbar.make(view, "채팅방이 생성되었습니다. 채팅탭에서 확인해주세요.", Snackbar.LENGTH_SHORT).show()

                } else {
                    Snackbar.make(view, "내가 올린 아이템입니다.", Snackbar.LENGTH_SHORT).show()
                }
            } ?: run {
                Snackbar.make(view, "로그인 후 사용해주세요.", Snackbar.LENGTH_SHORT).show()
            }
        })

        articleList.clear()

        binding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.articleRecyclerView.adapter = articleAdapter

        binding.addFloatingButton.setOnClickListener {
            if (auth.currentUser != null) {
                LogT.d("displayName -> ${auth.currentUser?.displayName}")
                LogT.d("email -> ${auth.currentUser?.email}")
                val intent = Intent(requireContext(), AddArticleActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(view, "로그인 후 사용해주세요.", Snackbar.LENGTH_SHORT).show()
            }
        }

        articleDB.addChildEventListener(listener)
    }

    override fun onResume() {
        super.onResume()

        articleAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        articleDB.removeEventListener(listener)
    }

}