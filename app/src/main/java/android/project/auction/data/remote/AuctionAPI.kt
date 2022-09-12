package android.project.auction.data.remote

import android.project.auction.data.remote.dto.bids.BidsHistoryDto
import android.project.auction.data.remote.dto.bids.HighestBidDto
import android.project.auction.data.remote.dto.bids.PlaceBidRequest
import android.project.auction.data.remote.dto.categories.Categories
import android.project.auction.data.remote.dto.categories.subcategories.SubCategoriesDto
import android.project.auction.data.remote.dto.items.createitem.CreateItemResponse
import android.project.auction.data.remote.dto.items.createitem.CreatePictureItemId
import android.project.auction.data.remote.dto.items.getitems.ItemDto
import android.project.auction.data.remote.dto.items.getpictures.AddItemPictureRequest
import android.project.auction.data.remote.dto.items.getpictures.GetItemPicturesDto
import android.project.auction.data.remote.dto.items.itemdetail.ItemDetailDto
import android.project.auction.data.remote.dto.userinfo.GetUserInfo
import retrofit2.Response
import retrofit2.http.*

interface AuctionAPI {

    @GET("Categories/CategoryList")
    suspend fun getCategories(): Categories

    @GET("Items")
    suspend fun getItems(): ItemDto

    @GET("Items/{itemId}")
    suspend fun getItemDetailById(@Path("itemId") itemId: String): ItemDetailDto

    @GET("Bids/bidhistory/{itemId}")
    suspend fun getBidsHistoryByItemId(@Path("itemId") itemId: String): BidsHistoryDto

    @POST("Bids")
    suspend fun placeBidAmount(
        @Body bidAmount: PlaceBidRequest
    ): Response<Unit>

    @POST("Pictures")
    suspend fun addImageForItemToRemoteDatabase(
        @Body createPictureForItem: CreatePictureItemId
    ): Response<Unit>

    @GET("Categories")
    suspend fun getCategoriesWithSubCategories(): SubCategoriesDto


    @FormUrlEncoded
    @POST("Items")
    suspend fun createItem(
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("startingPrice") startingPrice: Double,
        @Field("minIncrease") minIncrease: Double,
        @Field("startTime") startTime: String,
        @Field("endTime") endTime: String,
        @Field("subCategoryId") subCategoryId: String,
        @Field("categoryId") categoryId: String,
        @Field("userId") userId: String
    ): CreateItemResponse

    @GET("Bids/getHighestBid/{itemId}")
    suspend fun getHighestByItemId(@Path("itemId") itemId: String): HighestBidDto

    @GET("Pictures/{id}")
    suspend fun getItemPicture(@Path("id") id: String): GetItemPicturesDto

    @PUT("Items")
    suspend fun addImageMainPicture(@Body addItemPictureRequest: AddItemPictureRequest): Response<Unit>

    @GET("Identity/{id}")
    suspend fun getUserInfoById(@Path("id") id: String): GetUserInfo

}