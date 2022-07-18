package android.project.auction.data.remote

import android.project.auction.data.remote.dto.auth.authsignin.AuthSignInRequest
import android.project.auction.data.remote.dto.auth.authsignin.LoginResponse
import android.project.auction.data.remote.dto.auth.authsignup.AuthRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface AuthAPI {

    @POST("Identity/Register")
    suspend fun signUp(
        @Body request: AuthRequest
    )

    @POST("Identity/Login")
    suspend fun signIn(
        @Body request: AuthSignInRequest
    ) : LoginResponse

    @GET("Identity/Refresh")
    suspend fun authenticate(
        @Header("Authorization") token: String
    )

    @POST("Identity/Logout")
    suspend fun logout()
}