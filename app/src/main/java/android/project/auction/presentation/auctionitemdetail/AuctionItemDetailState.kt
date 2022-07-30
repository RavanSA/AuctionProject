package android.project.auction.presentation.auctionitemdetail

import android.project.auction.domain.model.item.ItemDetail

data class AuctionItemDetailState(
    val isItemDetailLoading: Boolean = false,
    val itemDetails: ItemDetail? = null,
    val error: String = ""
)