package android.project.auction.domain.use_case.getitems

import android.project.auction.data.local.entity.SellerOrBidder
import android.project.auction.domain.repository.AuctionRoomRepository
import android.util.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuctionsForSellerOrBidder @Inject constructor(
    private val repository: AuctionRoomRepository
) {

    fun invoke(sellerOrBidder: String): Flow<List<SellerOrBidder>> {
        Log.d("SELLERORBIDDER", sellerOrBidder)

        return repository.getSellerOrBidderAuctions(sellerOrBidder)
    }

}