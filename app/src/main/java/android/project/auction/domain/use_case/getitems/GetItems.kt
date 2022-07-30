package android.project.auction.domain.use_case.getitems

import android.project.auction.common.Resource
import android.project.auction.data.local.AppDatabase
import android.project.auction.data.local.entity.toItem
import android.project.auction.data.remote.dto.items.getitems.toItems
import android.project.auction.domain.model.item.Item
import android.project.auction.domain.repository.AuctionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetItems @Inject constructor(
    private val repository: AuctionRepository,
    private val appDatabase: AppDatabase
) {

    private val itemDao = appDatabase.itemDao

    operator fun invoke(
        fetchItemListFromRemote: Boolean,
        searchQuery: String
    ): Flow<Resource<List<Item>>> = flow {
        try {
            emit(Resource.Loading<List<Item>>(true))
            val localItemList = itemDao.searchCompanyListing(searchQuery)

            emit(Resource.Success(
                data = localItemList.map { it.toItem() }
            ))

            val isItemTableEmpty = localItemList.isEmpty() && searchQuery.isBlank()
            val loadFromCache = !isItemTableEmpty && !fetchItemListFromRemote


            if (loadFromCache) {
                emit(Resource.Loading<List<Item>>(false))
                return@flow
            }

            val remoteItemList = try {
                repository.getItems().data
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteItemList?.let { items ->
                itemDao.clearItemsCache()
                itemDao.insertItems(
                    items.map { it.toItems() }
                )
            }

            emit(Resource.Success<List<Item>>(itemDao.searchCompanyListing("").map { it.toItem() }))
            emit(Resource.Loading<List<Item>>(false))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Item>>(e.localizedMessage ?: "An unexpected error ocured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Item>>("Couldn't reach server. Check your internet connection."))
        }
    }

}