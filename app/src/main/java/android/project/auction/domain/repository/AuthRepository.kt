package android.project.auction.domain.repository

import android.project.auction.data.remote.dto.auth.authsignin.LoginResponse


interface AuthRepository {

    suspend fun signUp(email: String, fullName: String, password: String): Unit

    suspend fun signIn(email: String, password: String): LoginResponse

    suspend fun authenticate(token: String): Unit

    suspend fun logout(): Unit

    suspend fun getUserIDFromPreferences(): String


}