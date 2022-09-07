package android.project.auction.presentation.auctionitemdetail

import android.project.auction.data.local.entity.SellerOrBidder

sealed class AuctionItemDetailEvent {
    data class OnBidAmountPlaced(val itemId: String) : AuctionItemDetailEvent()
    object AddItemToFavorites : AuctionItemDetailEvent()
    data class BidAmountChanged(val amount: String, val itemId: String) : AuctionItemDetailEvent()
    data class SellerOrBidderEvent(val item: SellerOrBidder) : AuctionItemDetailEvent()
    object DeleteItem : AuctionItemDetailEvent()
    object RestoreItem : AuctionItemDetailEvent()
}