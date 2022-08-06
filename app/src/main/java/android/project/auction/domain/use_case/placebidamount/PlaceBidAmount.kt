package android.project.auction.domain.use_case.placebidamount

import android.project.auction.common.Resource
import android.project.auction.domain.repository.AuctionRepository
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PlaceBidAmount @Inject constructor(
    private val repository: AuctionRepository
) {

    suspend operator fun invoke(amount: Int, itemId: String, userId: String): Flow<Resource<Unit>> =
        flow {
            try {
                emit(Resource.Loading(true))

                val response = repository.placeBidAmount(
                    amount = amount,
                    itemId = itemId,
                    userId = userId
                )

                emit(
                    Resource.Success(
                        data = response
                    )
                )
                emit(Resource.Loading(false))
            } catch (e: HttpException) {
                if (e.code() == 401) {
                    emit(Resource.Error("not authorized"))
                } else {
                    emit(Resource.Error("unknown error"))
                }
            } catch (e: IOException) {
                Log.d("ERROR", e.toString())
                emit(Resource.Error("UNKNOWN ERROR"))
            }
        }
}