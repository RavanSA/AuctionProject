package android.project.auction.data.local.dao

import android.project.auction.data.local.entity.Favorites
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemToFavorites(fav: Favorites)

    @Delete
    suspend fun deleteItem(fav: Favorites)

    @Query("SELECT * FROM Favorites WHERE user_id =:userId")
    fun getFavoriteItems(userId: String): Flow<List<Favorites>>

    @Query("SELECT * FROM Favorites WHERE item_id=:id AND user_id = :userId")
    suspend fun getItemFromFavoritesById(id: String, userId: String): Favorites?

    @Query("SELECT EXISTS (SELECT 1 FROM Favorites WHERE item_id=:id)")
    suspend fun isItemAddedtoFavorites(id: String): Int?

}