package android.project.auction.data.local.dao

import android.project.auction.data.local.entity.SellerOrBidder
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SellerOrBidderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setSellerBidderInfo(items: SellerOrBidder)

    @Query("SELECT * FROM SellerOrBidder WHERE seller_or_bidder = :sellerOrBidder")
    fun getSellerOrBidderAuctions(sellerOrBidder: String): Flow<List<SellerOrBidder>>

}