package android.project.auction.presentation.auctionlist

sealed class AuctionListEvent {
    object Refresh : AuctionListEvent()
    data class OnSearchQueryChange(val query: String) : AuctionListEvent()
}