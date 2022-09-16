package android.project.auction.data.remote.dto.userinfo

data class UpdateUserInfoRequest(
    val address: String,
    val birthday: String,
    val city: String,
    val country: String,
    val fullName: String,
    val id: String,
    val phoneNumber: String,
    val postCode: String,
    val profilePicture: String,
    val title: String
)