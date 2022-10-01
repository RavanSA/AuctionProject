package android.project.auction.domain.model.messages

data class Message(
    val message: String,
    val itemId: String,
    val sellerId: String,
    val bidderId: String
)
