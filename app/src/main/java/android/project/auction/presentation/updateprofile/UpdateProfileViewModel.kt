package android.project.auction.presentation.updateprofile

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
class UpdateProfileViewModel @Inject constructor(
    private val useCase: AuctionProjectUseCase
) : ViewModel() {

    private val _state = mutableStateOf(UpdateProfileState())
    val state: State<UpdateProfileState> = _state

    init {
        getUserInfoById()
    }

    fun onEvent(event: UpdateProfileEvent) {
        when (event) {
            is UpdateProfileEvent.OnFullNameChange -> {
                _state.value = state.value.copy(
                    userName = event.value
                )
            }
            is UpdateProfileEvent.OnTitleChange -> {
                _state.value = state.value.copy(
                    title = event.value
                )
            }
            is UpdateProfileEvent.OnEmailChange -> {
                _state.value = state.value.copy(
                    email = event.value
                )
            }
            is UpdateProfileEvent.OnPhoneNumberChange -> {
                _state.value = state.value.copy(
                    phoneNumber = event.value
                )
            }
            is UpdateProfileEvent.OnBirthdayChange -> {
                _state.value = state.value.copy(
                    birthday = event.value
                )
            }
            is UpdateProfileEvent.OnCountryChange -> {
                _state.value = state.value.copy(
                    country = event.value
                )
            }
            is UpdateProfileEvent.OnPostCodeChange -> {
                _state.value = state.value.copy(
                    postCode = event.value
                )
            }
            is UpdateProfileEvent.OnCityChange -> {
                _state.value = state.value.copy(
                    city = event.value
                )
            }
            is UpdateProfileEvent.OnAddressChange -> {
                _state.value = state.value.copy(
                    address = event.value
                )
            }
            is UpdateProfileEvent.OnProfilePictureChange -> {
                _state.value = state.value.copy(
                    profilePicture = event.value
                )
            }
        }
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
                        userName = data.data?.userName ?: ""
                    )
                    _state.value = state.value.copy(
                        title = data.data?.title ?: ""
                    )
                    _state.value = state.value.copy(
                        email = data.data?.email ?: ""
                    )
                    _state.value = state.value.copy(
                        phoneNumber = data.data?.phoneNumber ?: ""
                    )
                    _state.value = state.value.copy(
                        birthday = data.data?.birthday ?: ""
                    )
                    _state.value = state.value.copy(
                        postCode = data.data?.postCode ?: ""
                    )
                    _state.value = state.value.copy(
                        country = data.data?.country ?: ""
                    )
                    _state.value = state.value.copy(
                        city = data.data?.city ?: ""
                    )
                    _state.value = state.value.copy(
                        address = data.data?.address ?: ""
                    )
                    _state.value = state.value.copy(
                        profilePicture = data.data?.profilePicture ?: ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}