package android.project.auction.presentation.auth

import android.project.auction.common.AuthResult
import android.project.auction.domain.use_case.authentication.AuctionAuthUseCase
import android.project.auction.domain.use_case.validateform.ValidationUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuctionAuthUseCase,
    private val validationUseCase: ValidationUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResults = resultChannel.receiveAsFlow()

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    init {
        authenticate()
    }

    fun onEvent(event: AuthUiEvent) {
        when (event) {
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
            is AuthUiEvent.SignUpRepeatedPasswordChanged -> {
                _state.value = state.value.copy(
                    signUpRepeatedPassword = event.value
                )
            }
            is AuthUiEvent.AcceptTerms -> {
                _state.value = state.value.copy(
                    acceptedTerms = event.value
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

    private fun signUp() {
        val emailResult = validationUseCase.validateEmail.execute(state.value.signUpUsername)
        val passwordResult = validationUseCase.validatePassword.execute(state.value.signUpPassword)
        val repeatedPasswordResult = validationUseCase.validateRepeatedPassword.execute(
            state.value.signUpPassword, state.value.signUpRepeatedPassword
        )
        val termsResult = validationUseCase.validateTerms.execute(state.value.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { !it.successful }

        if (hasError) {
            _state.value = state.value.copy(
                signUpUsernameError = emailResult.errorMessage,
                signUpPasswordError = passwordResult.errorMessage,
                signUpRepeatedPasswordError = repeatedPasswordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }

        viewModelScope.launch {

            _state.value = state.value.copy(
                isLoading = true
            )
            val result = authUseCases.signUp.invoke(
                email = state.value.signUpUsername,
                fullName = state.value.signUpFullName,
                password = state.value.signUpPassword
            )

            resultChannel.send(result)
            validationEventChannel.send(ValidationEvent.Success)

            _state.value = state.value.copy(
                isLoading = false
            )
        }
    }

    private fun signIn() {
        val emailResultSignIn = validationUseCase.validateEmail.execute(state.value.signInUsername)
        val passwordResultSignIn =
            validationUseCase.validatePassword.execute(state.value.signInPassword)

        val hasError = listOf(
            emailResultSignIn,
            passwordResultSignIn,
        ).any { !it.successful }

        if (hasError) {
            _state.value = state.value.copy(
                signInUsernameError = emailResultSignIn.errorMessage,
                signInPasswordError = passwordResultSignIn.errorMessage
            )
            return
        }


        viewModelScope.launch {


            _state.value = state.value.copy(
                isLoading = true
            )
            val result = authUseCases.signIn.invoke(
                email = state.value.signInUsername,
                password = state.value.signInPassword
            )

            resultChannel.send(result)
            validationEventChannel.send(ValidationEvent.Success)

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

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }

}