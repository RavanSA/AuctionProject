package android.project.auction.presentation.chat

import android.project.auction.domain.model.item.ItemDetail
import android.project.auction.domain.model.messages.Messages

data class ChatState(
    val isMessagesLoading: Boolean = false,
    val messages: List<Messages> = emptyList(),
    val messageError: String = "",

    var message: String = "",
    var itemDetail: ItemDetail? = null,
    var userId: String = ""
)