package android.project.auction.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SellerOrBidder(
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
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "main_item_picture")
    val mainItemPicture: String = "",
    @ColumnInfo(name = "seller_or_bidder")
    val sellerOrBidder: String = "null",
//    @ColumnInfo(name = "user_id")
//    val userId: String = ""
)
