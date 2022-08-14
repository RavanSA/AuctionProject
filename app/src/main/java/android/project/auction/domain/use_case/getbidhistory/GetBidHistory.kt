package android.project.auction.domain.use_case.getbidhistory

import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.bids.toBids
import android.project.auction.domain.model.bids.Bids
import android.project.auction.domain.repository.AuctionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBidHistory @Inject constructor(
    private val repository: AuctionRepository
) {

    operator fun invoke(itemId: String): Flow<Resource<List<Bids>>> = flow {
        try {

            emit(Resource.Loading<List<Bids>>(true))

            val result = repository.getBidsHistoryByItemId(itemId = itemId).data


            emit(Resource.Success<List<Bids>>(
                data = result.map { it.toBids() }
            ))

            emit(Resource.Loading<List<Bids>>(false))

        } catch (e: IOException) {
            emit(Resource.Error<List<Bids>>("Couldn't reach server. Check your internet connection."))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Bids>>(e.localizedMessage ?: "An unexpected error ocured"))
        }
    }

}