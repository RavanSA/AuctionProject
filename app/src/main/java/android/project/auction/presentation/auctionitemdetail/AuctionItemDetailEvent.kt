package android.project.auction.presentation.auctionitemdetail

sealed class AuctionItemDetailEvent {
    object OnBidAmountPlaced : AuctionItemDetailEvent()
    object AddItemToFavorites : AuctionItemDetailEvent()
    data class BidAmountChanged(val amount: String, val itemId: String) : AuctionItemDetailEvent()
    object DeleteItem : AuctionItemDetailEvent()
    object RestoreItem : AuctionItemDetailEvent()
}