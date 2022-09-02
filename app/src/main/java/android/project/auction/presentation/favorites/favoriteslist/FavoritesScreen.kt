package android.project.auction.presentation.favorites.favoriteslist

import android.project.auction.R
import android.project.auction.data.local.entity.Favorites
import android.project.auction.presentation.Screen
import android.project.auction.presentation.ui.common.bottomNav.BottomNav
import android.project.auction.presentation.ui.common.topBar.TopBar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.collectLatest

@ExperimentalFoundationApi
@Composable
fun FavoritesScreen(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        favoritesViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is FavoritesViewModel.FavoriteItemUiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = "Item deleted from favorites",
                        actionLabel = "Undo"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        favoritesViewModel.onEvent(FavoritesEvent.RestoreItem)
                    }
                }
            }
        }
    }


    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(Color.White)
            ) {
                TopBar(
                    title = "Auction",
                    icon = Icons.Default.Menu,
                    onNavigationIconClick = {

                    },
                    leftIcon = Icons.Default.Add
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(65.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                    .background(Color.White),
                cutoutShape = CircleShape,
                elevation = 22.dp,
                backgroundColor = Color.White
            ) {
                BottomNav(navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    navController.navigate(
                        Screen.PostItemScreen.route
                    )
                },
                contentColor = Color.White,
                backgroundColor = Color.Black
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add icon")
            }
        },
        content = {
            FavoritesScreenBody(
                favoritesViewModel = favoritesViewModel,
                navController = navController,
                scaffoldState = scaffoldState
            )
        }

    )

}

@ExperimentalFoundationApi
@Composable
fun FavoritesScreenBody(
    favoritesViewModel: FavoritesViewModel,
    navController: NavController,
    scaffoldState: ScaffoldState
) {


    val state = favoritesViewModel.state.value

    var count = 0
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        item {

        }

        items(0) {

        }

        item {
            val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp / 2


            FlowRow(
                mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
            ) {
                for (fav in state.favoritesItems) {
                    count++
                    Column(
                        modifier = Modifier

                    ) {
                        FavoritesScreenItem(
                            fav = fav,
                            scaffoldState = scaffoldState,
                            navController = navController,
                            state = state
                        )
                    }
                    if (count == state.favoritesItems.size) {
                        Spacer(modifier = Modifier.size(100.dp))
                    }
                }
            }
        }
    }
}


@Composable
fun FavoritesScreenItem(
    fav: Favorites,
    scaffoldState: ScaffoldState,
    navController: NavController,
    state: FavoritesState
) {
    Card(
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
            .background(Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(5.dp)
                )
                .clickable {
                    navController.navigate(
                        Screen.AuctionItemDetailScreen.route + "/${fav.id}"
                    )
                }
        ) {

            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                GlideImage(
                    modifier = Modifier
                        .height(130.dp)
                        .shadow(
                            elevation = 3.dp,
                            shape = RoundedCornerShape(0.dp)
                        ),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    imageModel = fav.pictures,
                    placeHolder = ImageBitmap.imageResource(R.drawable.login_image),
                    error = ImageBitmap.imageResource(R.drawable.register_page)
                )

                FavoriteButton(
                    modifier = Modifier.padding(12.dp),
                    scaffoldState = scaffoldState,
                    fav = fav
                )

            }

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp, top = 4.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = fav.startingPrice.toString() + "$",
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 0.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 15.sp,
                )
                Text(
                    text = fav.title,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 0.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 13.sp
                )
                Text(
                    text = fav.startTime,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 0.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 13.sp
                )
            }
        }
    }
}


@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    scaffoldState: ScaffoldState,
    favoritesScreenViewModel: FavoritesViewModel = hiltViewModel(),
    fav: Favorites
) {

    var isFavorite by remember { mutableStateOf(true) }

    val scope = rememberCoroutineScope()


    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        },

        ) {
        Icon(
            tint = color,
            modifier = Modifier.clickable {
                favoritesScreenViewModel.onEvent(
                    FavoritesEvent.DeleteItem(fav = fav)
                )
            },
            imageVector = Icons.Filled.Favorite,
            contentDescription = null
        )
    }

}


