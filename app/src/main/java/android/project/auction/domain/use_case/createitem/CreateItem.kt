package android.project.auction.domain.use_case.createitem

import android.content.SharedPreferences
import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.items.createitem.CreateItemRequest
import android.project.auction.data.remote.dto.items.createitem.CreateItemResponse
import android.project.auction.domain.repository.AuctionRepository
import android.util.Log
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateItem @Inject constructor(
    private val repository: AuctionRepository,
    private val preferences: SharedPreferences
) {

    suspend operator fun invoke(createItemRequest: CreateItemRequest) {
        try {

            Resource.Loading<Unit>(true)

            val userID = preferences.getString("USERID", null) ?: "NOT REGISTERED USER"

            repository.createItem(
                createItemRequest = CreateItemRequest(
                    title = createItemRequest.title,
                    description = createItemRequest.description,
                    startingPrice = createItemRequest.startingPrice,
                    minIncrease = createItemRequest.minIncrease,
                    startTime = createItemRequest.startTime,
                    endTime = createItemRequest.endTime,
                    categoryId = createItemRequest.categoryId,
                    subCategoryId = createItemRequest.subCategoryId,
                    userId = userID
                )
            )


            Resource.Loading<CreateItemResponse>(false)

        } catch (e: IOException) {
            Resource.Error<CreateItemResponse>("Couldn't reach server. Check your internet connection.")
            Log.d("ERRORIO", "REPOTEST")
        } catch (e: HttpException) {

            if (e.code() == 401) {
                Log.d("ERROR", e.toString())
            } else {
                Log.d("ERROR", e.toString())
            }
            Resource.Error<CreateItemResponse>(e.localizedMessage ?: "An unexpected error ocured")
            Log.d("ERRORHTTP", "REPOTEST")
        }
    }

}