package android.project.auction.presentation.auctionlist

import android.annotation.SuppressLint
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auctionlist.components.CategoriesListItem
import android.project.auction.presentation.auctionlist.components.ItemList
import android.project.auction.presentation.auctionlist.components.SearchBar
import android.project.auction.presentation.auth.AuthViewModel
import android.project.auction.presentation.ui.common.LoadingScreen
import android.project.auction.presentation.ui.common.bottomNav.BottomNav
import android.project.auction.presentation.ui.common.navDrawer.DrawerList
import android.project.auction.presentation.ui.common.navDrawer.PushScreen
import android.project.auction.presentation.ui.common.topBar.TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalMaterialApi
@Composable
fun AuctionListScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
    auctionViewModel: AuctionListViewModel = hiltViewModel()
) {

    if (auctionViewModel.state.value.isItemLoading
        || auctionViewModel.stateItem.isCategoriesLoading
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            LoadingScreen()
        }
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scaffoldState = rememberScaffoldState(
        drawerState = drawerState
    )
    val scope: CoroutineScope = rememberCoroutineScope()
    val myShape = customShape()
    val widthDp = myShape.leftSpaceWidth?.let { pxToDp(it) }
//    LaunchedEffect(viewModel, context) {
//        viewModel.authResults.collect { authResult ->
//            when (authResult) {
//                is AuthResult.UnAuthorized -> {
//                    navController.navigate(Screen.LoginScreen.route)
//                }
//                is AuthResult.UnknownError -> {
//                    Toast.makeText(
//                        context,
//                        "UNKNOWN ERROR OCCURED",
//                        Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(White)
            ) {
                TopBar(
                    title = "Auction",
                    icon = Icons.Default.Menu,
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                    leftIcon = Icons.Default.Add
                )
                SearchBar()
            }
        },
        drawerContent = {
             DrawerList(navController, auctionViewModel.userInfoState.userInfo)
//        DrawerHeader()
//        DrawerBody(
//            items = listOf(
//            NavDrawerItem(
//                id = "login",
//                title = "Login",
//                contentDescription = "Go to Home Screen",
//                icon = Icons.Default.Home
//            ),
//            NavDrawerItem(
//                id = "myauction",
//                title = "My Auctions",
//                contentDescription = "Go to Settings Screen",
//                icon = Icons.Default.AdminPanelSettings
//            ),
//                NavDrawerItem(
//                    id = "addauction",
//                    title = "Add Auction",
//                    contentDescription = "Go to Help Screen",
//                    icon = Icons.Default.Add
//                ),
//                NavDrawerItem(
//                    id = "contactus",
//                    title = "Contact Us",
//                    contentDescription = "Go to Help Screen",
//                    icon = Icons.Default.Contacts
//                ),
//                NavDrawerItem(
//                    id = "language",
//                    title = "Language",
//                    contentDescription = "Go to Help Screen",
//                    icon = Icons.Default.Language
//                ),
//                NavDrawerItem(
//                    id = "help",
//                    title = "Help",
//                    contentDescription = "Go to Help Screen",
//                    icon = Icons.Default.Help
//                ),
////                NavDrawerItem(
////                    id = "logout",
////                    title = "Logout",
////                    contentDescription = "Go to Login Screen",
////                    icon = Icons.Default.Info
////                )
//            ),
//            onItemClick = {
//                if (it.title == "Logout") {
//                    viewModel.onEvent(AuthUiEvent.Logout)
//                }
//                if (it.title == "Home") {
//                    navController.navigate(
//                        Screen.AuctionListScreen.route
//                    )
//                }
//            }
//        )
            widthDp?.let {
                Modifier
                    .fillMaxHeight()
                    .width(it)
            }?.let { Spacer(it) }
        },
        drawerShape = myShape,
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
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    navController.navigate(
                        Screen.PostItemScreen.route
                    )
                },
                contentColor = Color.White,
                backgroundColor = Black
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add icon")
            }
        },
        scaffoldState = scaffoldState,
        content = {
            PushScreen()
            MainScreenBody(
                auctionViewModel = auctionViewModel,
                navController = navController
            )
        }

    )


}


@Composable
fun MainScreenBody(
    auctionViewModel: AuctionListViewModel = hiltViewModel(),
    navController: NavController
) {

    val auctionItemState = auctionViewModel.stateItem
    val categories = auctionViewModel.state.value.categories

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = auctionViewModel.stateItem.isItemRefreshing
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {



        Column(
            modifier = Modifier
        ) {
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    auctionViewModel.onEvent(AuctionListEvent.Refresh)
                }
            ) {

                Spacer(modifier = Modifier.size(10.dp))
                ItemLazyColumn(
                    auctionListState = auctionItemState,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun CategoriesLazyRow(
    auctionViewModel: AuctionListViewModel = hiltViewModel()
) {
    val auctionCategoriesState = auctionViewModel.state

    Column(modifier = Modifier) {
        Text(
            text = "Categories",
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        LazyRow(
            modifier = Modifier.background(Color.White),
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(auctionCategoriesState.value.categories) { categories ->
                CategoriesListItem(auctionViewModel = auctionViewModel, category = categories)
            }
        }
    }
}

@Composable
fun ItemLazyColumn(
    auctionListState: AuctionListState,
    navController: NavController,
    auctionViewModel: AuctionListViewModel = hiltViewModel()
) {

    Column(modifier = Modifier) {


        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            item {
                CategoriesLazyRow(
                    auctionViewModel = auctionViewModel
                )

                Text(
                    text = "Items",
                    color = Color.Black,
                    maxLines = 1,
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            items(auctionListState.item) { item ->
                ItemList(item = item,
                    onItemClick = {
                        navController.navigate(Screen.AuctionItemDetailScreen.route + "/${item.id}")
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.size(60.dp))
            }
        }
    }
}


@Composable
fun customShape() = MyShape()

@Composable
fun pxToDp(px: Float) = with(LocalDensity.current) { px.toDp() }


class MyShape : Shape {
    var leftSpaceWidth: Float? = null

    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: Density
    ): Outline {
        leftSpaceWidth = size.width * 1 / 3
        return Outline.Rectangle(
            Rect(
                left = 0f,
                top = 0f,
                right = (size.width * 2.5 / 3).toFloat(),
                bottom = size.height
            )
        )
    }
}