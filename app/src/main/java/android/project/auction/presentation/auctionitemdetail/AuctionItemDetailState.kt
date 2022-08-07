package android.project.auction.presentation.auctionitemdetail

import android.project.auction.domain.model.bids.Bids
import android.project.auction.domain.model.item.ItemDetail

data class AuctionItemDetailState(
    val isItemDetailLoading: Boolean = false,
    val itemDetails: ItemDetail? = null,
    val error: String = "",
    val isBidHistoryLoading: Boolean = false,
    val bidHistory: List<Bids> = emptyList(),
    val bidError: String = "",
    val bidAmount: String = "",
    val itemID: String = "",
    val postingBidAmount: Boolean = false
)