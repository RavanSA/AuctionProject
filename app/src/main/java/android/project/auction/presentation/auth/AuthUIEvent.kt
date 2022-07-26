package android.project.auction.presentation.auth


sealed class AuthUiEvent {
    data class SignUpUsernameChanged(val value: String) : AuthUiEvent()
    data class SignUpFullNameChanged(val value: String) : AuthUiEvent()
    data class SignUpPasswordChanged(val value: String) : AuthUiEvent()
    data class SignUpRepeatedPasswordChanged(val value: String) : AuthUiEvent()
    data class AcceptTerms(val value: Boolean) : AuthUiEvent()
    object SignUp : AuthUiEvent()

    data class SignInUsernameChanged(val value: String) : AuthUiEvent()
    data class SignInPasswordChanged(val value: String) : AuthUiEvent()
    object SignIn : AuthUiEvent()

    object Logout : AuthUiEvent()

}