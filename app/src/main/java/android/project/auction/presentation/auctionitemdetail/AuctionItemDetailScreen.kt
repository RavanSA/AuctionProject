package android.project.auction.presentation.auctionitemdetail

import android.project.auction.domain.model.item.ItemImages
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auctionitemdetail.components.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.*


@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun AuctionItemDetailScreen(
    navController: NavController,
    auctionItemDetailViewModel: AuctionItemDetailViewModel = hiltViewModel()
) {

    val bottomState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val state = auctionItemDetailViewModel.state

    val highestBidState = auctionItemDetailViewModel.stateHighestBid

    val bidHistoryState = auctionItemDetailViewModel.stateBidHistory

    val itemPictureState = auctionItemDetailViewModel.stateItemPictures

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        auctionItemDetailViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AuctionItemDetailViewModel.ItemDetailUiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AuctionItemDetailViewModel.ItemDetailUiEvent.AddFavoriteItem -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Item Added to Favorites"
                    )
                }

            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Bid History",
                    color = Color.Black,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(5.dp))

                Divider(
                    modifier = Modifier.padding(5.dp),
                    color = Color.Black
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .defaultMinSize(minHeight = 1.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {

                    items(bidHistoryState.bidHistory) { bidHistory ->
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .fillMaxWidth(0.9f),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {
                            BidHistoryUserDetails(bidHistory)
                            if (bidHistoryState.bidHistory[bidHistoryState.bidHistory.size - 1].id != bidHistory.id) {
                                Divider(
                                    modifier = Modifier.padding(3.dp),
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        },
        sheetShape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp
        ),
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = White,
                    title = {
                        Row(
                            modifier = Modifier.padding(15.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("Details")

                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(
                                Screen.AuctionListScreen.route
                            )
                        }) {

                            Icon(Icons.Default.ArrowBack, "Menu")
                        }
                    },
                    actions = {
                        FavoriteButton(
                            scaffoldState = scaffoldState,
                            navController = navController
                        )
                    }
                )
            },
            scaffoldState = scaffoldState,
            content = {

                AuctionDetailContent(
                    state,
                    bidHistoryState,
                    bottomState,
                    navController,
                    highestBidState,
                    itemPictureState
                )

            }
        )
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun AuctionDetailContent(
    state: State<AuctionItemDetailState>,
    bidhistoryState: AuctionItemDetailState,
    bottomState: ModalBottomSheetState,
    navController: NavController,
    highestBidState: AuctionItemDetailState,
    itemPictureState: AuctionItemDetailState
) {

    Column(
        modifier = Modifier
            .background(White)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.size(30.dp))

                NetworkImage(itemPictureState.itemPictures)
                Spacer(modifier = Modifier.size(30.dp))

                Text(
                    text = "${state.value.itemDetails?.title}",
                    color = Color.Black,
                    maxLines = 1,
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(30.dp))

                state.value.itemDetails?.let { AuctionStatusCard(itemDetails = it) }
                Spacer(modifier = Modifier.size(30.dp))

                CurrentBidComponent(highestBidState = highestBidState)

                Spacer(modifier = Modifier.size(20.dp))

                state.value.itemDetails?.description?.let {
                    ItemDetailDescription(
                        itemDetailDescription = it
                    )
                }
                if (bidhistoryState.bidHistory.isNotEmpty()) {
                    BidHistoryCard(modifier = Modifier, bottomState = bottomState)
                }
            }

            itemsIndexed(bidhistoryState.bidHistory) { index, bidhistory ->
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(0.9f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    if (index < 5) {
                        BidHistoryUserDetails(bidhistory)
                    }
                }
            }


            item {
                Text(text = "LAST ELEMENT")
                MoreItems()
            }
        }

        state.value.itemDetails?.let {
            StickyPlaceBidButton(
                navController,
                it,
                state.value.userId,
                highestBidState
            )

        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@ExperimentalPagerApi
@Composable
fun NetworkImage(
    pictures: List<ItemImages>
) {

    val state = rememberPagerState()
    ImageSlider(state = state)
    Spacer(modifier = Modifier.padding(4.dp))
    DotsIndicator(
        totalDots = pictures.size,
        selectedIndex = state.currentPage
    )
}


fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    return sdf.format(Date())
}