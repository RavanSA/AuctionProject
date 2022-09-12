package android.project.auction.presentation.userprofile

import android.project.auction.data.local.entity.SellerOrBidder
import android.project.auction.domain.model.userinfo.UserInfo

data class UserProfileState(
    val auctionList: List<SellerOrBidder> = emptyList(),
    val userInfo: UserInfo? = null,
    val userInfoLoading: Boolean = false,
    val userInfoError: String = ""
)