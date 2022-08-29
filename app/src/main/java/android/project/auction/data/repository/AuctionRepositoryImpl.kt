package android.project.auction.data.repository

import android.project.auction.data.remote.AuctionAPI
import android.project.auction.data.remote.dto.bids.BidsHistoryDto
import android.project.auction.data.remote.dto.bids.HighestBidDto
import android.project.auction.data.remote.dto.bids.PlaceBidRequest
import android.project.auction.data.remote.dto.categories.Categories
import android.project.auction.data.remote.dto.categories.subcategories.SubCategoriesDto
import android.project.auction.data.remote.dto.items.createitem.CreateItemRequest
import android.project.auction.data.remote.dto.items.createitem.CreateItemResponse
import android.project.auction.data.remote.dto.items.createitem.CreatePictureItemId
import android.project.auction.data.remote.dto.items.getitems.ItemDto
import android.project.auction.data.remote.dto.items.getpictures.AddItemPictureRequest
import android.project.auction.data.remote.dto.items.getpictures.GetItemPicturesDto
import android.project.auction.data.remote.dto.items.itemdetail.ItemDetailDto
import android.project.auction.domain.repository.AuctionRepository
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

        return api.placeBidAmount(
            PlaceBidRequest(
                amount = amount,
                itemId = itemId,
                userId = userId
            )
        )
    }

    override suspend fun addImageForItemToRemoteDatabase(
        createPictureForItem: CreatePictureItemId
    ): Response<Unit> {
        return api.addImageForItemToRemoteDatabase(createPictureForItem = createPictureForItem)
    }

    override suspend fun getCategoriesWithSubcategories(): SubCategoriesDto {
        return api.getCategoriesWithSubCategories()
    }

    override suspend fun createItem(createItemRequest: CreateItemRequest): CreateItemResponse {
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

    override suspend fun getItemPictures(id: String): GetItemPicturesDto {
        return api.getItemPicture(id = id)
    }

    override suspend fun addItemMainPicture(addItemPictureRequest: AddItemPictureRequest): Response<Unit> {
        return api.addImageMainPicture(addItemPictureRequest)
    }

}