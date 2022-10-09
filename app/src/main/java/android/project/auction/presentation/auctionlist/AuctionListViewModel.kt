package android.project.auction.presentation.auctionlist

import android.project.auction.common.Resource
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
class AuctionListViewModel @Inject constructor(
    private val auctionProjectUseCase: AuctionProjectUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AuctionListState())
    val state: State<AuctionListState> = _state

    var stateItem by mutableStateOf(AuctionListState())

    var userInfoState by mutableStateOf(AuctionListState())

    private var searchJob: Job? = null

    init {
        _state.value = _state.value.copy(
            loading = true
        )
        getCategories()
        getItems()
        getUserInfo()
        _state.value = _state.value.copy(
            loading = false
        )
    }

    fun onEvent(event: AuctionListEvent) {
        when (event) {
            is AuctionListEvent.Refresh -> {
                getItems(getItemsFromRemote = true)
            }
            is AuctionListEvent.OnSearchQueryChange -> {
                stateItem = stateItem.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getItems()
                }
            }
            is AuctionListEvent.OnMoreItemClicked -> {
                getItems()
            }
        }
    }

    private fun getCategories() {
        auctionProjectUseCase.getCategories.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(categories = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = AuctionListState(
                        errorCategories = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(isCategoriesLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getItems(
        query: String = stateItem.searchQuery.lowercase(),
        getItemsFromRemote: Boolean = false
    ) {
        auctionProjectUseCase.getItems.invoke(getItemsFromRemote, query).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let { listings ->
                        stateItem = stateItem.copy(
                            item = listings
                        )
                    }
                }
                is Resource.Error -> {
                    stateItem = stateItem.copy(
                        errorItem = "ERROR OCCURED"
                    )
                }
                is Resource.Loading -> {
                    stateItem = stateItem.copy(
                        isItemLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUserInfo() {
        auctionProjectUseCase.getUserInfoById.invoke().onEach { data ->
            when (data) {
                is Resource.Loading -> {
                    userInfoState = userInfoState.copy(
                        loadingUserInfo = true
                    )
                }
                is Resource.Error -> {
                    userInfoState = userInfoState.copy(
                        userInfoError = "ERROR OCCURRED"
                    )
                }
                is Resource.Success -> {
                    userInfoState = userInfoState.copy(
                        userInfo = data.data
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}

