package android.project.auction.domain.use_case.adddeletefavorites

import android.project.auction.data.local.entity.Favorites
import android.project.auction.domain.repository.AuctionRoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteItems @Inject constructor(
    private val repository: AuctionRoomRepository
) {

    operator fun invoke(): Flow<List<Favorites>> {
        return repository.getFavoriteItems()
    }

}