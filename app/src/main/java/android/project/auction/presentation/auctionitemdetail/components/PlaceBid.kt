package android.project.auction.presentation.auctionitemdetail.components

import android.project.auction.common.AuthResult
import android.project.auction.data.local.entity.SellerOrBidder
import android.project.auction.domain.model.item.ItemDetail
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailEvent
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailViewModel
import android.project.auction.presentation.auth.AuthViewModel
import android.project.auction.presentation.auth.sign_in.dpToSp
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController

@Composable
fun PlaceBid(
    savedStateHandle: SavedStateHandle,
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
    itemDetail: ItemDetail,
) {


    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val context = LocalContext.current

    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { authResult ->
            when (authResult) {
                is AuthResult.UnAuthorized -> {
                    navController.navigate(Screen.LoginScreen.route)
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "UNKNOWN ERROR OCCURED",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }



    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Row(
                        modifier = Modifier.padding(15.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Place a Bid")

                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackPressedDispatcher?.onBackPressed()
                    }) {
                        Icon(Icons.Default.Clear, "Menu")
                    }
                },
                actions = {

                }
            )
        },
        content = {
            PlaceBidContent(itemDetail, navController)
        }
    )
}


@Composable
fun PlaceBidContent(
    item: ItemDetail,
    navController: NavController,
    auctionItemDetailViewModel: AuctionItemDetailViewModel = hiltViewModel()
) {

    auctionItemDetailViewModel.onEvent(
        AuctionItemDetailEvent.OnPlaceBidScreen(
            item.id
        )
    )

    val state = auctionItemDetailViewModel.statePlaceBid
    val highestBid = auctionItemDetailViewModel.stateHighestBid.highestBid?.amount ?: 0.0

    var bidError by remember { mutableStateOf("") }
    var enabled by remember { mutableStateOf(true) }
    var bidAmount by remember { mutableStateOf(0.0) }
    Log.d("HIGHEST BID", auctionItemDetailViewModel.stateHighestBid.highestBid.toString())

    bidAmount = if (state.bidAmount != "") {
        state.bidAmount.toDouble()
    } else {
        0.0
    }

    if (bidAmount < item.minIncrease && state.bidAmount != "") {
        bidError =
            "Bid amount cannot be less than the minimum increase. Minimum increase is ${item.minIncrease}"
        enabled = false
    } else if (bidAmount < highestBid && state.bidAmount != "") {
        bidError = "Bid amount cannot be less than the highest bid. Highest bid is $highestBid"
        enabled = false
    } else {
        bidError = ""
        enabled = true
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Place bid amount ",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = dpToSp(2.dp)
                ),
                fontSize = dpToSp(30.dp)
            )
            Log.d("STATEBIDAMOUNT", state.bidAmount)
            OutlinedTextField(
                value = state.bidAmount,
                onValueChange = {
                    auctionItemDetailViewModel.onEvent(
                        AuctionItemDetailEvent.BidAmountChanged(amount = it, itemId = item.id)
                    )
                },
                label = { Text(text = "Amount", color = Color.Black) },
                placeholder = { Text(text = "Bid Amount", color = Color.Black) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.9f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.size(5.dp))

            Text(
                text = bidError,
                modifier = Modifier.padding(10.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = dpToSp(2.dp)
                ),
                fontSize = 10.sp,
                color = Color.Red
            )

            Spacer(modifier = Modifier.size(10.dp))

            Button(
                enabled = enabled,
                onClick = {

                    auctionItemDetailViewModel.onEvent(
                        AuctionItemDetailEvent.OnBidAmountPlaced(
                            item.id
                        )
                    )

                    auctionItemDetailViewModel.setSellerOrBidder(
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
                            "bidder",
                        )
                    )

                    navController.navigate(
                        Screen.AuctionItemDetailScreen.route + "/${item.id}"
                    )


                }, modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Place Bid", fontSize = dpToSp(20.dp))
            }
        }
    }
}

