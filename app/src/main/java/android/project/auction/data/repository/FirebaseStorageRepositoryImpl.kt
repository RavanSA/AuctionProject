package android.project.auction.data.repository

import android.content.SharedPreferences
import android.net.Uri
import android.project.auction.data.remote.dto.items.createitem.CreatePictureItemId
import android.project.auction.data.remote.dto.items.getpictures.AddItemPictureRequest
import android.project.auction.domain.repository.FirebaseStorageRepository
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.*
import javax.inject.Inject

class FirebaseStorageRepositoryImpl @Inject constructor(
    private val repository: AuctionRepositoryImpl,
    private val preferences: SharedPreferences
) : FirebaseStorageRepository {

    private var imageList: MutableList<String> = mutableListOf()
    private var itemID: String = ""
    private var size: Int = 0

    private fun uploadItemImages(itemId: String, image: Uri): String {
        val storageReference = getStorageReference()
        var imageUrl = ""
        itemID = itemId

        storageReference.child(itemId).child(getRandomString()).putFile(image)
            .addOnSuccessListener {
                val task = it.storage.downloadUrl

                task.addOnCompleteListener { uri ->
                    GlobalScope.launch(Dispatchers.IO) {

                        withContext(Dispatchers.IO) {

                            imageUrl = uri.result.toString()
                            imageList.add(imageUrl)
                            Log.d("IMAGEURL", imageUrl)
                            Log.d("IMAGELIST", imageList.toString())
                            if (imageList.size == size) {
                                addImageForItemToRemoteDatabase()
                                addMainItemPicture(itemId, imageList[0])
                            }
                        }
                    }
                }
            }

        return imageUrl
    }

    override suspend fun addImagesForItems(itemId: String, uris: List<Uri>): List<String> {
        val itemImageUrls = ArrayList<String>()
        size = uris.size

        val uploadedImageUriLink = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            (uris.indices).map { index ->
                uploadItemImages(itemId, uris[index])
            }
        }

        uploadedImageUriLink.forEach { photoUriLink ->
            itemImageUrls.add((photoUriLink.toString()))
        }

        return itemImageUrls
    }


    private fun getStorageReference(): StorageReference {
        return FirebaseStorage.getInstance().reference
    }

    private fun getRandomString(): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..10)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private suspend fun addImageForItemToRemoteDatabase() {

        repository.addImageForItemToRemoteDatabase(
            createPictureForItem = CreatePictureItemId(
                itemId = itemID,
                pictures = imageList
            )
        )
    }

    private suspend fun addMainItemPicture(itemId: String, mainItemPicture: String) {
        repository.addItemMainPicture(
            AddItemPictureRequest(
                id = itemId,
                mainItemPicture = mainItemPicture
            )
        )

    }

    override suspend fun updateProfileImage(uri: Uri) {
        val storageReference = getStorageReference()
        var imageUrl = ""
        val userID = preferences.getString("USERID", null) ?: "NOT REGISTERED USER"
        Log.d("TEST7URI", uri.toString())

        storageReference.child(userID).putFile(uri)
            .addOnSuccessListener {
                val task = it.storage.downloadUrl

                task.addOnCompleteListener { uri ->
                    GlobalScope.launch(Dispatchers.IO) {

                        withContext(Dispatchers.IO) {
                            Log.d("TEST8URL", imageUrl)
                            imageUrl = uri.result.toString()
                            if (imageUrl != "") {
                                preferences
                                    .edit()
                                    .putString("USER_PROFILE_IMAGE", imageUrl)
                                    .apply()
                                Log.d("TEST9URL", imageUrl)
                            }
                            Log.d("TEST10URL", imageUrl)


                        }
                    }
                }
            }

    }


}