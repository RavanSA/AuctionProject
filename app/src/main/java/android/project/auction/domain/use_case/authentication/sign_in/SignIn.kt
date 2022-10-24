package android.project.auction.domain.use_case.authentication.sign_in

import android.content.SharedPreferences
import android.project.auction.common.AuthResult
import android.project.auction.common.Constants
import android.project.auction.domain.repository.AuthRepository
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
                .putString(Constants.TOKEN_JWT, response.data.token)
                .apply()

            preferences
                .edit()
                .putString(Constants.USER_ID, response.data.userId)
                .apply()

            AuthResult.Authorized()
        } catch (e: HttpException) {
            if(e.code() == 401){

                AuthResult.UnAuthorized()

            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()

        }
    }
}