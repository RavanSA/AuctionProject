package android.project.auction.data.repository

import android.project.auction.data.remote.AuctionAPI
import android.project.auction.data.remote.dto.bids.BidsHistoryDto
import android.project.auction.data.remote.dto.bids.HighestBidDto
import android.project.auction.data.remote.dto.bids.PlaceBidRequest
import android.project.auction.data.remote.dto.categories.Categories
import android.project.auction.data.remote.dto.categories.subcategories.SubCategoriesDto
import android.project.auction.data.remote.dto.items.createitem.CreateItemRequest
import android.project.auction.data.remote.dto.items.createitem.CreateItemResponse
import android.project.auction.data.remote.dto.items.getitems.ItemDto
import android.project.auction.data.remote.dto.items.itemdetail.ItemDetailDto
import android.project.auction.domain.repository.AuctionRepository
import android.util.Log
import retrofit2.Response
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

    override suspend fun placeBidAmount(
        amount: Int,
        itemId: String,
        userId: String
    ): Response<Unit> {
        Log.d("RESPONSEBIDTEST2", amount.toString())
        Log.d("RESPONSEITEMID", itemId)
        Log.d("RESPONSEUSERID", userId)

        return api.placeBidAmount(
            PlaceBidRequest(
                amount = amount,
                itemId = itemId,
                userId = userId
            )
        )
    }

    override suspend fun getCategoriesWithSubcategories(): SubCategoriesDto {
        return api.getCategoriesWithSubCategories()
    }

    override suspend fun createItem(createItemRequest: CreateItemRequest): CreateItemResponse {
        Log.d("CREATEREPOREQUES", createItemRequest.toString())
        return api.createItem(
            createItemRequest.title,
            createItemRequest.description,
            createItemRequest.startingPrice,
            createItemRequest.minIncrease,
            createItemRequest.startTime,
            createItemRequest.endTime,
            createItemRequest.subCategoryId,
            createItemRequest.categoryId,
            createItemRequest.userId
        )
    }

    override suspend fun getHighestBidByItemId(itemId: String): HighestBidDto {
        return api.getHighestByItemId(
            itemId = itemId
        )
    }


}