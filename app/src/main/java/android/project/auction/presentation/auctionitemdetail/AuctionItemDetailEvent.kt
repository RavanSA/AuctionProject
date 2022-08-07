package android.project.auction.presentation.auctionitemdetail

sealed class AuctionItemDetailEvent {
    data class OnItemsAddedFavorites(val itemId: String) : AuctionItemDetailEvent()
    object OnBidAmountPlaced : AuctionItemDetailEvent()
    data class BidAmountChanged(val amount: String, val itemId: String) : AuctionItemDetailEvent()
}