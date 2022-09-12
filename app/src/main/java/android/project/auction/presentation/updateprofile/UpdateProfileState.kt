package android.project.auction.presentation.updateprofile

data class UpdateProfileState(
    val userInfoLoading: Boolean = false,
    val userInfoError: String = "",
    val userName: String = "",
    val title: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val birthday: String = "",
    val postCode: String = "",
    val country: String = "",
    val city: String = "",
    val address: String = "",
    val profilePicture: String = ""
)