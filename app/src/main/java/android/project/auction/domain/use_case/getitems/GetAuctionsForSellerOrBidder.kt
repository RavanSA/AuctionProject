package android.project.auction.domain.use_case.getitems

import android.content.SharedPreferences
import android.project.auction.common.Constants
import android.project.auction.data.local.entity.SellerOrBidder
import android.project.auction.domain.repository.AuctionRoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuctionsForSellerOrBidder @Inject constructor(
    private val repository: AuctionRoomRepository,
    private val preferences: SharedPreferences
) {

    fun invoke(sellerOrBidder: String): Flow<List<SellerOrBidder>> {
        val userId = preferences.getString(Constants.USER_ID, null) ?: Constants.UNAUTHORIZED_USER
        return repository.getSellerOrBidderAuctions(sellerOrBidder, userId)
    }

}