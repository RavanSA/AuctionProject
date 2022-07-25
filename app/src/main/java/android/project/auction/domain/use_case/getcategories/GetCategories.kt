package android.project.auction.domain.use_case.getcategories

import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.categories.toCategory
import android.project.auction.domain.model.category.Category
import android.project.auction.domain.repository.AuctionRepository
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val repository: AuctionRepository
) {

    operator fun invoke(): Flow<Resource<List<Category>>> = flow {
        try {

            emit(Resource.Loading<List<Category>>(true))
            val categories = repository.getCategories().data
            emit(Resource.Loading<List<Category>>(false))
            emit(Resource.Success<List<Category>>(categories.map { it.toCategory() }))
            Log.d("CATEFORYITEMS", categories.toString())

        } catch (e: HttpException) {
            emit(Resource.Error<List<Category>>(e.localizedMessage ?: "An unexpected error ocured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Category>>("Couldn't reach server. Check your internet connection."))
        }
    }


}