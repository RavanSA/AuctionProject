package android.project.auction.domain.use_case

import android.project.auction.domain.use_case.createitem.CreateItem
import android.project.auction.domain.use_case.getbidhistory.GetBidHistory
import android.project.auction.domain.use_case.getcategories.GetCategories
import android.project.auction.domain.use_case.getcategories.GetSubCategories
import android.project.auction.domain.use_case.gethighestbid.GetHighestBid
import android.project.auction.domain.use_case.getitemdetail.GetItemDetail
import android.project.auction.domain.use_case.getitems.GetItems
import android.project.auction.domain.use_case.placebidamount.PlaceBidAmount

data class AuctionProjectUseCase(
    val getCategories: GetCategories,
    val getItems: GetItems,
    val getItemDetail: GetItemDetail,
    val getBidHistory: GetBidHistory,
    val placeBidAmount: PlaceBidAmount,
    val getSubCategories: GetSubCategories,
    val createItem: CreateItem,
    val getHighestBid: GetHighestBid
)
