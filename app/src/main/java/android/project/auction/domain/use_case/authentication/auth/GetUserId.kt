package android.project.auction.domain.use_case.authentication.auth

import android.project.auction.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserId @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): String {
        return repository.getUserIDFromPreferences()
    }

}