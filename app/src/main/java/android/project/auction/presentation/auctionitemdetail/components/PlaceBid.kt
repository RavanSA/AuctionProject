package android.project.auction.presentation.auctionitemdetail.components

import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailEvent
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailViewModel
import android.project.auction.presentation.auth.sign_in.dpToSp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PlaceBid(
    itemId: String,
    amount: String
) {

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
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Clear, "Menu")
                    }
                },
                actions = {

                }
            )
        },
        content = {

        }
    )

}

@Composable
fun PlaceBidContent(
    auctionItemDetailViewModel: AuctionItemDetailViewModel = hiltViewModel()
) {

    val state = auctionItemDetailViewModel.statePlaceBid

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

            OutlinedTextField(
                value = state.bidAmount.toString(),
                onValueChange = {
                    auctionItemDetailViewModel.onEvent(
                        AuctionItemDetailEvent.BidAmountChanged(amount = it.toInt())
                    )
                },
                label = { Text(text = "Amount", color = Color.Black) },
                placeholder = { Text(text = "Bid Amount", color = Color.Black) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )


            Button(
                onClick = {
                    auctionItemDetailViewModel.onEvent(
                        AuctionItemDetailEvent.OnBidAmountPlaced
                    )
                }, modifier = Modifier
                    .fillMaxWidth(0.8f)
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