package android.project.auction.presentation.favorites.favoritesdetail

import android.annotation.SuppressLint
import android.project.auction.data.local.entity.Favorites
import android.project.auction.presentation.Screen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AuctionItemDetailScreen(
    navController: NavController,
    favoriteDetailsViewModel: FavoriteDetailsViewModel = hiltViewModel()
) {

    val fav = favoriteDetailsViewModel.state.value.favoriteDetail

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
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
                }
            )
        },
        scaffoldState = scaffoldState,
        content = {

            if (fav != null) {
                FavoritesDetailContent(
                    fav = fav
                )
            }

        }
    )
}

@Composable
fun FavoritesDetailContent(
    fav: Favorites
) {

}
