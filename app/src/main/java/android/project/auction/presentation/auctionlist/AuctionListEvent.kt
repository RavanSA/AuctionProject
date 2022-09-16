package android.project.auction.presentation.auctionlist

sealed class AuctionListEvent {
    object Refresh : AuctionListEvent()
    object OnMoreItemClicked : AuctionListEvent()
    data class OnSearchQueryChange(val query: String) : AuctionListEvent()
}