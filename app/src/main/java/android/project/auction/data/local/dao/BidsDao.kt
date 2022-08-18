package android.project.auction.data.local.dao

import android.project.auction.data.local.entity.Bids
import androidx.room.*


@Dao
interface BidsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemBidLocal(bid: Bids)

    @Delete
    suspend fun deleteItemBidLocal(bid: Bids)

    @Query("SELECT itemId, MAX(amount), userId FROM Bids WHERE itemId = :id GROUP BY itemId")
    suspend fun getHighestBidLocal(id: String): Bids

}