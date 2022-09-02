package android.project.auction.presentation.detailedsearch.screens

import android.project.auction.domain.model.category.SubAndCategory
import android.project.auction.domain.model.category.SubCategories
import android.project.auction.domain.model.category.SubCategory
import android.project.auction.presentation.Screen
import android.project.auction.presentation.ui.common.topBar.TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DetailedSearchSubCategoriesScreen(
    navController: NavController,
    category: SubCategories
) {


    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(Color.White)
            ) {
                TopBar(
                    title = category.name,
                    icon = Icons.Default.ArrowBack,
                    onNavigationIconClick = {

                    },
                    leftIcon = Icons.Default.Add
                )
            }
        },
        content = {
            SubCategoriesLazyList(category, navController)
        }
    )
}

@Composable
fun SubCategoriesLazyList(subCategories: SubCategories, navController: NavController) {
    val categoryId: String = subCategories.id
    val categoryName: String = subCategories.name
    LazyColumn() {
        items(subCategories.subCategories) { sub ->
            SubCategoriesItems(
                sub, categoryId, categoryName, navController
            )
        }
    }
}

@Composable
fun SubCategoriesItems(
    subCategory: SubCategory,
    categoryId: String,
    categoryName: String,
    navController: NavController
) {
    val categories = SubAndCategory(
        subCategory.id,
        categoryId,
        categoryName,
        subCategory.name
    )

    Column(
        modifier = Modifier
            .padding(
                15.dp
            )
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "itemCategory",
                    value = categories
                )

                navController.navigate(
                    Screen.DetailedSearchScreen.route
                )


            },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = subCategory.name, fontSize = 15.sp)
        Divider(modifier = Modifier.size(1.dp), thickness = 3.dp)
    }
}