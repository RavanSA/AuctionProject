package android.project.auction.data.remote

import android.project.auction.data.remote.dto.categories.Categories
import android.project.auction.data.remote.dto.items.getitems.ItemDto
import android.project.auction.data.remote.dto.items.itemdetail.ItemDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AuctionAPI {

    @GET("Categories/CategoryList")
    suspend fun getCategories(): Categories

    @GET("Items")
    suspend fun getItems(): ItemDto

    @GET("Items/{itemId}")
    suspend fun getItemDetailById(@Path("itemId") itemId: String): ItemDetailDto

}