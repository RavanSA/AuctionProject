package android.project.auction.presentation.auctionlist.components

import android.project.auction.R
import android.project.auction.domain.model.item.Item
import android.project.auction.presentation.auctionitemdetail.getCurrentDate
import android.project.auction.presentation.auth.sign_in.dpToSp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.skydoves.landscapist.glide.GlideImage
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun ItemList(
    item: Item,
    onItemClick: (Item) -> Unit
) {

    val logoAnimationComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            resId = R.raw.live_icon_black
        )
    )

    val progress by animateLottieCompositionAsState(
        composition = logoAnimationComposition,
        iterations = LottieConstants.IterateForever
    )


    var currentUtcTime = getCurrentDate().split("T").toTypedArray()
    var endTime = item.endTime.split("T").toTypedArray()

    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val endTimeParsed: Date = sdf.parse(endTime[0])
    val utcTimeParsed: Date = sdf.parse(currentUtcTime[0])

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
                        Spacer(modifier = Modifier.size(0.dp))

                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center
                            ) {
                                when {
                                    endTimeParsed.after(utcTimeParsed) -> {
                                        //+
                                        LottieAnimation(
                                            modifier = Modifier.size(
                                                size = 50.dp
                                            ),
                                            composition = logoAnimationComposition,
                                            progress = { progress }
                                        )
                                    }
                                    utcTimeParsed.after(endTimeParsed) -> {
                                        Text(
                                            "Ended", fontSize = 15.sp, color = Color.Black,
                                            maxLines = 1, overflow = TextOverflow.Ellipsis,
                                            fontWeight = Bold
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(0.dp))
                        ProfileImage(item = item)

                        Spacer(modifier = Modifier.size(15.dp))

                        Text(
                            "Starting Price",
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
                                .height(30.dp),
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
    imageUrl: String,
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
        .size(80.dp)
        .clip(CircleShape)
        .border(2.dp, Color.Gray, CircleShape),
    item: Item
) {

    val imageUrl =
        "https://firebasestorage.googleapis.com/v0/b/auctionproject-72b40.appspot.com/o/${item.userId}?alt=media"

    GlideImage(
        modifier = modifier,
        imageModel = imageUrl,
        contentScale = ContentScale.Fit,
        placeHolder = ImageBitmap.imageResource(R.drawable.image_placeholder),
        error = ImageBitmap.imageResource(R.drawable.image_placeholder)
    )

}