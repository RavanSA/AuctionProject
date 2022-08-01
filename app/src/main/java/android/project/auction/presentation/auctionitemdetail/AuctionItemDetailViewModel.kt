package android.project.auction.presentation.auctionitemdetail

import android.project.auction.common.Resource
import android.project.auction.domain.use_case.AuctionProjectUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuctionItemDetailViewModel @Inject constructor(
    private val useCase: AuctionProjectUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AuctionItemDetailState())
    val state: State<AuctionItemDetailState> = _state

    var stateBidHistory by mutableStateOf(AuctionItemDetailState())


    init {
        savedStateHandle.get<String>("itemId")?.let { itemId ->
            getItemDetail(itemId)
            getBidHistory(itemId)
        }
    }

    private fun getBidHistory(itemId: String) {
        useCase.getBidHistory.invoke(itemId = itemId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateBidHistory = stateBidHistory.copy(
                        isBidHistoryLoading = true
                    )
                }
                is Resource.Success -> {
                    stateBidHistory = stateBidHistory.copy(
                        bidHistory = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    stateBidHistory = stateBidHistory.copy(
                        bidError = "Error Occured"
                    )
                }
            }
        }
    }

    fun onEvent() {

    }

    private fun getItemDetail(itemId: String) {
        useCase.getItemDetail.invoke(itemId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AuctionItemDetailState(
                        itemDetails = result.data
                    )
                }
                is Resource.Loading -> {
                    _state.value = AuctionItemDetailState(
                        isItemDetailLoading = true
                    )
                }
                is Resource.Error -> {
                    _state.value = AuctionItemDetailState(
                        error = result.message ?: "Cannot find such an item"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}