package android.project.auction.domain.model.messages

data class Messages(
    val bidderId: String,
    val created: String,
    val id: String,
    val itemId: String,
    val message: String,
    val sellerId: String,
    val userId: Any?
)
