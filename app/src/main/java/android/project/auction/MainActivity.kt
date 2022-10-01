package android.project.auction

import android.os.Bundle
import android.project.auction.domain.model.category.SubAndCategory
import android.project.auction.domain.model.category.SubCategories
import android.project.auction.domain.model.item.ItemDetail
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailScreen
import android.project.auction.presentation.auctionitemdetail.components.PlaceBid
import android.project.auction.presentation.auctionlist.AuctionListScreen
import android.project.auction.presentation.auth.sign_in.LoginPage
import android.project.auction.presentation.auth.signup.SignUpPage
import android.project.auction.presentation.chat.ChatScreen
import android.project.auction.presentation.detailedsearch.DetailedSearchScreen
import android.project.auction.presentation.detailedsearch.screens.DetailedSearchCategoriesScreen
import android.project.auction.presentation.detailedsearch.screens.DetailedSearchSubCategoriesScreen
import android.project.auction.presentation.favorites.favoriteslist.FavoritesScreen
import android.project.auction.presentation.postitem.PostItem
import android.project.auction.presentation.postitem.components.CreateItem
import android.project.auction.presentation.postitem.components.SubCategegoriesList
import android.project.auction.presentation.updateprofile.UpdateProfileScreen
import android.project.auction.presentation.userprofile.UserProfileScreen
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = Screen.AuctionListScreen.route) {
                composable(route = Screen.AuctionListScreen.route) {
                    AuctionListScreen(navController = navController)
                }
                composable(route = Screen.LoginScreen.route) {
                    LoginPage(navController = navController)
                }
                composable(route = Screen.SignUpScreen.route) {
                    SignUpPage(navController = navController)
                }
                composable(route = Screen.AuctionItemDetailScreen.route + "/{itemId}") {
                    AuctionItemDetailScreen(navController = navController)
                }
                composable(route = Screen.PlaceBidAmountScreen.route) {
                    val itemDetailForPlaceBid = navController
                        .previousBackStackEntry?.savedStateHandle?.get<ItemDetail>("itemDetails")

                    Log.d("ITEMDETAILTEST", itemDetailForPlaceBid.toString())
                    if (itemDetailForPlaceBid != null) {
                        Log.d("TESTSCREEN", "PLACEBID")
                        PlaceBid(
                            navController = navController,
                            savedStateHandle = SavedStateHandle(),
                            itemDetail = itemDetailForPlaceBid
                        )
                    }

                }
                composable(route = Screen.ChatScreen.route) {
                    val itemDetailForChat = navController
                        .previousBackStackEntry?.savedStateHandle?.get<ItemDetail>("itemDetails")

                    Log.d("ITEMDETAILCHAT", itemDetailForChat.toString())
                    if (itemDetailForChat != null) {
                        ChatScreen(
                            navController = navController,
                            itemDetail = itemDetailForChat
                        )
                    }
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
                composable(route = Screen.FavoritesScreen.route) {
                    FavoritesScreen(navController = navController)
                }
                composable(route = Screen.DetailedSearchCategoriesScreen.route) {
                    DetailedSearchCategoriesScreen(navController = navController)
                }
                composable(route = Screen.DetailedSearchSubCategoriesScreen.route) {
                    val detailedSubCategory = navController
                        .previousBackStackEntry?.savedStateHandle?.get<SubCategories>("detailedSubcategory")

                    detailedSubCategory?.let { it ->
                        DetailedSearchSubCategoriesScreen(
                            navController = navController,
                            category = it
                        )
                    }

                }
                composable(route = Screen.DetailedSearchScreen.route) {
                    val detailedSubAndCategory = navController
                        .previousBackStackEntry?.savedStateHandle?.get<SubAndCategory>("itemCategory")

                    detailedSubAndCategory?.let { it ->
                        DetailedSearchScreen(
                            navController = navController,
                            subAndCategory = it
                        )
                    }
                }
                composable(route = Screen.UserProfileScreen.route) {
                    UserProfileScreen(navController = navController)
                }
                composable(route = Screen.UpdateProfileScreen.route) {
                    UpdateProfileScreen(navController = navController)
                }
            }
        }
    }
}