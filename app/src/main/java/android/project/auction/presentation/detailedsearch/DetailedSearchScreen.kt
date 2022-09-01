package android.project.auction.presentation.detailedsearch

import android.project.auction.domain.model.category.SubAndCategory
import android.project.auction.presentation.ui.common.topBar.TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun DetailedSearchScreen(
    navController: NavController,
    subAndCategory: SubAndCategory
) {

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(Color.White)
            ) {
                TopBar(
                    title = "${subAndCategory.categoryName},${subAndCategory.subCategoryName}",
                    icon = Icons.Default.ArrowBack,
                    onNavigationIconClick = {

                    },
                    leftIcon = Icons.Default.Add
                )
            }
        },
        content = {
            DetailedSearchScreenContent(
                navController
            )
        }
    )

}

@Composable
fun DetailedSearchScreenContent(navController: NavController) {

}
