package android.project.auction.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bids(
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "created")
    val created: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "itemId")
    val itemId: String,
    @ColumnInfo(name = "userId")
    val userId: String
)
