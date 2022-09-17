package android.project.auction.presentation.detailedsearch.screens

import android.project.auction.domain.model.category.SubAndCategory
import android.project.auction.domain.model.category.SubCategories
import android.project.auction.domain.model.category.SubCategory
import android.project.auction.presentation.Screen
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
                        Text(category.name)

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


            }
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = subCategory.name, fontSize = 15.sp)
        Divider(modifier = Modifier.size(1.dp), thickness = 3.dp)
    }
}