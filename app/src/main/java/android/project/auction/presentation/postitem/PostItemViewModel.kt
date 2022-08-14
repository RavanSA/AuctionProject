package android.project.auction.presentation.postitem

import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.items.createitem.CreateItemRequest
import android.project.auction.domain.use_case.AuctionProjectUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostItemViewModel @Inject constructor(
    private val useCase: AuctionProjectUseCase
) : ViewModel() {
    //
    private val _state = mutableStateOf(PostItemState())
    val state: State<PostItemState> = _state

    var stateSubCategories by mutableStateOf(PostItemState())

    init {
        getSubCategories()
    }

    fun onEvent(event: PostItemEvent) {
        when (event) {
            is PostItemEvent.CategoryChanged -> {
                _state.value = state.value.copy(
                    categoriesInput = event.value
                )
            }
            is PostItemEvent.SubCategoryChange -> {
                _state.value = state.value.copy(
                    subCategoriesInput = event.value
                )
            }
            is PostItemEvent.TitleChanged -> {
                _state.value = state.value.copy(
                    title = event.value
                )
            }
            is PostItemEvent.DescriptionChanges -> {
                _state.value = state.value.copy(
                    description = event.value
                )
            }
            is PostItemEvent.StartingPriceChanged -> {
                _state.value = state.value.copy(
                    startingPrice = event.value
                )
            }
            is PostItemEvent.MinIncreaseChanged -> {
                _state.value = state.value.copy(
                    minIncrease = event.value
                )
            }
            is PostItemEvent.StartTimeChanged -> {
                _state.value = state.value.copy(
                    startTime = event.value
                )
            }
            is PostItemEvent.EndTimeChanged -> {
                _state.value = state.value.copy(
                    endTime = event.value
                )
            }
            is PostItemEvent.CreateItemClicked -> {
                createItem()
            }
        }
    }

    private fun getSubCategories() {
        useCase.getSubCategories.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    stateSubCategories = stateSubCategories.copy(
                        subCategories = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    stateSubCategories = stateSubCategories.copy(
                        subCategoriesError = result.message ?: "ERROR"
                    )
                }
                is Resource.Loading -> {
                    stateSubCategories = stateSubCategories.copy(
                        isSubCategoriesLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

//    private fun createItem() {
//        Log.d("CREAITEMCALLED","TEST")
//        viewModelScope.launch {
//            Log.d("RESPONSEUSECASE5", "TEST")
//            useCase.createItem.invoke(
//            createItemRequest = CreateItemRequest(
//                categoryId = state.value.categoriesInput,
//                subCategoryId = state.value.subCategoriesInput,
//                title = state.value.title,
//                description = state.value.description,
//                startingPrice = state.value.startingPrice.toDouble(),
//                minIncrease = state.value.minIncrease.toDouble(),
//                startTime = state.value.startTime,
//                endTime = state.value.endTime,
//                userId = ""
//            )
//        ).onEach { result ->
//            when (result) {
//                        is Resource.Loading -> {
//                    Log.d("LOADING","CREATEITEM")
//
//                    _state.value = state.value.copy(
//                        creatingItem = true
//                    )
//                }
//
//                is Resource.Error -> {
//                    Log.d("ERROR","CREATEITEM")
//
//                    _state.value = state.value.copy(
//                        creatingItemError = result.message ?: "ERROR OCCURED"
//                    )
//                }
//
//                is Resource.Success -> {
//                    Log.d("SUCCESS","CREATEITEM")
//
//                    createItemFunc()
//                    _state.value = result.data?.let {
//                        state.value.copy(
//                            itemCreated = it
//                        )
//                    } ?: PostItemState()
//                }
//
//            }
//        }
//      }
//    }

    private fun createItem() {
        viewModelScope.launch {

            _state.value = state.value.copy(
                creatingItem = true
            )

            useCase.createItem.invoke(
                createItemRequest = CreateItemRequest(
                    categoryId = state.value.categoriesInput,
                    subCategoryId = state.value.subCategoriesInput,
                    title = state.value.title,
                    description = state.value.description,
                    startingPrice = state.value.startingPrice.toDouble(),
                    minIncrease = state.value.minIncrease.toDouble(),
                    startTime = state.value.startTime,
                    endTime = state.value.endTime,
                    userId = ""
                )
            )

            _state.value = state.value.copy(
                creatingItem = false
            )

        }
    }

}