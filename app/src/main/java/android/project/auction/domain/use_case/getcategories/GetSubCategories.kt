package android.project.auction.domain.use_case.getcategories

import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.categories.subcategories.toSubCategories
import android.project.auction.domain.model.category.SubCategories
import android.project.auction.domain.repository.AuctionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSubCategories @Inject constructor(
    private val repository: AuctionRepository
) {

    operator fun invoke(): Flow<Resource<List<SubCategories>>> = flow {
        try {
            emit(Resource.Loading<List<SubCategories>>(true))

            val response = repository.getCategoriesWithSubcategories().data

            emit(Resource.Success<List<SubCategories>>(
                data = response.map { it.toSubCategories() }
            ))

            emit(Resource.Loading<List<SubCategories>>(false))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<SubCategories>>(
                    e.localizedMessage ?: "An unexpected error ocured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<SubCategories>>("Couldn't reach server. Check your internet connection."))
        }
    }

}