package android.project.auction.data.remote.dto.bids

data class PlaceBidRequest(
    val amount: Int,
    val itemId: String,
    val userId: String
)
