package android.project.auction.presentation.auctionitemdetail

import android.project.auction.R
import android.project.auction.domain.model.bids.Bids
import android.project.auction.domain.model.item.ItemDetail
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auctionlist.components.ProfileImage
import android.project.auction.presentation.auth.sign_in.dpToSp
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


//@Preview
//@Composable
//fun MyViewPreview() {
//    AuctionItemDetailScreen()
//}


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

    Log.d("topıtemfav", state.value.itemAddedFavorite.toString())


    val scaffoldState = rememberScaffoldState()

//    val itemAddToFavorite = mutableStateOf(false)

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
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bid History",
                    color = Color.Black,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Divider(
                    modifier = Modifier.padding(5.dp),
                    color = Color.Black
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .defaultMinSize(minHeight = 1.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
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
                            Divider(
                                modifier = Modifier.padding(3.dp),
                                color = Color.Gray
                            )
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
                            scaffoldState = scaffoldState
                        )
//                        IconButton(onClick = {
//                            auctionItemDetailViewModel.onEvent(
//                                AuctionItemDetailEvent.AddItemToFavorites
//                            )
////                            itemAddToFavorite.value = true
//                        }) {
//
//                            Icon(
//                                Icons.Filled.Favorite,
//                                contentDescription = ""
//                            )
//                        }

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
                    highestBidState
                )

            }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun AuctionDetailContent(
    state: State<AuctionItemDetailState>,
    bidhistoryState: AuctionItemDetailState,
    bottomState: ModalBottomSheetState,
    navController: NavController,
    highestBidState: AuctionItemDetailState
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

                NetworkImage(imageUrl = "")
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
                BidHistoryCard(modifier = Modifier, bottomState = bottomState)
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


@Composable
fun NetworkImage(
    imageUrl: String,
    modifier: Modifier = Modifier
        .size(375.dp)
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
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.9f)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(2.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

            Row(
                modifier = Modifier
                    .padding(2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = itemDetails.endTime,
                    modifier = Modifier

                        .padding(15.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "        Live",
                    modifier = Modifier
                        .padding(15.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 1,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
    }
}


@Composable
fun CurrentBidComponent(
    highestBidState: AuctionItemDetailState
) {


    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.9f)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(2.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = "CurrentBid",
            color = Color.Gray,
            maxLines = 1,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.size(10.dp))


        Text(
            text = "${highestBidState.highestBid?.amount}$",
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.size(5.dp))

        Row(
            modifier = Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImage(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
            Text(
                "@userFullName",
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(15.dp))

        }
        Spacer(modifier = Modifier.size(5.dp))

    }
}

@Composable
fun ItemDetailDescription(
    itemDetailDescription: String
) {

    var showMore by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.9f)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(2.dp)
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween,
        ) {

        Text(
            text = "Description",
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Column(modifier = Modifier
            .animateContentSize(animationSpec = tween(100))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { showMore = !showMore }) {
            if (showMore) {
                Text(
                    text = itemDetailDescription,
                    modifier = Modifier.padding(16.dp),
                    overflow = TextOverflow.Clip,
                    maxLines = 15,
                    color = Color.Black,
                    fontSize = 15.sp
                )
            } else {
                if (itemDetailDescription.length > 20) {
                    Text(
                        text = "$itemDetailDescription....more",
                        modifier = Modifier.padding(16.dp),
                        overflow = TextOverflow.Clip,
                        maxLines = 1,
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                } else {
                    Text(
                        text = itemDetailDescription,
                        modifier = Modifier.padding(16.dp),
                        overflow = TextOverflow.Clip,
                        maxLines = 1,
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}


@Composable
fun StickyPlaceBidButton(
    navController: NavController,
    item: ItemDetail,
    userID: String,
    highestBidder: AuctionItemDetailState
) {

    var enabled by remember { mutableStateOf(true) }
    var buttonTextState by remember { mutableStateOf("") }
    var currentUtcTime = getCurrentDate().split("T").toTypedArray()
    var endTime = item.endTime.split("T").toTypedArray()
    var highestBidAmount = highestBidder.highestBid?.amount
    Log.d("CURRENTUTC", currentUtcTime.toString())
    Log.d("ENDTIME", endTime.toString())

    //	2022-08-20 07:00:00.0000000
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val endTimeParsed: Date = sdf?.parse(endTime[0])
    val utcTimeParsed: Date = sdf?.parse(currentUtcTime[0])
    Log.d("HİGHESTBIDAMOUBT", highestBidAmount.toString())


    when {
        item.userId == userID -> {
            when {
                //+
                utcTimeParsed.after(endTimeParsed) && highestBidAmount != 0.0 -> {
                    //+
                    buttonTextState = "Contact Winner"
                    Log.d("OWNERID", "CONTACT WINNER")
                }

                utcTimeParsed.after(endTimeParsed) && highestBidAmount == 0.0 -> {
                    //+
                    buttonTextState = "Item not sold"
                    enabled = false
                    Log.d("OWNERID", "ITEM NOT SOLD")
                }
                else -> {
                    //+
                    buttonTextState = "Place a bid"
                    enabled = false
                    Log.d("OWNERID", "PLACE A BID")
                }
            }
        }
        highestBidder.highestBid?.userId ?: "" == userID -> {
            when {
                //+
                utcTimeParsed.after(endTimeParsed) -> {
                    buttonTextState = "Contact Seller"
                    Log.d("HIGHESTBIDDER", "CONTACT SELLER")
                }
                endTimeParsed.after(utcTimeParsed) -> {
                    //+
                    buttonTextState = "You're highest bidder"
                    enabled = false
                    Log.d("HIGHESTBIDDER", "YOUREHIGHESRBIDDR")
                }
            }
        }
        utcTimeParsed.after(endTimeParsed) -> {
            buttonTextState = "Auction Ended"
            enabled = false
        }
    }

    Log.d("ITEMID", item.toString())
    Button(
        onClick = {
            navController.navigate(
                Screen.PlaceBidAmountScreen.route + "/${item.id}"
            )
        }, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        ),
        enabled = enabled
    ) {
        Text(
            text = buttonTextState, fontSize = dpToSp(20.dp),
        )
    }

}

@ExperimentalMaterialApi
@Composable
fun BidHistoryCard(
    modifier: Modifier = Modifier,
    bottomState: ModalBottomSheetState
) {

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.9f),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.size(10.dp))

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Bid History",
                color = Color.Gray,
                maxLines = 1,
                modifier = Modifier.padding(start = 14.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Text(
                text = "See all",
                color = Color.Gray,
                maxLines = 1,
                modifier = Modifier
                    .padding(start = 40.dp)
                    .clickable {
                        coroutineScope.launch {
                            bottomState.show()
                        }
                    },
                fontSize = 15.sp
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
    }
}

@Composable
fun BidHistoryUserDetails(
    bids: Bids
) {
    Log.d("BID HISTORY", bids.toString())
    Row(
        modifier = Modifier
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        ProfileImage(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)                       // clip to the circle shape
                .border(2.dp, Color.Gray, CircleShape)
        )
        Column() {
            Text(
                "@" + "userfullname",
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                "" + bids.amount,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}

@Composable
fun FavoriteButton(
    color: Color = Color.White,
    scaffoldState: ScaffoldState,
    auctionItemDetailViewModel: AuctionItemDetailViewModel = hiltViewModel()
) {
    val isAddedToFavorites = auctionItemDetailViewModel.state.value.itemAddedFavorite

    var isFavorite by remember { mutableStateOf(false) }
    var changeColor by remember { mutableStateOf(false) }
    Log.d("FIRSTISFAVORUTE", isAddedToFavorites.toString())
    val scope = rememberCoroutineScope()

    Log.d("TEST2222", isAddedToFavorites.toString())
    isFavorite = isAddedToFavorites != null

    Column(
        modifier = Modifier
            .padding(5.dp)
            .drawBehind {
                drawCircle(
                    color = if (isFavorite || changeColor) Color.Black else Color.White,
                    radius = 60f
                )
            },
    ) {
        IconToggleButton(
            checked = isFavorite,
            onCheckedChange = {
                isFavorite = !isFavorite
            },

            ) {
            Icon(
                tint = if (isFavorite || changeColor) Color.White else Color.Black,
                modifier = Modifier.clickable {
                    if (isFavorite) {
                        Log.d("SECONDISFAVORUTE", isFavorite.toString())

                        auctionItemDetailViewModel.onEvent(
                            AuctionItemDetailEvent.DeleteItem
                        )
                        isFavorite = false
                        changeColor = false
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = "Item deleted from favorites",
                                actionLabel = "Undo"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                auctionItemDetailViewModel.onEvent(
                                    AuctionItemDetailEvent.RestoreItem
                                )
                            }
                        }
                    } else if (!isFavorite) {
                        Log.d("THIRSFAVORITE", isFavorite.toString())

                        auctionItemDetailViewModel.onEvent(
                            AuctionItemDetailEvent.AddItemToFavorites
                        )
                        isFavorite = true
                        changeColor = true
                    }

                },
                imageVector = if (isFavorite || changeColor) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.Favorite

                },
                contentDescription = null
            )
        }
    }
}

fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    return sdf.format(Date())
}