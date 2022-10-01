package android.project.auction.domain.repository

import android.project.auction.data.remote.dto.bids.BidsHistoryDto
import android.project.auction.data.remote.dto.bids.HighestBidDto
import android.project.auction.data.remote.dto.categories.Categories
import android.project.auction.data.remote.dto.categories.subcategories.SubCategoriesDto
import android.project.auction.data.remote.dto.items.createitem.CreateItemRequest
import android.project.auction.data.remote.dto.items.createitem.CreateItemResponse
import android.project.auction.data.remote.dto.items.createitem.CreatePictureItemId
import android.project.auction.data.remote.dto.items.getitems.ItemDto
import android.project.auction.data.remote.dto.items.getpictures.AddItemPictureRequest
import android.project.auction.data.remote.dto.items.getpictures.GetItemPicturesDto
import android.project.auction.data.remote.dto.items.itemdetail.ItemDetailDto
import android.project.auction.data.remote.dto.message.GetAllMessageByItemId
import android.project.auction.data.remote.dto.message.MessageRequest
import android.project.auction.data.remote.dto.userinfo.GetUserInfo
import android.project.auction.data.remote.dto.userinfo.UpdateUserInfoRequest
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

    suspend fun getItemPictures(id: String): GetItemPicturesDto

    suspend fun addItemMainPicture(addItemPictureRequest: AddItemPictureRequest): Response<Unit>

    suspend fun getUserInfoById(id: String): GetUserInfo

    suspend fun updateUserInfo(userInfoRequest: UpdateUserInfoRequest): Response<Unit>

    suspend fun sendMessageToUser(messageRequest: MessageRequest): Response<Unit>

    suspend fun getAllMessagesByItemId(itemId: String): GetAllMessageByItemId
}