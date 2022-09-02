package android.project.auction.data.local.dao

import android.project.auction.data.local.entity.Items
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

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
                LOWER(category_id) LIKE '%' || LOWER(:query) || '%' 
        """
    )
    suspend fun searchCompanyListing(query: String): List<Items>


    @Query(
        """
        SELECT * 
        FROM Items
        WHERE sub_category_id = :subCategoryId 
        AND category_id = :categoryId
        AND LOWER(item_title) LIKE '%' || LOWER(:searchQuery) || '%'
        AND starting_price BETWEEN :firstRange AND :secondRange OR 0
        AND start_time BETWEEN :firstDate AND :secondDate OR 0
    """
    )
    fun getItemWithFilteredCategories(
        subCategoryId: String,
        categoryId: String,
        searchQuery: String,
        firstRange: String,
        secondRange: String,
        firstDate: String,
        secondDate: String
    ): Flow<List<Items>>


}