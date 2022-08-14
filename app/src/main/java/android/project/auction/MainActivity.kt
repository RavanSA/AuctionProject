package android.project.auction

import android.os.Bundle
import android.project.auction.domain.model.category.SubAndCategory
import android.project.auction.domain.model.category.SubCategories
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailScreen
import android.project.auction.presentation.auctionitemdetail.components.PlaceBid
import android.project.auction.presentation.auctionlist.AuctionListScreen
import android.project.auction.presentation.auth.sign_in.LoginPage
import android.project.auction.presentation.auth.signup.SignUpPage
import android.project.auction.presentation.postitem.PostItem
import android.project.auction.presentation.postitem.components.CreateItem
import android.project.auction.presentation.postitem.components.SubCategegoriesList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = Screen.AuctionListScreen.route) {
                composable(route = Screen.LoginScreen.route) {
                    LoginPage(navController = navController)
                }
                composable(route = Screen.SignUpScreen.route) {
                    SignUpPage(navController = navController)
                }
                composable(route = Screen.AuctionListScreen.route) {
                    AuctionListScreen(navController = navController)
                }
                composable(route = Screen.AuctionItemDetailScreen.route + "/{itemId}") {
                    AuctionItemDetailScreen(navController = navController)
                }
                composable(route = Screen.PlaceBidAmountScreen.route + "/{itemId}") {
                    PlaceBid(navController = navController, savedStateHandle = SavedStateHandle())
                }
                composable(route = Screen.PostItemScreen.route) {
                    PostItem(navController = navController)
                }
                composable(route = Screen.SubCategoriesScreen.route) {
                    val subCategory = navController
                        .previousBackStackEntry?.savedStateHandle?.get<SubCategories>("subcategory")

                    subCategory?.let { it1 ->
                        SubCategegoriesList(
                            navController = navController,
                            category = it1
                        )
                    }

                }
                composable(route = Screen.CreateItemScreen.route) {
                    val subAndCategory = navController
                        .previousBackStackEntry?.savedStateHandle?.get<SubAndCategory>("itemCategory")

                    subAndCategory?.let { it -> CreateItem(navController = navController, it) }
                }
            }
        }
    }
}