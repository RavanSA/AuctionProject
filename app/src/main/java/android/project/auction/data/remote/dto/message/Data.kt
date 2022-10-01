package android.project.auction.data.remote.dto.message

import android.project.auction.domain.model.messages.Messages

data class MessagesDto(
    val bidderId: String,
    val created: String,
    val id: String,
    val itemId: String,
    val message: String,
    val sellerId: String,
    val userId: Any?
)

fun MessagesDto.toMessages(): Messages {
    return Messages(
        bidderId = bidderId,
        created = created,
        id = id,
        itemId = itemId,
        message = message,
        sellerId = sellerId,
        userId = userId
    )
}