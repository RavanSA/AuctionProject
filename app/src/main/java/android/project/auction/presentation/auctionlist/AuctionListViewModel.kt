package android.project.auction.presentation.auctionlist

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
class AuctionListViewModel @Inject constructor(
    private val auctionProjectUseCase: AuctionProjectUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AuctionListState())
    val state: State<AuctionListState> = _state

    init {
        getCategories()
        getItems()
    }

    private fun getCategories() {
        auctionProjectUseCase.getCategories.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AuctionListState(categories = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = AuctionListState(
                        errorCategories = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AuctionListState(isCategoriesLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getItems() {
        auctionProjectUseCase.getItems.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AuctionListState(
                        item = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = AuctionListState(
                        errorItem = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AuctionListState(
                        isCategoriesLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}

