package android.project.auction.presentation.userprofile

sealed class UserProfileEvent {
    data class OnTabChanged(val sellerOrBidder: String) : UserProfileEvent()
}