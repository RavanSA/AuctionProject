package android.project.auction.presentation.auctionitemdetail.components

import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailState
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CurrentBidComponent(
    highestBidState: AuctionItemDetailState,
    auctionItemDetailViewModel: AuctionItemDetailViewModel = hiltViewModel()
) {

    val itemDetails = auctionItemDetailViewModel.state.value.itemDetails

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.9f)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(2.dp)
            )
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            Column() {


                Text(
                    text = "CurrentBid",
                    color = Color.Black,
                    maxLines = 1,
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(10.dp))


                Text(
                    text = "${highestBidState.highestBid?.amount}$",
                    color = Color.Black,
                    maxLines = 1,
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(5.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            Column() {


                Text(
                    text = "Starting Price",
                    color = Color.Black,
                    maxLines = 1,
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = "${itemDetails?.startingPrice}$",
                    color = Color.Black,
                    maxLines = 1,
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(5.dp))
            }


            Spacer(modifier = Modifier.size(5.dp))

        }
    }
}