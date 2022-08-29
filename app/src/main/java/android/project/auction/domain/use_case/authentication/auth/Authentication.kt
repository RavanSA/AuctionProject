package android.project.auction.domain.use_case.authentication.auth

import android.content.SharedPreferences
import android.project.auction.common.AuthResult
import android.project.auction.domain.repository.AuthRepository
import retrofit2.HttpException
import javax.inject.Inject

class Authentication @Inject constructor(
    private val repository: AuthRepository,
    private val preferences: SharedPreferences
) {


    suspend operator fun invoke(): AuthResult<Unit> {
        return try {

            val token = preferences.getString("JWT", null) ?: return AuthResult.UnAuthorized()
             repository.authenticate("Bearer $token")

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