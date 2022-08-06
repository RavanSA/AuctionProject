package android.project.auction.domain.model.bids

data class Bids(
    val amount: Double,
    val created: String,
    val id: String,
    val itemId: String,
    val userId: String
)
