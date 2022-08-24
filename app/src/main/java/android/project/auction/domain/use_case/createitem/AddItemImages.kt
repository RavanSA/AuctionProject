package android.project.auction.domain.use_case.createitem

import android.net.Uri
import android.project.auction.domain.repository.FirebaseStorageRepository

class AddItemImages(
    private val repository: FirebaseStorageRepository
) {

    suspend operator fun invoke(
        itemId: String,
        image: List<Uri>
    ) {
        repository.addImagesForItems(itemId, image)
    }

}