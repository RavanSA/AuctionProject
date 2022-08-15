package android.project.auction.domain.use_case.gethighestbid

import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.bids.HighestBid
import android.project.auction.domain.repository.AuctionRepository
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHighestBid @Inject constructor(
    private val repository: AuctionRepository
) {

    operator fun invoke(itemId: String): Flow<Resource<HighestBid?>> = flow {
        try {
            Log.d("RESPONSE1234", itemId)

            emit(Resource.Loading<HighestBid?>(true))

            val test2 = repository.getBidsHistoryByItemId(itemId = itemId)

            val response = repository.getHighestBidByItemId(itemId = itemId).data

            Log.d("RESPONSE", response.toString())
            Log.d("RESPONSE1", test2.toString())

            emit(
                Resource.Loading<HighestBid?>(
                    false
                )
            )


            Log.d("REPPONSE111222", test2.toString())

            emit(
                Resource.Success<HighestBid?>(
                    data = response
                )
            )

            Log.d("REPPONSE111", response.toString())
//            Log.d("REPPONSE1122221", response.toString())
        } catch (e: HttpException) {
            Log.d("EEROR", e.toString())
            emit(Resource.Error<HighestBid?>(e.localizedMessage ?: "An unexpected error ocured"))
        } catch (e: IOException) {
            Log.d("ERROR", e.toString())
            emit(Resource.Error<HighestBid?>("Couldn't reach server. Check your internet connection."))
        }
    }

}
