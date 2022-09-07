package android.project.auction.presentation.userprofile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalUnitApi::class)
@ExperimentalPagerApi
@Composable
fun TabLayout() {

    val pagerState = rememberPagerState()

    val list = listOf(
        "My Auctions",
        "Participated Auctions",
        "My Last Auctions",
        "Ended Participated Auctions"
    )

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp)
    ) {
        Tabs(pagerState = pagerState, list = list)
        TabsContent(pagerState = pagerState, list = list)
    }

}


@ExperimentalPagerApi
@Composable
fun Tabs(
    pagerState: PagerState,
    list: List<String>
) {

    val scope = rememberCoroutineScope()

    ScrollableTabRow(

        selectedTabIndex = pagerState.currentPage,

        modifier = Modifier
            .clip(shape = RoundedCornerShape(20.dp)),

        backgroundColor = Color.Black,

        contentColor = Color.White,


//        indicator = { tabPositions ->
//
//            TabRowDefaults.Indicator(
//                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
//                height = 2.dp,
//                color = Color.White
//            )
//        }
    ) {

        list.forEachIndexed { index, _ ->
            Tab(

//                icon = {
//                    Icon(imageVector = list[index], contentDescription = null)
//                },

                text = {
                    Text(
                        list[index],

                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },

                selected = pagerState.currentPage == index,

                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState,
    list: List<String>
) {

    HorizontalPager(
        state = pagerState,
        count = list.size
    ) { page ->
        when (page) {

            0 -> TabContentScreen(data = "Welcome to Home Screen")

            1 -> TabContentScreen(data = "Welcome to Shopping Screen")

            2 -> TabContentScreen(data = "Welcome to Settings Screen")
        }
    }
}


@Composable
fun TabContentScreen(data: String) {
    Column(

        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = data,

            style = MaterialTheme.typography.h5,
            color = Color.Black,

            fontWeight = FontWeight.Bold,

            textAlign = TextAlign.Center
        )
    }
}