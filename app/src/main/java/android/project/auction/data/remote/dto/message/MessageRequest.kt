package android.project.auction.data.remote.dto.message

data class MessageRequest(
    val message: String,
    val itemId: String,
    val sellerId: String,
    val bidderId: String,
    val messageOwner: String
)
