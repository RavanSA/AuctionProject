package android.project.auction.domain.use_case.getuserinfobyid

import android.content.SharedPreferences
import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.userinfo.toUserInfo
import android.project.auction.domain.model.userinfo.UserInfo
import android.project.auction.domain.repository.AuctionRepository
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserInfoById @Inject constructor(
    private val repository: AuctionRepository,
    private val preferences: SharedPreferences
) {

    operator fun invoke(): Flow<Resource<UserInfo?>> = flow {
        try {
            emit(Resource.Loading<UserInfo?>(true))

            val userID = preferences.getString("USERID", null)
            if (userID != null) {
                val getUserInfoById = repository.getUserInfoById(userID).data
                Log.d("DATAPROFILE", getUserInfoById.toString())
                Log.d("USERID", userID)

                emit(Resource.Loading<UserInfo?>(false))
                emit(
                    Resource.Success<UserInfo?>(
                        data = getUserInfoById.toUserInfo()
                    )
                )
            } else {
                emit(Resource.Error<UserInfo?>("Unknown Error Occurred"))
            }
        } catch (e: IOException) {
            emit(Resource.Error<UserInfo?>("Couldn't reach server. Check your internet connection."))
        } catch (e: HttpException) {
            emit(Resource.Error<UserInfo?>(e.localizedMessage ?: "An unexpected error occured"))
        }
    }

}