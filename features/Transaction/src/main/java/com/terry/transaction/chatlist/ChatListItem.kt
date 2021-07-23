package com.terry.transaction.chatlist

/*
 * Created by Taehyung Kim on 2021-07-22
 */
data class ChatListItem(
    val buyerId: String,
    val sellerId: String,
    val itemTitle: String,
    val key: Long
) {
    constructor() : this("", "", "", 0)
}
