package android.project.auction.presentation.postitem

import android.project.auction.presentation.postitem.components.CategoriesList
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
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
import androidx.navigation.NavController

@Composable
fun PostItem(
    navController: NavController
) {

    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Row(
                        modifier = Modifier.padding(15.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Categories")

                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackPressedDispatcher?.onBackPressed()
                    }) {
                        Icon(Icons.Default.ArrowBack, "Menu")
                    }
                },
                actions = {

                }
            )
        },
        content = {
            CategoriesList(navController)
        }
    )

}


@Composable
fun SearchBar(
) {

//    Column(modifier = Modifier.background(Color.White)) {
//        OutlinedTextField(
//            value = "",
//            onValueChange = {
//
//            },
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//                .background(Color.White),
//            placeholder = {
//                Text(text = "Search...")
//            },
//            maxLines = 1,
//            singleLine = true
//        )
//    }
}

