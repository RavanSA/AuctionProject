package android.project.auction.domain.use_case.adddeletefavorites

import android.project.auction.data.local.entity.Favorites
import android.project.auction.domain.model.exceptions.InvalidFavoriteItemException
import android.project.auction.domain.repository.AuctionRoomRepository
import javax.inject.Inject

class AddFavoriteItem @Inject constructor(
    private val repository: AuctionRoomRepository
) {

    @Throws(InvalidFavoriteItemException::class)
    suspend operator fun invoke(fav: Favorites) {
        if (fav.title.isBlank()) {
            throw InvalidFavoriteItemException("The title can not be empty")
        }

        if (fav.description.isBlank()) {
            throw InvalidFavoriteItemException("The description can not be empty")
        }

        repository.addItemsToFavorites(fav)
    }

}