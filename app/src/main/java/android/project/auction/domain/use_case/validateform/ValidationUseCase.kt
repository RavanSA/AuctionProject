package android.project.auction.domain.use_case.validateform

data class ValidationUseCase(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validateRepeatedPassword: ValidateRepeatedPassword,
    val validateTerms: ValidateTerms,
)