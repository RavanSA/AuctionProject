package android.project.auction.data.repository

import android.content.SharedPreferences
import android.project.auction.data.remote.AuthAPI
import android.project.auction.data.remote.dto.auth.authsignin.AuthSignInRequest
import android.project.auction.data.remote.dto.auth.authsignin.LoginResponse
import android.project.auction.data.remote.dto.auth.authsignup.AuthRequest
import android.project.auction.domain.repository.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val api: AuthAPI,
    private val preferences: SharedPreferences
) : AuthRepository {


    override suspend fun signUp(email: String, fullName: String, password: String): Unit {
        return api.signUp(
            AuthRequest(
                email,
                fullName,
                password
            )
        )
    }

    override suspend fun signIn(email: String, password: String): LoginResponse {
        return api.signIn(
            AuthSignInRequest(
                email,
                password
            )
        )
    }

    override suspend fun authenticate(token: String): Unit {
        return api.authenticate(token)
    }

    override suspend fun logout(): Unit {
        return api.logout()
    }

    override suspend fun getUserIDFromPreferences(): String {
        return preferences.getString("USERID", null) ?: "UNAUTHORIZED USER"
    }

}