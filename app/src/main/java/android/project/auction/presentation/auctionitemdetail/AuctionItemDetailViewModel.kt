package android.project.auction.presentation.auctionitemdetail

import android.project.auction.common.Resource
import android.project.auction.data.local.entity.Bids
import android.project.auction.data.local.entity.Favorites
import android.project.auction.data.remote.dto.bids.HighestBid
import android.project.auction.domain.model.exceptions.InvalidFavoriteItemException
import android.project.auction.domain.use_case.authentication.AuctionAuthUseCase
import android.project.auction.domain.use_case.usecases.AuctionProjectUseCase
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuctionItemDetailViewModel @Inject constructor(
    private val useCase: AuctionProjectUseCase,
    private val authUseCase: AuctionAuthUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AuctionItemDetailState())
    val state: State<AuctionItemDetailState> = _state

    var stateBidHistory by mutableStateOf(AuctionItemDetailState())

    var statePlaceBid by mutableStateOf(AuctionItemDetailState())

    var stateHighestBid by mutableStateOf(AuctionItemDetailState())

    private val _eventFlow = MutableSharedFlow<ItemDetailUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentItem: Favorites? = null

    private var itemFavoritesJob: Job? = null

    var itemID: String = ""

    init {
        savedStateHandle.get<String>("itemId")?.let { itemId ->
            getItemDetail(itemId)
            getBidHistory(itemId)
            getItemId(itemId)
            getHighestBid(itemId)
        }
    }

    private fun getItemId(itemId: String) {
        Log.d("GETTINGITEMID", itemId)
        itemID = itemId
    }

    fun onEvent(event: AuctionItemDetailEvent) {
        when (event) {
            is AuctionItemDetailEvent.BidAmountChanged -> {
                statePlaceBid = statePlaceBid.copy(
                    bidAmount = event.amount,
                    itemID = event.itemId
                )
            }
            is AuctionItemDetailEvent.OnBidAmountPlaced -> {
                placeBidAmount()
            }
            is AuctionItemDetailEvent.AddItemToFavorites -> {
                viewModelScope.launch {
                    try {
                        state.value.itemDetails?.let {

                            Favorites(
                                description = it.description,
                                endTime = it.endTime,
                                id = it.id,
                                minIncrease = it.minIncrease,
                                pictures = it.pictures,
                                startTime = it.startTime,
                                startingPrice = it.startingPrice,
                                subCategoryId = it.subCategoryId,
                                categoryId = it.categoryId,
                                title = it.title,
                                userFullName = it.userFullName,
                                userId = it.userId
                            )

                        }?.let {
                            useCase.addFavoriteItem.invoke(
                                it
                            )
                        }

                        val highestBid = state.value.highestBid

                        if (highestBid != null) {
                            state.value.highestBid?.let {
                                Bids(
                                    id = highestBid.id,
                                    itemId = highestBid.itemId,
                                    userId = highestBid.userId,
                                    amount = highestBid.amount,
                                    created = ""
                                )
                            }?.let {
                                useCase.addItemBids.invoke(
                                    it
                                )
                            }
                        }

                        _eventFlow.emit(ItemDetailUiEvent.AddFavoriteItem)
                        _eventFlow.emit(
                            ItemDetailUiEvent.ShowSnackbar(
                                message = "Item added to favorites"
                            )
                        )
                        getFavoriteItemById(itemID)

                    } catch (e: InvalidFavoriteItemException) {
                        _eventFlow.emit(
                            ItemDetailUiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
            is AuctionItemDetailEvent.RestoreItem -> {
                viewModelScope.launch {
                    useCase.addFavoriteItem.invoke(
                        currentItem ?: return@launch
                    )
                    getFavoriteItemById(itemID)

                }
            }
            is AuctionItemDetailEvent.DeleteItem -> {
                viewModelScope.launch {
                    currentItem?.let { useCase.deleteFavoriteItem.invoke(it) }
                    getFavoriteItemById(itemID)

                }
            }
        }
    }

    private fun getBidHistory(itemId: String) {
        useCase.getBidHistory.invoke(itemId = itemId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("RESOURCELOADING", result.data.toString())

                    stateBidHistory = stateBidHistory.copy(
                        isBidHistoryLoading = true
                    )
                }
                is Resource.Success -> {
                    Log.d("RESOURCESUCCES", result.data.toString())
                    result.data?.let { listings ->
                        stateBidHistory = stateBidHistory.copy(
                            bidHistory = listings
                        )
                    }
                }
                is Resource.Error -> {
                    Log.d("RESORCE ERROR", result.data.toString())

                    stateBidHistory = stateBidHistory.copy(
                        bidError = "Error Occured"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getItemDetail(itemId: String) {
        useCase.getItemDetail.invoke(itemId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("TSTHİHESGTBID", result.data.toString())
                    val data = result.data
                    getFavoriteItemById(itemId)

                    currentItem = data?.let {
                        Favorites(
                            description = data.description,
                            endTime = data.endTime,
                            id = data.id,
                            minIncrease = data.minIncrease,
                            pictures = data.pictures,
                            startTime = data.startTime,
                            startingPrice = data.startingPrice,
                            subCategoryId = data.subCategoryId,
                            categoryId = data.categoryId,
                            title = data.title,
                            userFullName = data.userFullName,
                            userId = it.userId
                        )
                    }

                    _state.value = AuctionItemDetailState(
                        itemDetails = result.data
                    )
                    getUserId()
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

    private fun placeBidAmount() {
        viewModelScope.launch {
            stateBidHistory = stateBidHistory.copy(
                postingBidAmount = true
            )

            useCase.placeBidAmount.invoke(
                amount = statePlaceBid.bidAmount.toInt(),
                itemId = itemID
            )

            stateBidHistory = stateBidHistory.copy(
                postingBidAmount = false
            )
        }
    }

    private fun getHighestBid(itemId: String) {
        useCase.getHighestBid.invoke(itemId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateHighestBid = stateHighestBid.copy(
                        highestBidLoading = true
                    )
                }
                is Resource.Success -> {
                    stateHighestBid = stateHighestBid.copy(
                        highestBid = result.data ?: HighestBid(
                            0.0,
                            "", "", "", ""
                        )
                    )


                }
                is Resource.Error -> {
                    stateHighestBid = stateHighestBid.copy(
                        error = result.message ?: "ERROR OCCURED"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUserId() {
        viewModelScope.launch {
            Log.d("LOGUSERID", authUseCase.userId.invoke())
            _state.value = state.value.copy(
                userId = authUseCase.userId.invoke()
            )

        }
    }

    private fun getFavoriteItemById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val isAdded = useCase.getFavoriteItemById.invoke(id)
            Log.d("VIEWMODELTEST", isAdded.toString())
            withContext(Dispatchers.Main) {

                _state.value = state.value.copy(
                    itemAddedFavorite = isAdded
                )

            }

        }
    }

    sealed class ItemDetailUiEvent {
        data class ShowSnackbar(val message: String) : ItemDetailUiEvent()
        object AddFavoriteItem : ItemDetailUiEvent()
    }

}