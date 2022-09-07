package android.project.auction.domain.repository

import android.project.auction.data.local.entity.Bids
import android.project.auction.data.local.entity.Favorites
import android.project.auction.data.local.entity.Items
import android.project.auction.data.local.entity.SellerOrBidder
import kotlinx.coroutines.flow.Flow

interface AuctionRoomRepository {

    suspend fun addItemsToFavorites(fav: Favorites)

    suspend fun deleteItemFromFavoritesById(fav: Favorites)

    fun getFavoriteItems(): Flow<List<Favorites>>

    suspend fun getFavoriteItemById(itemId: String): Favorites?

    suspend fun addItemBidLocal(bid: Bids)

    suspend fun deleteItemBidLocal(bid: Bids)

    suspend fun getHighestBidLocal(id: String): Bids

    suspend fun isItemAddedToFavorites(id: String): Int?

    fun getItemWithFilteredCategories(
        subCategoryId: String,
        categoryId: String,
        searchQuery: String,
        firstRange: String,
        secondRange: String,
        firstDate: String,
        secondDate: String
    ): Flow<List<Items>>

    suspend fun setSellerOrBidder(items: SellerOrBidder)

}