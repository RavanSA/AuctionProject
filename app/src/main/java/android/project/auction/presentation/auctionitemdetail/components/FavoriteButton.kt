package android.project.auction.presentation.auctionitemdetail.components

import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailEvent
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailViewModel
import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoriteButton(
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