package android.project.auction.data.local

import android.project.auction.data.local.dao.BidsDao
import android.project.auction.data.local.dao.FavoritesDao
import android.project.auction.data.local.dao.ItemsDao
import android.project.auction.data.local.entity.Bids
import android.project.auction.data.local.entity.Favorites
import android.project.auction.data.local.entity.Items
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Items::class, Favorites::class, Bids::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract val itemDao: ItemsDao
    abstract val favDao: FavoritesDao
    abstract val bidDao: BidsDao
}