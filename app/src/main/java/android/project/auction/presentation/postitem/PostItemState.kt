package android.project.auction.presentation.postitem

import android.net.Uri
import android.project.auction.data.remote.dto.items.createitem.CreateItemResponse
import android.project.auction.data.remote.dto.items.createitem.Data
import android.project.auction.domain.model.category.SubCategories

data class PostItemState(
    val isCategoriesLoading: Boolean = false,
    val categories: List<SubCategories> = emptyList(),
    val categoriesError: String = "",

    val isSubCategoriesLoading: Boolean = false,
    val subCategories: List<SubCategories> = emptyList(),
    val subCategoriesError: String = "",

    var categoriesInput: String = "",
    var subCategoriesInput: String = "",
    var title: String = "",
    var description: String = "",
    var startingPrice: String = "",
    var minIncrease: String = "",
    var startTime: String = "",
    var endTime: String = "",

    val creatingItem: Boolean = false,
    val creatingItemError: String = "",
    val itemCreated: CreateItemResponse = CreateItemResponse(Data(""), ""),

    var imagesList: List<Uri> = emptyList()
)