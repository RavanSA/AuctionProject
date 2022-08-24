package android.project.auction.domain.use_case.createitem

import android.content.SharedPreferences
import android.net.Uri
import android.project.auction.common.Resource
import android.project.auction.data.remote.dto.items.createitem.CreateItemRequest
import android.project.auction.domain.repository.AuctionRepository
import android.project.auction.domain.repository.FirebaseStorageRepository
import android.util.Log
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

            val userID = preferences.getString("USERID", null) ?: "NOT REGISTERED USER"

//            repository.createItem(
//                createItemRequest = CreateItemRequest(
//                    title = createItemRequest.title,
//                    description = createItemRequest.description,
//                    startingPrice = createItemRequest.startingPrice,
//                    minIncrease = createItemRequest.minIncrease,
//                    startTime = createItemRequest.startTime,
//                    endTime = createItemRequest.endTime,
//                    categoryId = createItemRequest.categoryId,
//                    subCategoryId = createItemRequest.subCategoryId,
//                    userId = userID
//                )
//            )
            Log.d("CREAITEMREQUS", createItemRequest.toString())
            Log.d("TITLE", createItemRequest.title)
            Log.d("DESCRIPTION", createItemRequest.description)
            Log.d("STARTINGPRCI", createItemRequest.startingPrice.toString())
            Log.d("MININCREASE", createItemRequest.minIncrease.toString())
            Log.d("STATRTIME", createItemRequest.startTime)
            Log.d("ENDTIME", createItemRequest.endTime)
            Log.d("Category", createItemRequest.categoryId)
            Log.d("SUBCATEGIRYUF", createItemRequest.subCategoryId)
            Log.d("USERID", userID)

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

            Log.d("IMAGELIST URI", imagesList.toString())

            Log.d("ITEMIDCREATE", test.data.id)

            addImagesToCloud(test.data.id, imagesList)



            Resource.Loading<Unit>(false)

        } catch (e: IOException) {
            Resource.Error<Unit>("Couldn't reach server. Check your internet connection.")
            Log.d("ERRORIO", "REPOTEST")
        } catch (e: HttpException) {

            if (e.code() == 401) {
                Log.d("ERROR", e.toString())
            } else {
                Log.d("ERROR", e.toString())
            }
            Resource.Error<Unit>(e.localizedMessage ?: "An unexpected error ocured")
            Log.d("ERRORHTTP", "REPOTEST")
        }
    }

    private suspend fun addImagesToCloud(
        itemId: String,
        image: List<Uri>
    ) {
        cloudRepository.addImagesForItems(itemId, image)
    }


}