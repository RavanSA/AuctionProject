package android.project.auction.data.remote.dto.items.itemdetail

import android.project.auction.domain.model.item.ItemDetail

data class ItemDetailDataDto(
    val categoryId: String,
    val description: String,
    val endTime: String,
    val id: String,
    val minIncrease: Int,
    val pictures: List<Any>,
    val startTime: String,
    val startingPrice: Int,
    val subCategoryId: String,
    val subCategoryName: String,
    val title: String,
    val userFullName: String,
    val userId: String
)

fun ItemDetailDataDto.toItemDetail(): ItemDetail {
    return ItemDetail(
        description = description,
        endTime = endTime,
        id = id,
        minIncrease = minIncrease,
        pictures = "pictures",
        startTime = startTime,
        startingPrice = startingPrice,
        subCategoryId = subCategoryId,
        categoryId = categoryId,
        title = title,
        userFullName = "userFullName",
        userId = userId,
        subCategoryName = subCategoryName
    )
}
