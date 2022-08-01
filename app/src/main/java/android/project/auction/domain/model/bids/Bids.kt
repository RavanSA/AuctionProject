package android.project.auction.domain.model.bids

data class Bids(
    val amount: Int,
    val createdBy: String,
    val id: String,
    val itemId: String,
    val userId: String
)
