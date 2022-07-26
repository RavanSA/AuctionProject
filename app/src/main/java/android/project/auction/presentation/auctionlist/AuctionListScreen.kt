package android.project.auction.presentation.auctionlist

import android.project.auction.common.AuthResult
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auctionlist.components.CategoriesListItem
import android.project.auction.presentation.auctionlist.components.InspirationItem
import android.project.auction.presentation.auth.AuthUiEvent
import android.project.auction.presentation.auth.AuthViewModel
import android.project.auction.presentation.ui.common.LoadingScreen
import android.project.auction.presentation.ui.common.bottomNav.BottomNav
import android.project.auction.presentation.ui.common.navDrawer.DrawerBody
import android.project.auction.presentation.ui.common.navDrawer.DrawerHeader
import android.project.auction.presentation.ui.common.navDrawer.NavDrawerItem
import android.project.auction.presentation.ui.common.topBar.TopBar
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun AuctionListScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
    auctionViewModel: AuctionListViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val context = LocalContext.current
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val auctionItemState = auctionViewModel.stateItem


    val auctionListState = auctionViewModel.state

    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { authResult ->
            when (authResult) {
                is AuthResult.UnAuthorized -> {
                    navController.navigate(Screen.LoginScreen.route)
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "UNKNOWN ERROR OCCURED",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
            ) {
                TopBar(
                    title = "Auction",
                    icon = Icons.Default.ThumbUp,
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
                SearchBar()
            }
        },
        drawerContent = {
        DrawerHeader()
        DrawerBody(
            items = listOf(
            NavDrawerItem(
                id = "home",
                title = "Home",
                contentDescription = "Go to Home Screen",
                icon = Icons.Default.Home
            ),
            NavDrawerItem(
                id = "settings",
                title = "Settings",
                contentDescription = "Go to Settings Screen",
                icon = Icons.Default.Settings
            ),
                NavDrawerItem(
                    id = "help",
                    title = "Help",
                    contentDescription = "Go to Help Screen",
                    icon = Icons.Default.Info
                ),
                NavDrawerItem(
                    id = "logout",
                    title = "Logout",
                    contentDescription = "Go to Login Screen",
                    icon = Icons.Default.Info
                )
            ),
            onItemClick = {
                if (it.title == "Logout") {
                    viewModel.onEvent(AuthUiEvent.Logout)
                }
            }
        )
    },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(65.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
                cutoutShape = CircleShape,
                //backgroundColor = Color.White,
                elevation = 22.dp
            ) {
                BottomNav(navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {},
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add icon")
            }
        },
        content = {
            MainScreenBody(
                auctionViewModel = auctionViewModel
            )
        }

    )


    if (state.value.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            LoadingScreen()
        }
    }

}


@Composable
fun SearchBar(
    auctionViewModel: AuctionListViewModel = hiltViewModel()
) {
    val auctionItemState = auctionViewModel.stateItem

    OutlinedTextField(
        value = auctionItemState.searchQuery,
        onValueChange = {
            auctionViewModel.onEvent(
                AuctionListEvent.OnSearchQueryChange(it)
            )
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        placeholder = {
            Text(text = "Search...")
        },
        maxLines = 1,
        singleLine = true
    )
}

@Composable
fun MainScreenBody(
    auctionViewModel: AuctionListViewModel = hiltViewModel()
) {

    val auctionItemState = auctionViewModel.stateItem
    val auctionCategoriesState = auctionViewModel.state

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = auctionViewModel.stateItem.isItemRefreshing
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CategoriesLazyRow(auctionListState = auctionCategoriesState)

//        ItemLazyColumn(auctionListState = auctionItemState)

        Column(
            modifier = Modifier
        ) {
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    auctionViewModel.onEvent(AuctionListEvent.Refresh)
                }
            ) {
//                CategoriesLazyRow(auctionListState = auctionCategoriesState)

//                CategoriesLazyRow(auctionListState = auctionCategoriesState)
                Spacer(modifier = Modifier.size(10.dp))
                ItemLazyColumn(auctionListState = auctionItemState)
            }
        }
    }
}


@Composable
fun CategoriesLazyRow(
    auctionListState: State<AuctionListState>
) {
    Log.d("CATEGORYLAZYITEMS", auctionListState.value.toString())


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
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(auctionListState.value.categories) { categories ->
                CategoriesListItem(category = categories, onItemClick = {})
                Log.d("CATEFORYITEMS", categories.toString())
            }
        }
    }
}

@Composable
fun ItemLazyColumn(
    auctionListState: AuctionListState
) {

    Column(modifier = Modifier) {


        Text(
            text = "Items",
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )


        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(auctionListState.item) { item ->
                InspirationItem(item = item)
            }
        }
    }
}