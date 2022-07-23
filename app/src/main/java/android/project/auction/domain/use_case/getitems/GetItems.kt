package android.project.auction.domain.use_case.getitems

import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.items.toItem
import android.project.auction.domain.model.item.Item
import android.project.auction.domain.repository.AuctionRepository
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetItems @Inject constructor(
    private val repository: AuctionRepository
) {

    operator fun invoke(): Flow<Resource<List<Item>>> = flow {
        try {
            emit(Resource.Loading<List<Item>>())
            val items = repository.getItems().data
            Log.d("ITEMSTEST", items.toString())
            emit(Resource.Success<List<Item>>(items.map { it.toItem() }))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Item>>(e.localizedMessage ?: "An unexpected error ocured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Item>>("Couldn't reach server. Check your internet connection."))
        }
    }

}