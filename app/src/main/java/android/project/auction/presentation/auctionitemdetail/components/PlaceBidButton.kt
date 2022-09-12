package android.project.auction.presentation.auctionitemdetail.components

import android.project.auction.data.local.entity.SellerOrBidder
import android.project.auction.domain.model.item.ItemDetail
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailEvent
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailState
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailViewModel
import android.project.auction.presentation.auctionitemdetail.getCurrentDate
import android.project.auction.presentation.auth.sign_in.dpToSp
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun StickyPlaceBidButton(
    navController: NavController,
    item: ItemDetail,
    userID: String,
    highestBidder: AuctionItemDetailState,
    auctionItemDetailViewModel: AuctionItemDetailViewModel = hiltViewModel()
) {

    var enabled by remember { mutableStateOf(true) }
    var buttonTextState by remember { mutableStateOf("") }
    var currentUtcTime = getCurrentDate().split("T").toTypedArray()
    var endTime = item.endTime.split("T").toTypedArray()
    var highestBidAmount = highestBidder.highestBid?.amount
    Log.d("CURRENTUTC", currentUtcTime.toString())
    Log.d("ENDTIME", endTime.toString())

    //	2022-08-20T07:00:00.0000000
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val endTimeParsed: Date = sdf?.parse(endTime[0])
    val utcTimeParsed: Date = sdf?.parse(currentUtcTime[0])
    Log.d("HİGHESTBIDAMOUBT", highestBidAmount.toString())


    when {
        item.userId == userID -> {

            auctionItemDetailViewModel.onEvent(
                AuctionItemDetailEvent.SellerOrBidderEvent(
                    SellerOrBidder(
                        item.description,
                        item.endTime,
                        item.id,
                        item.minIncrease,
                        item.pictures,
                        item.startTime,
                        item.startingPrice,
                        item.subCategoryId,
                        item.categoryId,
                        item.title,
                        item.userFullName,
                        item.userId,
                        item.mainItemPicture,
                        "owner",
                    )
                )
            )

            when {
                //+
                utcTimeParsed.after(endTimeParsed) && highestBidAmount != 0.0 -> {
                    //+
                    buttonTextState = "Contact Winner"
                    Log.d("OWNERID", "CONTACT WINNER")
                }

                utcTimeParsed.after(endTimeParsed) && highestBidAmount == 0.0 -> {
                    //+
                    buttonTextState = "Item not sold"
                    enabled = false
                    Log.d("OWNERID", "ITEM NOT SOLD")
                }
                else -> {
                    //+
                    buttonTextState = "Place a bid"
                    enabled = false
                    val sellerOrBider = item
                    Log.d("OWNERID", "PLACE A BID")
                }
            }
        }
        highestBidder.highestBid?.userId ?: "" == userID -> {
            when {
                //+
                utcTimeParsed.after(endTimeParsed) -> {
                    buttonTextState = "Contact Seller"
                    Log.d("HIGHESTBIDDER", "CONTACT SELLER")
                }
                endTimeParsed.after(utcTimeParsed) -> {
                    //+
                    buttonTextState = "You're highest bidder"
                    enabled = false

                    Log.d("HIGHESTBIDDER", "YOUREHIGHESRBIDDR")
                }
            }
        }
        utcTimeParsed.after(endTimeParsed) -> {
            buttonTextState = "Auction Ended"
            enabled = false
        }
    }

    Log.d("ITEMID", item.toString())
    Button(
        onClick = {

            navController.currentBackStackEntry?.savedStateHandle?.set(
                key = "itemDetails",
                value = item
            )

            navController.navigate(
                Screen.PlaceBidAmountScreen.route
            )
        }, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        ),
        enabled = enabled
    ) {
        Text(
            text = buttonTextState, fontSize = dpToSp(20.dp),
        )
    }

}