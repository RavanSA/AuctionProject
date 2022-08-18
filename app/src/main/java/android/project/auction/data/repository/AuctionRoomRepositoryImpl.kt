package android.project.auction.data.repository

import android.project.auction.data.local.dao.BidsDao
import android.project.auction.data.local.dao.FavoritesDao
import android.project.auction.data.local.entity.Bids
import android.project.auction.data.local.entity.Favorites
import android.project.auction.domain.repository.AuctionRoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuctionRoomRepositoryImpl @Inject constructor(
    private val favDao: FavoritesDao,
    private val bidDao: BidsDao
) : AuctionRoomRepository {


    override suspend fun addItemsToFavorites(fav: Favorites) {
        return favDao.addItemToFavorites(fav)
    }

    override suspend fun deleteItemFromFavoritesById(fav: Favorites) {
        return favDao.deleteItem(fav)
    }

    override fun getFavoriteItems(): Flow<List<Favorites>> {
        return favDao.getFavoriteItems()
    }

    override suspend fun getFavoriteItemById(itemId: String): Favorites? {
        return favDao.getItemFromFavoritesById(itemId)
    }

    override suspend fun addItemBidLocal(bid: Bids) {
        return bidDao.addItemBidLocal(bid)
    }

    override suspend fun deleteItemBidLocal(bid: Bids) {
        return bidDao.deleteItemBidLocal(bid)
    }

    override suspend fun getHighestBidLocal(id: String): Bids {
        return bidDao.getHighestBidLocal(id)
    }
}