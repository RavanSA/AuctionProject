package android.project.auction.presentation.detailedsearch

import android.project.auction.domain.model.util.ItemOrder

sealed class DetailedSearchEvent {
    data class Order(val itemOrder: ItemOrder) : DetailedSearchEvent()
    data class OnSearchQueryChanged(val value: String) : DetailedSearchEvent()
    data class OnFilterMinStartingPriceChange(val minPrice: String) : DetailedSearchEvent()
    data class OnFilterMaxStartPriceChange(val value: String) : DetailedSearchEvent()
    data class OnFirstCreatedDateChanged(val firstDate: String) : DetailedSearchEvent()
    data class OnSecondCreateDateChanged(val secondDate: String) : DetailedSearchEvent()
    object FilterButtonClicked : DetailedSearchEvent()
    object ToggleOrderSection : DetailedSearchEvent()
    data class OnSubCategoryItemClicked(val categoryId: String, val subCubCategoryId: String) :
        DetailedSearchEvent()
}
