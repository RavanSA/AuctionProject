package android.project.auction.domain.repository

import android.project.auction.data.remote.dto.bids.BidsHistoryDto
import android.project.auction.data.remote.dto.bids.HighestBidDto
import android.project.auction.data.remote.dto.categories.Categories
import android.project.auction.data.remote.dto.categories.subcategories.SubCategoriesDto
import android.project.auction.data.remote.dto.items.createitem.CreateItemRequest
import android.project.auction.data.remote.dto.items.createitem.CreateItemResponse
import android.project.auction.data.remote.dto.items.createitem.CreatePictureItemId
import android.project.auction.data.remote.dto.items.getitems.ItemDto
import android.project.auction.data.remote.dto.items.itemdetail.ItemDetailDto
import retrofit2.Response

interface AuctionRepository {

    suspend fun getCategories(): Categories

    suspend fun getItems(): ItemDto

    suspend fun getItemDetailById(itemId: String): ItemDetailDto

    suspend fun getBidsHistoryByItemId(itemId: String): BidsHistoryDto

    suspend fun placeBidAmount(amount: Int, itemId: String, userId: String): Response<Unit>

    suspend fun addImageForItemToRemoteDatabase(createPictureForItem: CreatePictureItemId): Response<Unit>

    suspend fun getCategoriesWithSubcategories(): SubCategoriesDto

    suspend fun createItem(createItemRequest: CreateItemRequest): CreateItemResponse

    suspend fun getHighestBidByItemId(itemId: String): HighestBidDto

}