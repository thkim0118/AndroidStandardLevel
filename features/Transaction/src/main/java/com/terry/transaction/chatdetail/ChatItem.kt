package com.terry.transaction.chatdetail

/*
 * Created by Taehyung Kim on 2021-07-23
 */
data class ChatItem(
    val senderId: String,
    val message: String
) {
    constructor() : this("", "")
}
