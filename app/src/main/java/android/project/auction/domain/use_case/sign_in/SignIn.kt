package android.project.auction.domain.use_case.sign_in

import android.content.SharedPreferences
import android.project.auction.common.AuthResult
import android.project.auction.domain.repository.AuthRepository
import android.util.Log
import retrofit2.HttpException
import javax.inject.Inject

class SignIn @Inject constructor(
    private val repository: AuthRepository,
    private val preferences: SharedPreferences
) {


    suspend operator fun invoke(email: String, password: String): AuthResult<Unit> {
        return try {

            val response = repository.signIn(
                email = email,
                password = password
            )

            preferences
                .edit()
                .putString("JWT",response.data.token)
                .apply()

            AuthResult.Authorized()
        } catch (e: HttpException) {
            if(e.code() == 401){

                AuthResult.UnAuthorized()

            } else {
                Log.d("ERROR", e.toString())
                AuthResult.UnknownError()

            }
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())

            AuthResult.UnknownError()

        }
    }
}