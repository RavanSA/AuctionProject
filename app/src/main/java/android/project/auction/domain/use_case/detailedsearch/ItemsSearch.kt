package android.project.auction.domain.use_case.detailedsearch

import android.project.auction.data.local.entity.Items
import android.project.auction.domain.model.util.ItemOrder
import android.project.auction.domain.model.util.OrderType
import android.project.auction.domain.repository.AuctionRoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemsSearch @Inject constructor(
    private val repository: AuctionRoomRepository
) {

    operator fun invoke(
        searchQuery: String,
        itemOrder: ItemOrder = ItemOrder.Created(OrderType.Descending),
        categoryId: String,
        subCategoryId: String,
        firstRange: String,
        secondRange: String,
        firstDate: String,
        secondDate: String
    ): Flow<List<Items>> {

        return repository.getItemWithFilteredCategories(
            categoryId = categoryId,
            subCategoryId = subCategoryId,
            searchQuery = searchQuery,
            firstRange = firstRange,
            secondRange = secondRange,
            firstDate = firstDate,
            secondDate = secondDate
        ).map { items ->
            when (itemOrder.orderType) {
                is OrderType.Ascending -> {
                    when (itemOrder) {
                        is ItemOrder.Created -> items.sortedBy { it.startTime }
                        is ItemOrder.StartingPrice -> items.sortedBy { it.startingPrice }
                    }
                }
                is OrderType.Descending -> {
                    when (itemOrder) {
                        is ItemOrder.Created -> items.sortedByDescending { it.startTime }
                        is ItemOrder.StartingPrice -> items.sortedByDescending { it.startingPrice }
                    }
                }
            }
        }
    }
}