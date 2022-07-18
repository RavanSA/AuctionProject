package android.project.auction.presentation.auth

data class AuthState(
    val isLoading: Boolean = false,
    val signUpUsername: String = "",
    val signUpFullName: String = "",
    val signUpPassword: String = "",
    val signInUsername: String = "",
    val signInPassword: String = ""
 )
