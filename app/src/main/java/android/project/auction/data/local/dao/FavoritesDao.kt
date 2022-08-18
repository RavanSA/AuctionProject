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

    @Query("SELECT * FROM Favorites")
    fun getFavoriteItems(): Flow<List<Favorites>>

    @Query("SELECT * FROM Favorites WHERE item_id=:id")
    suspend fun getItemFromFavoritesById(id: String): Favorites?

}