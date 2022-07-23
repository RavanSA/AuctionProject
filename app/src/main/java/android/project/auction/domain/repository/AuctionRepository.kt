package android.project.auction.domain.repository

import android.project.auction.data.remote.dto.categories.Categories
import android.project.auction.data.remote.dto.items.ItemDto

interface AuctionRepository {

    suspend fun getCategories(): Categories

    suspend fun getItems(): ItemDto
}