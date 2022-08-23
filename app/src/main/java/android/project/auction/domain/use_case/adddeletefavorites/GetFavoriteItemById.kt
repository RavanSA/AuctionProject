package android.project.auction.domain.use_case.adddeletefavorites

import android.project.auction.data.local.entity.Favorites
import android.project.auction.domain.repository.AuctionRoomRepository
import javax.inject.Inject

class GetFavoriteItemById @Inject constructor(
    private val repository: AuctionRoomRepository
) {

    suspend operator fun invoke(itemId: String): Favorites? {
        return repository.getFavoriteItemById(itemId)
    }

    suspend fun isItemAddedToFavorites(id: String): Int? {

        return repository.isItemAddedToFavorites(id)
    }

}