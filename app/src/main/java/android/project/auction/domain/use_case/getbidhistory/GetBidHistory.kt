package android.project.auction.domain.use_case.getbidhistory

import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.bids.toBids
import android.project.auction.domain.model.bids.Bids
import android.project.auction.domain.repository.AuctionRepository
import android.util.Log
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

            Log.d("USECASEREPO", repository.getBidsHistoryByItemId(itemId).toString())

            val result = repository.getBidsHistoryByItemId(itemId = itemId).data

            Log.d("USECASEBIDHISTORY", result.toString())


            emit(Resource.Success<List<Bids>>(
                data = result.map { it.toBids() }
            ))

            Log.d("USECASEBIDHISTORY", result.toString())

            emit(Resource.Loading<List<Bids>>(false))

        } catch (e: IOException) {
            Log.d("RESORCEIOERROR", e.toString())

            emit(Resource.Error<List<Bids>>("Couldn't reach server. Check your internet connection."))
        } catch (e: HttpException) {
            Log.d("RESORCEHTTPERROR", e.toString())

            emit(Resource.Error<List<Bids>>(e.localizedMessage ?: "An unexpected error ocured"))
        }
    }

}