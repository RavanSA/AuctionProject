package android.project.auction.data.remote.dto.bids

import android.project.auction.domain.model.bids.Bids

data class BidHistoryDataDto(
    val amount: Double,
    val created: String,
    val id: String,
    val itemId: String,
    val userId: String
)

fun BidHistoryDataDto.toBids(): Bids {
    return Bids(
        amount = amount,
        created = created,
        id = id,
        itemId = itemId,
        userId = userId
    )
}