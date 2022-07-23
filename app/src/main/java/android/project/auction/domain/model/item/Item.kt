package android.project.auction.domain.model.item

import android.project.auction.data.remote.dto.items.Picture

data class Item(
    val description: String,
    val endTime: String,
    val id: String,
    val minIncrease: Int,
    val pictures: List<Picture>,
    val startTime: String,
    val startingPrice: Int,
    val subCategoryId: String,
    val title: String,
    val userFullName: String,
    val userId: String
)
