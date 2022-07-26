package android.project.auction.domain.use_case.validateform

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)