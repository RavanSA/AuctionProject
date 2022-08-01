package android.project.auction.data.repository

import android.project.auction.data.remote.AuctionAPI
import android.project.auction.data.remote.dto.bids.BidsHistoryDto
import android.project.auction.data.remote.dto.categories.Categories
import android.project.auction.data.remote.dto.items.getitems.ItemDto
import android.project.auction.data.remote.dto.items.itemdetail.ItemDetailDto
import android.project.auction.domain.repository.AuctionRepository
import javax.inject.Inject

class AuctionRepositoryImpl @Inject constructor(
    private val api: AuctionAPI
) : AuctionRepository {


    override suspend fun getCategories(): Categories {
        return api.getCategories()
    }

    override suspend fun getItems(): ItemDto {
        return api.getItems()
    }

    override suspend fun getItemDetailById(itemId: String): ItemDetailDto {
        return api.getItemDetailById(itemId = itemId)
    }

    override suspend fun getBidsHistoryByItemId(itemId: String): BidsHistoryDto {
        return api.getBidsHistoryByItemId(itemId)
    }

}