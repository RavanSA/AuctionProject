package android.project.auction.data.remote.dto.items

import android.project.auction.data.local.entity.Items
import android.project.auction.domain.model.item.Item

data class ItemDataDto(
    val description: String,
    val endTime: String,
    val id: String,
    val minIncrease: Int,
    val pictures: List<Picture> = emptyList(),
    val startTime: String,
    val startingPrice: Int,
    val subCategoryId: String,
    val title: String,
    val userFullName: String,
    val userId: String
)

fun ItemDataDto.toItem(): Item {
    return Item(
        description = description,
        endTime = endTime,
        id = id,
        minIncrease = minIncrease,
        pictures = "",
        startTime = startTime,
        startingPrice = startingPrice,
        subCategoryId = subCategoryId,
        title = title,
        userFullName = "userFullName",
        userId = userId
    )
}

fun ItemDataDto.toItems(): Items {
    return Items(
        description = description,
        endTime = endTime,
        id = id,
        minIncrease = minIncrease,
        pictures = "pictures",
        startTime = startTime,
        startingPrice = startingPrice,
        subCategoryId = subCategoryId,
        title = title,
        userFullName = "userFullName",
        userId = userId
    )
}