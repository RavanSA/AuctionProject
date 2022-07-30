package android.project.auction.presentation.auctionitemdetail

import android.project.auction.R
import android.project.auction.domain.model.item.ItemDetail
import android.project.auction.presentation.ui.theme.TextWhite
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage


//@Preview
//@Composable
//fun MyViewPreview() {
//    AuctionItemDetailScreen()
//}


@Composable
fun AuctionItemDetailScreen(
    navController: NavController,
    auctionItemDetailViewModel: AuctionItemDetailViewModel = hiltViewModel()
) {

    val state = auctionItemDetailViewModel.state


    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = TextWhite,
                title = {
                    Row(
                        modifier = Modifier.padding(15.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Details")

                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ArrowBack, "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Favorite, "Menu")
                    }
                }
            )
        },
        content = {
            AuctionDetailContent(state)
        }
    )
}


@Composable
fun AuctionDetailContent(
    state: State<AuctionItemDetailState>
) {
    Surface(
        modifier = Modifier
            .background(TextWhite),
        shape = MaterialTheme.shapes.small,
        color = TextWhite,
        contentColor = TextWhite
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            NetworkImage(imageUrl = "")

            Text(text = "${state.value.itemDetails?.title}")

//            state.value.itemDetails?.let { AuctionStatusCard(itemDetails = it) }
//
//            Text(text = "Current Bid")
//            Text(text = "${state.value.itemDetails?.startingPrice}")

        }
    }
}

@Composable
fun NetworkImage(
    imageUrl: String,
    modifier: Modifier = Modifier
        .size(300.dp)
        .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)),
    contentScale: ContentScale = ContentScale.Fit,
    fadeIn: Boolean = true,
    @DrawableRes previewPlaceholder: Int = 0
) {
    Column(
        modifier = modifier,
    ) {

        GlideImage(
            imageModel = "https://developers.google.com/static/maps/documentation/streetview/images/error-image-generic.png",
            contentScale = ContentScale.Fit,
            placeHolder = ImageBitmap.imageResource(R.drawable.login_image),
            error = ImageBitmap.imageResource(R.drawable.register_page)
        )
    }
}


@Composable
fun AuctionStatusCard(
    itemDetails: ItemDetail
) {
    Column(

    ) {
        Card(
            modifier = Modifier.background(Color.White),
        ) {
            Row(
                modifier = Modifier
                    .padding(2.dp)
                    .background(TextWhite),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = itemDetails.startTime,
                    modifier = Modifier
                        .background(
                            color = TextWhite,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(15.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1
                )

                Text(
                    text = "Live",
                    modifier = Modifier
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(15.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1
                )
            }
        }
    }
}