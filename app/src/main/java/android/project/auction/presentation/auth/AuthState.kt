package android.project.auction.presentation.auth

data class AuthState(
    val isLoading: Boolean = false,
    val signUpUsername: String = "",
    val signUpUsernameError: String? = null,
    val signUpFullName: String = "",
    val signUpFullNameError: String? = null,
    val signUpPassword: String = "",
    val signUpPasswordError: String? = null,
    val signUpRepeatedPassword: String = "",
    val signUpRepeatedPasswordError: String? = null,
    val signInUsername: String = "",
    val signInUsernameError: String? = null,
    val signInPassword: String = "",
    val signInPasswordError: String? = null,
    val acceptedTerms: Boolean = false,
    val termsError: String? = null
)
