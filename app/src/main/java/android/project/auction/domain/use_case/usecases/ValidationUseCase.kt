package android.project.auction.domain.use_case.usecases

import android.project.auction.domain.use_case.validateform.ValidateEmail
import android.project.auction.domain.use_case.validateform.ValidatePassword
import android.project.auction.domain.use_case.validateform.ValidateRepeatedPassword
import android.project.auction.domain.use_case.validateform.ValidateTerms

data class ValidationUseCase(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validateRepeatedPassword: ValidateRepeatedPassword,
    val validateTerms: ValidateTerms,
)