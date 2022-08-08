package android.project.auction.presentation.postitem

import android.project.auction.presentation.postitem.components.CategoriesList
import android.project.auction.presentation.ui.common.topBar.TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun PostItem(
    navController: NavController,
    postItemViewModel: PostItemViewModel = hiltViewModel()
) {

    val categoriesState = postItemViewModel.state

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(White)
            ) {
                TopBar(
                    title = "Auction",
                    icon = Icons.Default.ArrowBack,
                    onNavigationIconClick = {

                    },
                    leftIcon = Icons.Default.Add
                )
                SearchBar()
            }
        },
        content = {
            CategoriesList(categoriesState)
        }
    )

}


@Composable
fun SearchBar(
) {

    Column(modifier = Modifier.background(Color.White)) {
        OutlinedTextField(
            value = "",
            onValueChange = {

            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.White),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )
    }
}