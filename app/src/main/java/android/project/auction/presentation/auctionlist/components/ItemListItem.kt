package android.project.auction.presentation.auctionlist.components

import android.project.auction.R
import android.project.auction.domain.model.item.Item
import android.project.auction.presentation.auth.sign_in.dpToSp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Podcasts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun ItemList(
    item: Item,
    onItemClick: (Item) -> Unit
) {
    Surface(
        modifier = Modifier
            .background(Color.White)
            .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
            .clickable { onItemClick(item) },
        shape = MaterialTheme.shapes.small,
        color = Color.White,
        contentColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier
                    .size(250.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)),
            ) {
                Box(

                ) {
                    NetworkImage(
                        item.mainItemPicture
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black
                                    ),
                                    startY = 400f
                                )
                            )
                    )

                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        Text(item.title, style = TextStyle(color = Color.White, fontSize = 16.sp))
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.13f)
                            .fillMaxWidth(0.5f)
                            .align(Alignment.TopStart)
                    ) {
//                        Text(item.title, style = TextStyle(color = Color.White, fontSize = 16.sp))
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .background(Color.White)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .fillMaxSize()
                    .padding(10.dp), content = {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Spacer(modifier = Modifier.size(10.dp))

                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                Icons.Default.Podcasts, "Live", modifier = Modifier.background(
                                    Color.Black
                                )
                            )
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
                        Spacer(modifier = Modifier.size(12.dp))

                        Button(
                            onClick = {
                            }, modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Black,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "BID", fontSize = dpToSp(13.dp), color = Color.White)
                        }
                    }
                })
        }
    }
}


@Composable
fun NetworkImage(
    imageUrl: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        GlideImage(
            imageModel = imageUrl,
            contentScale = ContentScale.Crop,
            placeHolder = ImageBitmap.imageResource(R.drawable.image_placeholder),
            error = ImageBitmap.imageResource(R.drawable.image_placeholder)
        )
    }
}

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier
        .size(64.dp)
        .clip(CircleShape)                       // clip to the circle shape
        .border(2.dp, Color.Gray, CircleShape)
) {
    Image(
        painter = painterResource(R.drawable.sample_avatar),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,            // crop the image if it's not a square
        modifier = modifier  // add a border (optional)
    )
}