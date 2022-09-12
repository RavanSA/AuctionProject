package android.project.auction.domain.use_case.usecases

import android.project.auction.domain.use_case.adddeletefavorites.*
import android.project.auction.domain.use_case.createitem.CreateItem
import android.project.auction.domain.use_case.createitem.SellerOrBidder
import android.project.auction.domain.use_case.detailedsearch.ItemsSearch
import android.project.auction.domain.use_case.getbidhistory.GetBidHistory
import android.project.auction.domain.use_case.getcategories.GetCategories
import android.project.auction.domain.use_case.getcategories.GetSubCategories
import android.project.auction.domain.use_case.gethighestbid.GetHighestBid
import android.project.auction.domain.use_case.getitemdetail.GetItemDetail
import android.project.auction.domain.use_case.getitemdetail.GetItemPicture
import android.project.auction.domain.use_case.getitems.GetAuctionsForSellerOrBidder
import android.project.auction.domain.use_case.getitems.GetItems
import android.project.auction.domain.use_case.getuserinfobyid.GetUserInfoById
import android.project.auction.domain.use_case.placebidamount.PlaceBidAmount

data class AuctionProjectUseCase(
    val getCategories: GetCategories,
    val getItems: GetItems,
    val getItemDetail: GetItemDetail,
    val getBidHistory: GetBidHistory,
    val placeBidAmount: PlaceBidAmount,
    val getSubCategories: GetSubCategories,
    val createItem: CreateItem,
    val getHighestBid: GetHighestBid,
    val addFavoriteItem: AddFavoriteItem,
    val deleteFavoriteItem: DeleteFavoriteItem,
    val getFavoriteItems: GetFavoriteItems,
    val getFavoriteItemById: GetFavoriteItemById,
    val addItemBids: AddItemBids,
    val getHighestBidLocal: GetHighestBidLocal,
    val getItemPictures: GetItemPicture,
    val getItemWithFilterCategories: ItemsSearch,
    val sellerOrBidder: SellerOrBidder,
    val getSellerOrBidderList: GetAuctionsForSellerOrBidder,
    val getUserInfoById: GetUserInfoById
)
