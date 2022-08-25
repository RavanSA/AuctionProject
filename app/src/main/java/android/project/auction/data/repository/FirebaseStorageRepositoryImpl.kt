package android.project.auction.data.repository

import android.net.Uri
import android.project.auction.data.remote.dto.items.createitem.CreatePictureItemId
import android.project.auction.domain.repository.FirebaseStorageRepository
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.*
import javax.inject.Inject

class FirebaseStorageRepositoryImpl @Inject constructor(
    private val repository: AuctionRepositoryImpl
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
                Log.d("TASKURL", task.toString())

                task.addOnCompleteListener { uri ->
                    GlobalScope.launch(Dispatchers.IO) {

                        withContext(Dispatchers.IO) {

                            imageUrl = uri.result.toString()
                            imageList.add(imageUrl)
                            Log.d("IMAGEURL", imageUrl)
                            Log.d("IMAGELIST", imageList.toString())
                            if (imageList.size == size) {
                                addImageForItemToRemoteDatabase()
                                //TODO UPDATEIMAGE
                            }
                        }
                    }
                }
            }

        Log.d("IMAGES", imageUrl)
        Log.d("IMAGELIST", imageList.toString())
        return imageUrl
    }

    override suspend fun addImagesForItems(itemId: String, uris: List<Uri>): List<String> {
        val itemImageUrls = ArrayList<String>()
        Log.d("AADDD", uris.size.toString())
        size = uris.size

        val uploadedImageUriLink = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            (uris.indices).map { index ->
                uploadItemImages(itemId, uris[index])
            }
        }

        Log.d("ADDEDIMAHES", uploadedImageUriLink.toString())
        uploadedImageUriLink.forEach { photoUriLink ->
            itemImageUrls.add((photoUriLink.toString()))
        }
        //NULL
        Log.d("IMAGEURLSFORPOST", itemImageUrls.toString())
        Log.d("ADDIMAGELİST", imageList.toString())

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
        Log.d("IMAGELIST", "1111111111")

        repository.addImageForItemToRemoteDatabase(
            createPictureForItem = CreatePictureItemId(
                itemId = itemID,
                pictures = imageList
            )
        )
    }

}