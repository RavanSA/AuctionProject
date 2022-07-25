package android.project.auction.data.local

import android.project.auction.data.local.dao.ItemsDao
import android.project.auction.data.local.entity.Items
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Items::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val itemDao: ItemsDao
}