package android.project.auction.domain.use_case.getallmessagebyitemid

import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.message.toMessages
import android.project.auction.domain.model.messages.Messages
import android.project.auction.domain.repository.AuctionRepository
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllMessageByItemId @Inject constructor(
    private val repository: AuctionRepository
) {

    operator fun invoke(itemId: String): Flow<Resource<List<Messages>>> = flow {
        try {
            emit(Resource.Loading<List<Messages>>(true))

            val messages = repository.getAllMessagesByItemId(itemId).data

            Log.d("MESSAGESTEST", messages.toString())

            emit(Resource.Success<List<Messages>>(
                data = messages.map { it.toMessages() }
            ))

            emit(Resource.Loading<List<Messages>>(false))


        } catch (e: HttpException) {
            emit(Resource.Error<List<Messages>>(e.localizedMessage ?: "An unexpected error ocured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Messages>>("Couldn't reach server. Check your internet connection."))
        }
    }
}