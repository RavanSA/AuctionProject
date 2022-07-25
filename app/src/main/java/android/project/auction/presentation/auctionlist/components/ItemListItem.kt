package android.project.auction.presentation.auctionlist.components

import android.project.auction.R
import android.project.auction.domain.model.item.Item
import android.project.auction.presentation.auth.sign_in.dpToSp
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ItemListItem(
    item: Item,
    onItemClick: (Item) -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(Color.White)
            .border(1.dp, Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = item.title,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
    }
    Log.d("TESTITEMNAME", item.title)
}


@Composable
fun InspirationItem(
    item: Item,
) {
    Surface(
        modifier = Modifier,
        shape = MaterialTheme.shapes.small,
        color = Color.White,
        contentColor = MaterialTheme.colors.onSurface,
        border = BorderStroke(2.dp, MaterialTheme.colors.primary)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            NetworkImage(
                ""
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth(), content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Spacer(modifier = Modifier.size(10.dp))

                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(Icons.Filled.PlayArrow, "Live")
                            Text(
                                " Live", fontSize = 15.sp, color = Color.Black, maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = Bold
                            )

                        }
                        Spacer(modifier = Modifier.size(15.dp))
                        ProfileImage()
                        Text(
                            "@" + item.userFullName,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.size(15.dp))

                        Text(
                            "Current Bid",
                            fontSize = 15.sp,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            item.startingPrice.toString() + "$",
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.size(15.dp))

                        Button(
                            onClick = {
                            }, modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(40.dp)
                        ) {
                            Text(text = "BID", fontSize = dpToSp(10.dp), color = Color.White)
                        }
                    }
                })
        }
    }
}


@Composable
fun NetworkImage(
    imageUrl: String,
    modifier: Modifier = Modifier.size(250.dp),
    contentScale: ContentScale = ContentScale.Fit,
    fadeIn: Boolean = true,
    @DrawableRes previewPlaceholder: Int = 0
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        GlideImage(
            imageModel = "https://developers.google.com/static/maps/documentation/streetview/images/error-image-generic.png",
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Fit,
            // shows a placeholder while loading the image.
            placeHolder = ImageBitmap.imageResource(R.drawable.login_image),
            // shows an error ImageBitmap when the request failed.
            error = ImageBitmap.imageResource(R.drawable.register_page)
        )
    }
}

@Composable
fun ProfileImage() {
    Image(
        painter = painterResource(R.drawable.sample_avatar),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,            // crop the image if it's not a square
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)                       // clip to the circle shape
            .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
    )
}