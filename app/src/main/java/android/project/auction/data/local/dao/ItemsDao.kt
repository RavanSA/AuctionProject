package android.project.auction.data.local.dao

import android.project.auction.data.local.entity.Items
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<Items>)

    @Query("DELETE FROM Items")
    suspend fun clearItemsCache()

    @Query(
        """
            SELECT * 
            FROM Items
            WHERE LOWER(item_title) LIKE '%' || LOWER(:query) || '%' OR
                UPPER(:query) == description
        """
    )
    suspend fun searchCompanyListing(query: String): List<Items>
}