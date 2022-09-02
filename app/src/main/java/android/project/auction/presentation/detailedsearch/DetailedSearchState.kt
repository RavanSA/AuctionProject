package android.project.auction.presentation.detailedsearch

import android.project.auction.data.local.entity.Items
import android.project.auction.domain.model.category.SubCategories
import android.project.auction.domain.model.util.ItemOrder
import android.project.auction.domain.model.util.OrderType

data class DetailedSearchState(
    val subCategories: List<SubCategories> = emptyList(),
    val isSubCategoriesLoading: Boolean = false,
    val subCategoryError: String = "",

    val filteredItemList: List<Items> = emptyList(),
    val errorItemList: String = "",
    val isLoading: Boolean = false,

    val itemOrder: ItemOrder = ItemOrder.Created(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,

    var categoryId: String = "",
    var subCategoryId: String = "",

    val searchQuery: String = "",

    val minStartingPrice: String = "",
    val maxStartingPrice: String = "",

    val fisrtCreateDate: String = "",
    val secondCreatedDate: String = ""

)