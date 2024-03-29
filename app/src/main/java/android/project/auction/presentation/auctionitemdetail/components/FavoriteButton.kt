package android.project.auction.presentation.auctionitemdetail.components

import android.project.auction.common.AuthResult
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailEvent
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailViewModel
import android.project.auction.presentation.auth.AuthViewModel
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun FavoriteButton(
    scaffoldState: ScaffoldState,
    viewModel: AuthViewModel = hiltViewModel(),
    auctionItemDetailViewModel: AuctionItemDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val isAddedToFavorites = auctionItemDetailViewModel.state.value.itemAddedFavorite

    val context = LocalContext.current
    var isFavorite by remember { mutableStateOf(false) }
    var changeColor by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

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
                        scope.launch {
                            viewModel.authResults.collect { authResult ->
                                when (authResult) {
                                    is AuthResult.UnAuthorized -> {
                                        navController.navigate(Screen.LoginScreen.route)
                                    }
                                    is AuthResult.UnknownError -> {
                                        Toast.makeText(
                                            context,
                                            "UNKNOWN ERROR OCCURED",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                    else -> {}
                                }
                            }
                        }
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