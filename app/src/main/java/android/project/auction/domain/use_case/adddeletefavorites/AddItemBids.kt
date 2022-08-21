package android.project.auction.domain.use_case.adddeletefavorites

import android.project.auction.data.local.entity.Bids
import android.project.auction.domain.repository.AuctionRoomRepository
import javax.inject.Inject

class AddItemBids @Inject constructor(
    private val repository: AuctionRoomRepository
) {

    suspend operator fun invoke(bids: Bids) {
        repository.addItemBidLocal(bids)
    }

}