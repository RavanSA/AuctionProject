package android.project.auction.presentation.postitem

import android.project.auction.common.Resource
import android.project.auction.domain.use_case.AuctionProjectUseCase
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostItemViewModel @Inject constructor(
    private val useCase: AuctionProjectUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PostItemState())
    val state: State<PostItemState> = _state

    val data = savedStateHandle

    init {
        getCategories()
        getSubCategories()
    }

    fun onEvent(event: PostItemEvent) {
        when (event) {
            is PostItemEvent.OnCategoryItemClicked -> {
                getSubCategoryByCategoryId(savedStateHandle = data)
            }
        }
    }

    private fun getSubCategories() {
        useCase.getSubCategories.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PostItemState(subCategories = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PostItemState(
                        subCategoriesError = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PostItemState(isSubCategoriesLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCategories() {
        useCase.getCategories.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PostItemState(categories = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PostItemState(
                        categoriesError = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PostItemState(isCategoriesLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSubCategoryByCategoryId(savedStateHandle: SavedStateHandle) {
        savedStateHandle.get<String>("categoryId")?.let { categoryId ->
            val allSubCategories = state.value.subCategories
//            for(categoryId in allSubCategories) {
//                if(categoryId.id ){
//
//                }
//            }

            allSubCategories.filter { subCategory ->
                subCategory.id == categoryId
            }
            Log.d("SUBCATEGORY", allSubCategories.toString())

        }
    }
}