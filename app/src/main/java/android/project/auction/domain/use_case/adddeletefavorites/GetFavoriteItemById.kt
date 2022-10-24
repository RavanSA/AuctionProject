package android.project.auction.domain.use_case.adddeletefavorites

import android.content.SharedPreferences
import android.project.auction.common.Constants
import android.project.auction.data.local.entity.Favorites
import android.project.auction.domain.repository.AuctionRoomRepository
import javax.inject.Inject

class GetFavoriteItemById @Inject constructor(
    private val repository: AuctionRoomRepository,
    private val preferences: SharedPreferences
) {

    suspend operator fun invoke(itemId: String): Favorites? {
        val userID = preferences.getString(Constants.USER_ID, null) ?: "Error"
        return repository.getFavoriteItemById(itemId, userID)
    }

    suspend fun isItemAddedToFavorites(id: String): Int? {
        return repository.isItemAddedToFavorites(id)
    }

}