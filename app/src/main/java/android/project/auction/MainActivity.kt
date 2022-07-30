package android.project.auction

import android.os.Bundle
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auctionitemdetail.AuctionItemDetailScreen
import android.project.auction.presentation.auctionlist.AuctionListScreen
import android.project.auction.presentation.auth.sign_in.LoginPage
import android.project.auction.presentation.auth.signup.SignUpPage
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
            }
        }
    }
}
