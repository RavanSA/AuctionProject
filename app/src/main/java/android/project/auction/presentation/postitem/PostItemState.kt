package android.project.auction.presentation.postitem

import android.project.auction.domain.model.category.Category

data class PostItemState(
    val isCategoriesLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val categoriesError: String = ""
)