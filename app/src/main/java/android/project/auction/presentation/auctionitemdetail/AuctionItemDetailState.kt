package android.project.auction.presentation.auctionitemdetail

import android.project.auction.data.local.entity.Favorites
import android.project.auction.data.remote.dto.bids.HighestBid
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
    val postingBidAmount: Boolean = false,

    val highestBid: HighestBid? = null,
    val highestBidLoading: Boolean = false,
    val highestError: String = "",

    val userId: String = "",

    val itemAddedFavorite: Favorites? = null
)