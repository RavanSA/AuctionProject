package android.project.auction.presentation.auctionlist

import android.project.auction.common.AuthResult
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auctionlist.components.ItemListItem
import android.project.auction.presentation.auth.AuthUiEvent
import android.project.auction.presentation.auth.AuthViewModel
import android.project.auction.presentation.auth.sign_in.dpToSp
import android.project.auction.presentation.ui.common.LoadingScreen
import android.project.auction.presentation.ui.common.bottomNav.BottomNav
import android.project.auction.presentation.ui.common.navDrawer.DrawerBody
import android.project.auction.presentation.ui.common.navDrawer.DrawerHeader
import android.project.auction.presentation.ui.common.navDrawer.NavDrawerItem
import android.project.auction.presentation.ui.common.topBar.TopBar
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
            TopBar(
                title = "Auction" ,
                icon = Icons.Default.ThumbUp,
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
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
        ), onItemClick ={
            println("Clicked on ${it.title}")
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
        }

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(auctionListState.value.item) { item ->
                    ItemListItem(item = item, onItemClick = {})
                }
            }

//            LazyColumn(modifier = Modifier.fillMaxSize() ){
//                items(auctionListState.value.categories) { categories ->
//                    CategoriesListItem(category = categories, onItemClick = {})
//                }
//            }

//            LazyColumn(modifier = Modifier.fillMaxSize() ){
//                items(auctionListState.value.item) { item ->
//                    ItemListItem(item = item, onItemClick = {} )
//                }
//            }
//
//
//            Text(text = "You're authenticated!", color = Color.Black)
//
//            Spacer(modifier = Modifier.padding(10.dp))
//
//
//
//            Button(onClick = {
//                viewModel.onEvent(AuthUiEvent.Logout)
//            }, modifier = Modifier
//                .fillMaxWidth(0.8f)
//                .height(50.dp)) {
//                Text(text = "Logout", fontSize = dpToSp(20.dp), color = Color.White)
//            }

        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
//        LazyColumn(modifier = Modifier.fillMaxSize() ){
//            items(auctionListState.value.item) { item ->
//                ItemListItem(item = item, onItemClick = {} )
//            }
//        }


            Text(text = "You're authenticated!", color = Color.Black)

            Spacer(modifier = Modifier.padding(10.dp))



            Button(
                onClick = {
                    viewModel.onEvent(AuthUiEvent.Logout)
                }, modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text(text = "Logout", fontSize = dpToSp(20.dp), color = Color.White)
            }
        }
    }



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