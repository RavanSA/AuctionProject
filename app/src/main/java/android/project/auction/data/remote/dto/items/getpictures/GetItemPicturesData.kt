package android.project.auction.data.remote.dto.items.getpictures

import android.project.auction.domain.model.item.ItemImages

data class GetItemPicturesData(
    val id: String,
    val itemId: String,
    val url: String
)

fun GetItemPicturesData.toItemImages(): ItemImages {
    return ItemImages(
        id = id,
        itemId = itemId,
        url = url
    )
}
