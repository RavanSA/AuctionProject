package android.project.auction.data.local.entity

import androidx.room.Entity

@Entity
data class Bids(
    val amount: Double,
    val created: String,
    val id: String,
    val itemId: String,
    val userId: String
)
