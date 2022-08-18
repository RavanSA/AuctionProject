package android.project.auction.domain.use_case.adddeletefavorites

import android.project.auction.data.local.entity.Favorites
import android.project.auction.domain.repository.AuctionRoomRepository
import javax.inject.Inject

class DeleteFavoriteItem @Inject constructor(
    private val repository: AuctionRoomRepository
) {

    suspend operator fun invoke(fav: Favorites) {
        repository.deleteItemFromFavoritesById(fav)
    }

}