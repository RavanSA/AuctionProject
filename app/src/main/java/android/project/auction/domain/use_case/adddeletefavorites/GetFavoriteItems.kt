package android.project.auction.domain.use_case.adddeletefavorites

import android.content.SharedPreferences
import android.project.auction.common.Constants
import android.project.auction.data.local.entity.Favorites
import android.project.auction.domain.repository.AuctionRoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteItems @Inject constructor(
    private val repository: AuctionRoomRepository,
    private val preferences: SharedPreferences
) {

    operator fun invoke(): Flow<List<Favorites>> {
        val userID = preferences.getString(Constants.USER_ID, null) ?: "Error"
        return repository.getFavoriteItems(userID)
    }

}