package android.project.auction.domain.repository

import android.net.Uri

interface FirebaseStorageRepository {

    suspend fun addImagesForItems(itemId: String, uris: List<Uri>): List<String>

    suspend fun updateProfileImage(uri: Uri)

}