package android.project.auction.presentation.auctionitemdetail.components

import android.project.auction.R
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun ImageSlider(
    state: PagerState,
    viewModel: AuctionItemDetailViewModel = hiltViewModel()
) {


    val images = viewModel.stateItemPictures.itemPictures


    val imageUrl = remember { mutableStateOf("") }
    images?.size?.let {
        HorizontalPager(
            state = state, count = it, modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) { page ->

            images?.let {
                imageUrl.value = it[page].url
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.BottomCenter) {

                    val painter = rememberImagePainter(data = imageUrl.value, builder = {
                        placeholder(R.drawable.image_placeholder)
                        scale(coil.size.Scale.FILL)
                    })
                    Image(
                        painter = painter,
                        contentDescription = "",
                        Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}


