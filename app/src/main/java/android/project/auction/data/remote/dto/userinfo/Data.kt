package android.project.auction.data.remote.dto.userinfo

import android.project.auction.domain.model.userinfo.UserInfo

data class Data(
    val address: String?,
    val birthday: String?,
    val city: String?,
    val country: String?,
    val email: String?,
    val fullName: String?,
    val id: String?,
    val phoneNumber: String?,
    val postCode: String?,
    val profilePicture: String?,
    val title: String?,
    val userName: String?
)


fun Data.toUserInfo(): UserInfo? {
    return UserInfo(
        address = address,
        birthday = birthday,
        city = city,
        country = country,
        email = email,
        fullName = fullName,
        id = id,
        phoneNumber = phoneNumber,
        postCode = postCode,
        profilePicture = profilePicture,
        title = title,
        userName = userName
    )
}