package android.project.auction.presentation.userprofile.components

import android.project.auction.data.local.entity.SellerOrBidder
import android.project.auction.presentation.userprofile.UserProfileEvent
import android.project.auction.presentation.userprofile.UserProfileViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalUnitApi::class)
@ExperimentalPagerApi
@Composable
fun TabLayout(
    navController: NavController,
    userProfileViewModel: UserProfileViewModel = hiltViewModel()
) {

    val itemList = userProfileViewModel.state.value.auctionList

    val pagerState = rememberPagerState(0)

    val list = listOf(
        "My Auctions",
        "Participated Auctions"
    )

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp)
    ) {
        Tabs(pagerState = pagerState, list = list)
        TabsContent(
            pagerState = pagerState,
            list = list,
            itemList = itemList,
            navController = navController
        )
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

    ) {

        list.forEachIndexed { index, _ ->
            Tab(
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
    list: List<String>,
    itemList: List<SellerOrBidder>,
    navController: NavController,
    userProfileViewModel: UserProfileViewModel = hiltViewModel()
) {

    HorizontalPager(
        state = pagerState,
        count = list.size
    ) {

        when (pagerState.currentPage) {
            0 -> {
                userProfileViewModel.onEvent(
                    UserProfileEvent.OnTabChanged(
                        "owner"
                    )
                )
                TabContentScreen(
                    data = itemList,
                    navController = navController
                )
            }

            1 -> {
                userProfileViewModel.onEvent(
                    UserProfileEvent.OnTabChanged(
                        "bidder"
                    )
                )
                TabContentScreen(
                    data = itemList,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun TabContentScreen(
    data: List<SellerOrBidder>,
    navController: NavController
) {

    LazyColumn(
        contentPadding = PaddingValues(2.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {


        items(data) { item ->

            SellerOrBidderItem(
                items = item,
                navController = navController
            )

        }

        item {
            ProfileSettings()
        }

    }

}