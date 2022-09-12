package android.project.auction.presentation.updateprofile

sealed class UpdateProfileEvent {
    data class OnFullNameChange(val value: String) : UpdateProfileEvent()
    data class OnTitleChange(val value: String) : UpdateProfileEvent()
    data class OnEmailChange(val value: String) : UpdateProfileEvent()
    data class OnPhoneNumberChange(val value: String) : UpdateProfileEvent()
    data class OnBirthdayChange(val value: String) : UpdateProfileEvent()
    data class OnCountryChange(val value: String) : UpdateProfileEvent()
    data class OnPostCodeChange(val value: String) : UpdateProfileEvent()
    data class OnCityChange(val value: String) : UpdateProfileEvent()
    data class OnAddressChange(val value: String) : UpdateProfileEvent()
    data class OnProfilePictureChange(val value: String) : UpdateProfileEvent()


}