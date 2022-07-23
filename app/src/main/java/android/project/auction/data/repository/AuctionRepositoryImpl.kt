package android.project.auction.data.repository

import android.project.auction.data.remote.AuctionAPI
import android.project.auction.data.remote.dto.categories.Categories
import android.project.auction.data.remote.dto.items.ItemDto
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

}