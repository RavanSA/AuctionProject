package android.project.auction.presentation.detailedsearch

import android.project.auction.common.Resource
import android.project.auction.domain.model.util.ItemOrder
import android.project.auction.domain.model.util.OrderType
import android.project.auction.domain.use_case.usecases.AuctionProjectUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailedSearchViewModel @Inject constructor(
    private val useCase: AuctionProjectUseCase
) : ViewModel() {

    private val _state = mutableStateOf(DetailedSearchState())
    val state: State<DetailedSearchState> = _state

    var stateFilteredItemList by mutableStateOf(DetailedSearchState())

    private var searchJob: Job? = null


    init {
        getSubCategories()
        getItemWithFilteredCategories(
            categoryId = state.value.categoryId,
            subCategoryId = state.value.subCategoryId,
            itemOrder = ItemOrder.Created(OrderType.Descending)
        )
    }

    fun onEvent(event: DetailedSearchEvent) {
        when (event) {
            is DetailedSearchEvent.Order -> {
                if (state.value.itemOrder::class == event.itemOrder::class
                    && state.value.itemOrder.orderType == event.itemOrder.orderType
                ) {
                    return
                }
                getItemWithFilteredCategories(
                    categoryId = state.value.categoryId,
                    subCategoryId = state.value.subCategoryId,
                    itemOrder = event.itemOrder
                )
            }
            is DetailedSearchEvent.OnSubCategoryItemClicked -> {
                getItemWithFilteredCategories(
                    categoryId = event.categoryId,
                    subCategoryId = event.subCubCategoryId,
                    itemOrder = ItemOrder.Created(OrderType.Descending)
                )

            }
            is DetailedSearchEvent.ToggleOrderSection -> {

                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            is DetailedSearchEvent.OnSearchQueryChanged -> {
                _state.value = state.value.copy(
                    searchQuery = event.value
                )

                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(400L)
                    getItemWithFilteredCategories(
                        categoryId = state.value.categoryId,
                        subCategoryId = state.value.subCategoryId,
                        itemOrder = ItemOrder.Created(OrderType.Descending)
                    )
                }
            }
            is DetailedSearchEvent.OnFilterMinStartingPriceChange -> {
                _state.value = state.value.copy(
                    minStartingPrice = event.minPrice
                )
            }
            is DetailedSearchEvent.OnFilterMaxStartPriceChange -> {
                _state.value = state.value.copy(
                    maxStartingPrice = event.value
                )
            }
            is DetailedSearchEvent.OnFirstCreatedDateChanged -> {
                _state.value = state.value.copy(
                    fisrtCreateDate = event.firstDate
                )
            }
            is DetailedSearchEvent.OnSecondCreateDateChanged -> {
                _state.value = state.value.copy(
                    secondCreatedDate = event.secondDate
                )
            }
            is DetailedSearchEvent.FilterButtonClicked -> {
                getItemWithFilteredCategories(
                    categoryId = state.value.categoryId,
                    subCategoryId = state.value.subCategoryId,
                    itemOrder = state.value.itemOrder,
                    firstRange = state.value.minStartingPrice,
                    secondRange = state.value.maxStartingPrice,
                    firstDate = state.value.fisrtCreateDate,
                    secondDate = state.value.secondCreatedDate
                )
            }

        }
    }

    private fun getSubCategories() {
        useCase.getSubCategories.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        subCategories = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        subCategoryError = result.message ?: "ERROR"
                    )
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isSubCategoriesLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getItemWithFilteredCategories(
        categoryId: String,
        subCategoryId: String,
        searchQuery: String = state.value.searchQuery,
        itemOrder: ItemOrder,
        firstRange: String = "0",
        secondRange: String = "10000000000000000000",
        firstDate: String = "",
        secondDate: String = ""
    ) {
        useCase.getItemWithFilterCategories.invoke(
            categoryId = categoryId,
            subCategoryId = subCategoryId,
            firstRange = firstRange,
            secondRange = secondRange,
            firstDate = firstDate,
            secondDate = secondDate,
            searchQuery = searchQuery,
            itemOrder = itemOrder
        ).onEach { item ->
            _state.value = state.value.copy(
                filteredItemList = item,
                itemOrder = itemOrder
            )

        }.launchIn(viewModelScope)

    }

}