package android.project.auction.presentation.postitem

import android.project.auction.domain.model.category.Category
import android.project.auction.domain.model.category.SubCategories

data class PostItemState(
    val isCategoriesLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val categoriesError: String = "",
    val isSubCategoriesLoading: Boolean = false,
    val subCategories: List<SubCategories> = emptyList(),
    val subCategoriesError: String = ""
)