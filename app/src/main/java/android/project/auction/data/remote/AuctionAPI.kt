package android.project.auction.data.remote

import android.project.auction.data.remote.dto.bids.BidsHistoryDto
import android.project.auction.data.remote.dto.bids.PlaceBidRequest
import android.project.auction.data.remote.dto.categories.Categories
import android.project.auction.data.remote.dto.categories.subcategories.SubCategoriesDto
import android.project.auction.data.remote.dto.items.getitems.ItemDto
import android.project.auction.data.remote.dto.items.itemdetail.ItemDetailDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

    @GET("Categories")
    suspend fun getCategoriesWithSubCategories(): SubCategoriesDto

}