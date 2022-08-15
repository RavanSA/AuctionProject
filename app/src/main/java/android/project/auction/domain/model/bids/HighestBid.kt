package android.project.auction.domain.model.bids

data class HighestBid(
    val amount: Double,
    val createdBy: Any,
    val id: String,
    val itemId: String,
    val userId: String
)