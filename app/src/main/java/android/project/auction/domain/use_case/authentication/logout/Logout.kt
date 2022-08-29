package android.project.auction.domain.use_case.authentication.logout

import android.content.SharedPreferences
import android.project.auction.common.AuthResult
import android.project.auction.domain.repository.AuthRepository
import retrofit2.HttpException
import javax.inject.Inject

class Logout @Inject constructor(
    private val repository: AuthRepository,
    private val preferences: SharedPreferences
){


    suspend operator fun invoke(): AuthResult<Unit> {
        return try {

            repository.logout()

            preferences
                .edit()
                .putString("JWT", null)
                .apply()

            preferences
                .edit()
                .putString("USERID", null)
                .apply()

            AuthResult.UnAuthorized()

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