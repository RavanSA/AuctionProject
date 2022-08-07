package android.project.auction.domain.use_case.placebidamount

import android.content.SharedPreferences
import android.project.auction.domain.repository.AuctionRepository
import android.util.Log
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PlaceBidAmount @Inject constructor(
    private val repository: AuctionRepository,
    private val preferences: SharedPreferences
) {

    suspend operator fun invoke(amount: Int, itemId: String) {

        try {
//                emit(Resource.Loading<Unit>(true))
            Log.d("INVOKEITEMID", itemId)
            Log.d("INVOKEAMOUNT", amount.toString())

            val userID = preferences.getString("USERID", null) ?: "NOT REGISTERED USER"
            Log.d("INVOKEUSERID", userID)

            repository.placeBidAmount(
                amount = amount,
                itemId = itemId,
                userId = userID
            )

//                Log.d("RESPONSEBID", response.toString())

//                emit(
//                    Resource.Success<Unit>(
//                        data = response
//                    )
//                )
//                emit(Resource.Loading<Unit>(false))
        } catch (e: HttpException) {
                if (e.code() == 401) {
                    Log.d("ERROR", e.toString())

//                    emit(Resource.Error<Unit>("not authorized"))
                } else {
                    Log.d("ERROR", e.toString())

//                    emit(Resource.Error<Unit>("unknown error"))
                }
            } catch (e: IOException) {
                Log.d("ERROR", e.toString())
//                emit(Resource.Error<Unit>("UNKNOWN ERROR"))
            }
        }
}