package android.project.auction.presentation.userprofile.components

import android.project.auction.R
import android.project.auction.data.local.entity.SellerOrBidder
import android.project.auction.presentation.Screen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SellerOrBidderItem(
    items: SellerOrBidder,
    navController: NavController
) {

    Surface(
        modifier = Modifier
            .background(Color.White)
            .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
            .shadow(1.dp),
        shape = MaterialTheme.shapes.small,
        color = Color.White,
        contentColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                .shadow(1.dp)
                .clickable {
                    navController.navigate(
                        Screen.AuctionItemDetailScreen.route + "/${items.id}"
                    )
                },
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .padding(5.dp),
                horizontalAlignment = Alignment.Start,

                ) {

                ItemImage(imageUrl = items.mainItemPicture)
            }
            Column(
                modifier = Modifier
                    .padding(10.dp)

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {


                    Text(
                        items.title,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 5.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        "Live",
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(top = 5.dp)
                ) {


                    Text(
                        items.description,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 5.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        "${items.startingPrice}$",
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }

            }

        }
    }

}


@Composable
fun ItemImage(
    imageUrl: String
) {


    GlideImage(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp)),
        imageModel = imageUrl,
        contentScale = ContentScale.FillBounds,
        placeHolder = ImageBitmap.imageResource(R.drawable.image_placeholder),
        error = ImageBitmap.imageResource(R.drawable.image_placeholder)
    )

}