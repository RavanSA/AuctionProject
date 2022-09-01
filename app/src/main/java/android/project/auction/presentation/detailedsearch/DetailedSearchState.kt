package android.project.auction.presentation.detailedsearch

import android.project.auction.domain.model.category.SubCategories

data class DetailedSearchState(
    val subCategories: List<SubCategories> = emptyList(),
    val isSubCategoriesLoading: Boolean = false,
    val subCategoryError: String = ""
)