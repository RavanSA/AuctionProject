package android.project.auction.domain.repository

import android.project.auction.data.remote.dto.bids.BidsHistoryDto
import android.project.auction.data.remote.dto.categories.Categories
import android.project.auction.data.remote.dto.items.getitems.ItemDto
import android.project.auction.data.remote.dto.items.itemdetail.ItemDetailDto

interface AuctionRepository {

    suspend fun getCategories(): Categories

    suspend fun getItems(): ItemDto

    suspend fun getItemDetailById(itemId: String): ItemDetailDto

    suspend fun getBidsHistoryByItemId(itemId: String): BidsHistoryDto
}