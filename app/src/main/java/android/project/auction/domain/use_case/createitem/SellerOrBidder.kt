package android.project.auction.domain.use_case.createitem

import android.project.auction.data.local.entity.SellerOrBidder
import android.project.auction.domain.repository.AuctionRoomRepository
import javax.inject.Inject

class SellerOrBidder @Inject constructor(
    private val repository: AuctionRoomRepository
) {

    suspend operator fun invoke(items: SellerOrBidder) {
        repository.setSellerOrBidder(items)
    }

}