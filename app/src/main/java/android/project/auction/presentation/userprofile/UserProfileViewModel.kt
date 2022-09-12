package android.project.auction.presentation.userprofile

import android.project.auction.common.Resource
import android.project.auction.domain.use_case.usecases.AuctionProjectUseCase
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val useCase: AuctionProjectUseCase
) : ViewModel() {

    private val _state = mutableStateOf(UserProfileState())
    val state: State<UserProfileState> = _state

    init {
        getUserInfoById()
    }

    fun onEvent(event: UserProfileEvent) {
        when (event) {

            is UserProfileEvent.OnTabChanged -> {
                getSellerOrBidderAuctions(event.sellerOrBidder)
            }

        }
    }

    private fun getSellerOrBidderAuctions(
        sellerOrBidder: String
    ) {
        useCase.getSellerOrBidderList.invoke(sellerOrBidder).onEach { data ->

            _state.value = state.value.copy(
                auctionList = data
            )

        }.launchIn(viewModelScope)
    }

    private fun getUserInfoById() {
        useCase.getUserInfoById.invoke().onEach { data ->
            when (data) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        userInfoLoading = true
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        userInfoError = data.message ?: "Error Occured"
                    )
                }
                is Resource.Success -> {
                    Log.d("DATATEST", data.data.toString())
                    _state.value = state.value.copy(
                        userInfo = data.data
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}