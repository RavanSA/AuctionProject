package android.project.auction.presentation.auctionitemdetail.components

import android.project.auction.presentation.auctionlist.AuctionListEvent
import android.project.auction.presentation.auctionlist.AuctionListViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter

@Composable
fun MoreItems(
    auctionListViewModel: AuctionListViewModel = hiltViewModel()
) {

    auctionListViewModel.onEvent(
        AuctionListEvent.OnMoreItemClicked
    )

    val item = auctionListViewModel.stateItem

    Column(modifier = Modifier) {
        Text(
            text = "More Items",
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        LazyRow(
            modifier = Modifier.background(Color.White),
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            itemsIndexed(item.item) { index, item ->
                if (index < 8) {
                    Column(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                            .padding(
                                start = 20.dp, top = 5.dp,
                                end = 5.dp, bottom = 5.dp
                            )
                            .clickable {

                            },
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Image(
                            painter = rememberImagePainter(item.mainItemPicture),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(0.dp, 10.dp)
                                .size(200.dp)
                                .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                        )
                    }
                }
            }
        }
    }
}