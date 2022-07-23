package android.project.auction.domain.use_case

import android.project.auction.domain.use_case.getcategories.GetCategories
import android.project.auction.domain.use_case.getitems.GetItems

data class AuctionProjectUseCase(
    val getCategories: GetCategories,
    val getItems: GetItems
)
