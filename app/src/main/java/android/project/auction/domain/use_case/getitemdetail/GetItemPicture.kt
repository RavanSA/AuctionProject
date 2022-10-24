package android.project.auction.domain.use_case.getitemdetail

import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.items.getpictures.toItemImages
import android.project.auction.domain.model.item.ItemImages
import android.project.auction.domain.repository.AuctionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetItemPicture @Inject constructor(
    private val repository: AuctionRepository
) {

    operator fun invoke(id: String): Flow<Resource<List<ItemImages>>> = flow {
        try {
            emit(Resource.Loading<List<ItemImages>>(true))

            val pictures = repository.getItemPictures(id)

            emit(Resource.Loading<List<ItemImages>>(false))

            emit(Resource.Success<List<ItemImages>>(
                data = pictures.data.map { it.toItemImages() }
            ))

        } catch (e: IOException) {
            emit(Resource.Error<List<ItemImages>>("Couldn't reach server. Check your internet connection."))

        } catch (e: HttpException) {
            emit(
                Resource.Error<List<ItemImages>>(
                    e.localizedMessage ?: "An unexpected error ocured"
                )
            )

        }
    }

}