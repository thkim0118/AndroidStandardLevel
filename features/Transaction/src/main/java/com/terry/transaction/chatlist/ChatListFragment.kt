package com.terry.transaction.chatlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.terry.common.base.BaseFragment
import com.terry.common.util.FirebaseDBKey
import com.terry.transaction.chatdetail.ChatRoomActivity
import com.terry.transaction.databinding.FragmentChatlistBinding

/*
 * Created by Taehyung Kim on 2021-07-20
 */
class ChatListFragment : BaseFragment<FragmentChatlistBinding>(FragmentChatlistBinding::inflate) {

    private lateinit var chatListAdapter: ChatListAdapter
    private val chatRoomList = mutableListOf<ChatListItem>()

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatListAdapter = ChatListAdapter(onItemClicked = { chatRoom ->

            // TODO: 2021-07-23 Key 값을 변경하여 중복된 채팅방이 생성되지 않도록 수정
            // TODO: 2021-07-23 상품의 정보를 보여주는 창을 확인한 뒤 채팅방을 생성할 수 있는 flow
            context?.let {
                val intent = Intent(it, ChatRoomActivity::class.java)
                intent.putExtra("chatKey", chatRoom.key)
                startActivity(intent)
            }
        })

        chatRoomList.clear()

        binding.chatListRecyclerView.adapter = chatListAdapter
        binding.chatListRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        if (auth.currentUser == null) return

        auth.currentUser?.let { currentUser ->
            val chatDB =
                Firebase.database.reference.child(FirebaseDBKey.DB_USERS).child(currentUser.uid)
                    .child(FirebaseDBKey.CHILD_CHAT)

            // TODO: 2021-07-23 채팅방 목록을 사진 등 여러 정보를 함께 보여주는 기능
            chatDB.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        val model = it.getValue(ChatListItem::class.java)
                        model ?: return

                        chatRoomList.add(model)
                    }

                    chatListAdapter.submitList(chatRoomList)
                    chatListAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }

    override fun onResume() {
        super.onResume()

        chatListAdapter.notifyDataSetChanged()
    }

}