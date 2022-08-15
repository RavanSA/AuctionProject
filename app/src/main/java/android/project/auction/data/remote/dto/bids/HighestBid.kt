package android.project.auction.data.remote.dto.bids

import android.project.auction.domain.model.bids.Bids
import android.project.auction.domain.model.bids.HighestBid

data class HighestBid(
    var amount: Double,
    val createdBy: Any,
    val id: String,
    val itemId: String,
    val userId: String
)

fun HighestBid.toHighestBid(): Bids {
    return Bids(
        amount = amount,
        created = createdBy as String,
        id = id,
        itemId = itemId,
        userId = userId
    )
}