package android.project.auction.data.repository

import android.project.auction.data.local.dao.BidsDao
import android.project.auction.data.local.dao.FavoritesDao
import android.project.auction.data.local.dao.ItemsDao
import android.project.auction.data.local.dao.SellerOrBidderDao
import android.project.auction.data.local.entity.Bids
import android.project.auction.data.local.entity.Favorites
import android.project.auction.data.local.entity.Items
import android.project.auction.data.local.entity.SellerOrBidder
import android.project.auction.domain.repository.AuctionRoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuctionRoomRepositoryImpl @Inject constructor(
    private val favDao: FavoritesDao,
    private val bidDao: BidsDao,
    private val itemDao: ItemsDao,
    private val sellerOrBidderDao: SellerOrBidderDao
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

    override suspend fun isItemAddedToFavorites(id: String): Int? {
        return favDao.isItemAddedtoFavorites(id)
    }

    override fun getItemWithFilteredCategories(
        subCategoryId: String,
        categoryId: String,
        searchQuery: String,
        firstRange: String,
        secondRange: String,
        firstDate: String,
        secondDate: String
    ): Flow<List<Items>> {
        return itemDao.getItemWithFilteredCategories(
            subCategoryId = subCategoryId,
            categoryId = categoryId,
            searchQuery = searchQuery,
            firstRange = firstRange,
            secondRange = secondRange,
            firstDate = firstDate,
            secondDate = secondDate
        )
    }

    override suspend fun setSellerOrBidder(items: SellerOrBidder) {
        return sellerOrBidderDao.setSellerBidderInfo(
            items = items
        )
    }

    override fun getSellerOrBidderAuctions(sellerOrBidder: String): Flow<List<SellerOrBidder>> {
        return sellerOrBidderDao.getSellerOrBidderAuctions(sellerOrBidder)
    }

}