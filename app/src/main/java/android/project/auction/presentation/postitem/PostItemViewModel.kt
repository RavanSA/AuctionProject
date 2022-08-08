package android.project.auction.presentation.postitem

import android.project.auction.common.Resource
import android.project.auction.domain.use_case.AuctionProjectUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostItemViewModel @Inject constructor(
    private val useCase: AuctionProjectUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PostItemState())
    val state: State<PostItemState> = _state

    init {
        getCategories()
    }

    fun onEvent() {

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


}