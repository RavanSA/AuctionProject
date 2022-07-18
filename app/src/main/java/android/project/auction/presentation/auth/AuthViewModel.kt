package android.project.auction.presentation.auth

import android.project.auction.common.AuthResult
import android.project.auction.domain.use_case.AuctionAuthUseCase
import android.project.auction.presentation.auth.AuthState
import android.project.auction.presentation.auth.AuthUiEvent
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuctionAuthUseCase
) : ViewModel(){

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResults = resultChannel.receiveAsFlow()

    init {
        authenticate()
    }

    fun onEvent(event: AuthUiEvent){
        when(event){
            is AuthUiEvent.SignInUsernameChanged -> {
                _state.value = state.value.copy(
                    signInUsername = event.value
                )
            }
            is AuthUiEvent.SignInPasswordChanged -> {
                _state.value = state.value.copy(
                    signInPassword = event.value
                )
            }
            is AuthUiEvent.SignIn -> {
                signIn()
            }
            is AuthUiEvent.SignUpUsernameChanged -> {
                _state.value = state.value.copy(
                    signUpUsername = event.value
                )
            }
            is AuthUiEvent.SignUpFullNameChanged -> {
                _state.value = state.value.copy(
                    signUpFullName = event.value
                )
            }
            is AuthUiEvent.SignUpPasswordChanged -> {
                _state.value = state.value.copy(
                    signUpPassword = event.value
                )
            }
            is AuthUiEvent.SignUp -> {
                signUp()
            }
            is AuthUiEvent.Logout -> {
                logout()
            }
        }
    }

    private fun signUp(){
        viewModelScope.launch{
            _state.value = state.value.copy(
                isLoading = true
            )
            val result = authUseCases.signUp.invoke(
                email = state.value.signUpUsername,
                fullName = state.value.signUpFullName,
                password = state.value.signUpPassword
            )

            resultChannel.send(result)
            _state.value = state.value.copy(
                isLoading = false
            )
        }
    }

    private fun signIn(){
        viewModelScope.launch{
            _state.value = state.value.copy(
                isLoading = true
            )
            val result = authUseCases.signIn.invoke(
                email = state.value.signInUsername,
                password = state.value.signInPassword
            )

            resultChannel.send(result)
            _state.value = state.value.copy(
                isLoading = false
            )
        }
    }

    private fun authenticate(){
        viewModelScope.launch{
            _state.value = state.value.copy(
                isLoading = true
            )

            val result = authUseCases.authentication.invoke()

            resultChannel.send(result)
            _state.value = state.value.copy(
                isLoading = false
            )
        }
    }

    private fun logout() {
        viewModelScope.launch{
            _state.value = state.value.copy(
                isLoading = true
            )

            val result = authUseCases.logout.invoke()

            resultChannel.send(result)
            _state.value = state.value.copy(
                isLoading = false
            )

        }
    }

}