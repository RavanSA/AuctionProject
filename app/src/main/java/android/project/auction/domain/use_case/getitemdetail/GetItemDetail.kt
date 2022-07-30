package android.project.auction.domain.use_case.getitemdetail

import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.items.itemdetail.toItemDetail
import android.project.auction.domain.model.item.ItemDetail
import android.project.auction.domain.repository.AuctionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetItemDetail @Inject constructor(
    private val repository: AuctionRepository
) {

    operator fun invoke(id: String): Flow<Resource<ItemDetail>> = flow {
        try {
            emit(Resource.Loading<ItemDetail>(true))
            val itemDetail = repository.getItemDetailById(id).data
            emit(Resource.Loading<ItemDetail>(false))
            emit(
                Resource.Success<ItemDetail>(
                    data = itemDetail.toItemDetail()
                )
            )

        } catch (e: IOException) {
            emit(Resource.Error<ItemDetail>("Couldn't reach server. Check your internet connection."))
        } catch (e: HttpException) {
            emit(Resource.Error<ItemDetail>(e.localizedMessage ?: "An unexpected error ocured"))
        }
    }

}