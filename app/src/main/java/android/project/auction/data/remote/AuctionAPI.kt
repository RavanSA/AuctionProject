package android.project.auction.data.remote

import android.project.auction.data.remote.dto.categories.Categories
import android.project.auction.data.remote.dto.items.ItemDto
import retrofit2.http.GET

interface AuctionAPI {

    @GET("Categories/CategoryList")
    suspend fun getCategories(): Categories

    @GET("Items")
    suspend fun getItems(): ItemDto

}