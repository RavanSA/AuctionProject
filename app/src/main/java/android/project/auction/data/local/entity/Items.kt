package android.project.auction.data.local.entity

import android.project.auction.domain.model.item.Item
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Items(
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "end_time")
    val endTime: String,
    @PrimaryKey
    @ColumnInfo(name = "item_id")
    val id: String,
    @ColumnInfo(name = "minimum_increase")
    val minIncrease: Int,
    @ColumnInfo(name = "pictures")
    val pictures: String = "",
    @ColumnInfo(name = "start_time")
    val startTime: String,
    @ColumnInfo(name = "starting_price")
    val startingPrice: Int,
    @ColumnInfo(name = "sub_category_id")
    val subCategoryId: String,
    @ColumnInfo(name = "category_id")
    val categoryId: String,
    @ColumnInfo(name = "item_title")
    val title: String,
    @ColumnInfo(name = "seller_username")
    val userFullName: String = "",
    @ColumnInfo(name = "seller_user_id")
    val userId: String,
    @ColumnInfo(name = "main_item_picture")
    val mainItemPicture: String = ""
)


fun Items.toItem(): Item {
    return Item(
        description = description,
        endTime = endTime,
        id = id,
        minIncrease = minIncrease,
        pictures = "",
        startTime = startTime,
        startingPrice = startingPrice,
        subCategoryId = subCategoryId,
        categoryId = categoryId,
        title = title,
        userFullName = "userFullName",
        userId = userId,
        mainItemPicture = mainItemPicture
    )
}


