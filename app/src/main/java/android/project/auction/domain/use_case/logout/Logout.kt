package android.project.auction.domain.use_case.logout

import android.content.SharedPreferences
import android.project.auction.common.AuthResult
import android.project.auction.domain.repository.AuthRepository
import android.util.Log
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

            AuthResult.UnAuthorized()

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