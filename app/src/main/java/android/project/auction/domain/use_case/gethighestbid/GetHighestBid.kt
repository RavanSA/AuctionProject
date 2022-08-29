package android.project.auction.domain.use_case.gethighestbid

import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.bids.HighestBid
import android.project.auction.domain.repository.AuctionRepository
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

            emit(Resource.Loading<HighestBid?>(true))

            val test2 = repository.getBidsHistoryByItemId(itemId = itemId)

            val response = repository.getHighestBidByItemId(itemId = itemId).data

            emit(
                Resource.Loading<HighestBid?>(
                    false
                )
            )

            emit(
                Resource.Success<HighestBid?>(
                    data = response
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error<HighestBid?>(e.localizedMessage ?: "An unexpected error ocured"))
        } catch (e: IOException) {
            emit(Resource.Error<HighestBid?>("Couldn't reach server. Check your internet connection."))
        }
    }

}
