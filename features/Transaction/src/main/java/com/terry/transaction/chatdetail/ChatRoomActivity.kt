package com.terry.transaction.chatdetail

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.terry.common.base.BaseActivity
import com.terry.common.util.FirebaseDBKey
import com.terry.transaction.databinding.ActivityChatRoomBinding

/*
 * Created by Taehyung Kim on 2021-07-23
 */
class ChatRoomActivity : BaseActivity<ActivityChatRoomBinding>(ActivityChatRoomBinding::inflate) {

    private val auth by lazy { Firebase.auth }

    private val chatList = mutableListOf<ChatItem>()
    private val adapter = ChatItemAdapter()
    private var chatDB: DatabaseReference? = null

    // TODO: 2021-07-23 채팅 닉네임, 나의 채팅 하이라이트, 채팅 UI 왼쪽, 오른쪽으로 변경
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val chatKey = intent.getLongExtra("chatKey", -1)

        chatDB = Firebase.database.reference.child(FirebaseDBKey.DB_CHATS).child("$chatKey")
        chatDB?.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatItem = snapshot.getValue(ChatItem::class.java)
                chatItem ?: return

                chatList.add(chatItem)
                adapter.submitList(chatList)
                adapter.notifyDataSetChanged()
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

        binding.chatRecyclerView.adapter = adapter
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.sendButton.setOnClickListener {
            auth.currentUser?.let { currentUser ->
                val chatItem = ChatItem(
                    senderId = currentUser.uid,
                    message = binding.messageEditText.text.toString()
                )

                chatDB?.push()?.setValue(chatItem)
            }

        }
    }

}