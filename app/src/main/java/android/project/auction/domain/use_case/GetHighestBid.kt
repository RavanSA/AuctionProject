package android.project.auction.domain.use_case

import android.project.auction.common.Resource
import android.project.auction.domain.repository.AuctionRepository
import javax.inject.Inject

class GetHighestBid @Inject constructor(
    private val repository: AuctionRepository
) {
    operator fun invoke(): Flow<Resource<>> {

    }

}