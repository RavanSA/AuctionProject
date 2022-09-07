package android.project.auction.data.local.dao

import android.project.auction.data.local.entity.SellerOrBidder
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface SellerOrBidderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setSellerBidderInfo(items: SellerOrBidder)

//    @Query("UPDATE SellerOrBidder SET seller_or_bidder = :status WHERE item_id = :itemId")
//    suspend fun setSellerBidderInfo(status: String, itemId: String)


}