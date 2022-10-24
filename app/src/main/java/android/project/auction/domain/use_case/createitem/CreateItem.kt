package android.project.auction.domain.use_case.createitem

import android.content.SharedPreferences
import android.net.Uri
import android.project.auction.common.Constants
import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.items.createitem.CreateItemRequest
import android.project.auction.domain.repository.AuctionRepository
import android.project.auction.domain.repository.FirebaseStorageRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateItem @Inject constructor(
    private val repository: AuctionRepository,
    private val preferences: SharedPreferences,
    private val cloudRepository: FirebaseStorageRepository
) {

    suspend operator fun invoke(createItemRequest: CreateItemRequest, imagesList: List<Uri>) {
        try {

            Resource.Loading<Unit>(true)

            val userID = preferences.getString(Constants.USER_ID, null) ?: Constants.UNAUTHORIZED_USER

            val test = repository.createItem(
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

            addImagesToCloud(test.data.id, imagesList)

            Resource.Loading<Unit>(false)

        } catch (e: IOException) {
            Resource.Error<Unit>("Couldn't reach server. Check your internet connection.")
        } catch (e: HttpException) {
            Resource.Error<Unit>(e.localizedMessage ?: "An unexpected error ocured")
        }
    }

    private suspend fun addImagesToCloud(
        itemId: String,
        image: List<Uri>
    ) {
        cloudRepository.addImagesForItems(itemId, image)
    }


}