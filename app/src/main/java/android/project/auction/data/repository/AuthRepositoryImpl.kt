package android.project.auction.data.repository

import android.project.auction.data.remote.AuthAPI
import android.project.auction.data.remote.dto.auth.authsignin.AuthSignInRequest
import android.project.auction.data.remote.dto.auth.authsignin.LoginResponse
import android.project.auction.data.remote.dto.auth.authsignup.AuthRequest
import android.project.auction.domain.repository.AuthRepository
import android.util.Log
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val api: AuthAPI
) : AuthRepository {


    override suspend fun signUp(email: String, fullName: String, password: String): Unit {
        Log.d("APITEST", api.toString())

        return api.signUp(
            AuthRequest(
                email,
                fullName,
                password
            )
        )
    }

    override suspend fun signIn(email: String, password: String): LoginResponse {
        Log.d("SIGNERRORMAIL",email)
        Log.d("SIGNERRORPASSWORD",password)
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

}