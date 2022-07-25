package android.project.auction.presentation.auctionlist

import android.project.auction.domain.model.category.Category
import android.project.auction.domain.model.item.Item

data class AuctionListState(
    val isCategoriesLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val errorCategories: String = "",
    val isItemLoading: Boolean = true,
    val item: List<Item> = emptyList(),
    val errorItem: String = "",
    val isItemRefreshing: Boolean = false,
    val searchQuery: String = ""
)