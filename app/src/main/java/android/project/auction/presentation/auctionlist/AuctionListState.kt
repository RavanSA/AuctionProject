package android.project.auction.presentation.auctionlist

import android.project.auction.domain.model.category.Category
import android.project.auction.domain.model.item.Item
import android.project.auction.domain.model.userinfo.UserInfo

data class AuctionListState(
    val isCategoriesLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val errorCategories: String = "",
    val isItemLoading: Boolean = true,
    val item: List<Item> = emptyList(),
    val errorItem: String = "",
    val isItemRefreshing: Boolean = false,
    val searchQuery: String = "",
    val userInfo: UserInfo? = null,
    val userInfoError: String = "",
    val loadingUserInfo: Boolean = false,
    val loading: Boolean = false
)