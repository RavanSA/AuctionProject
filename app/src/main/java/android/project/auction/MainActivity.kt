package android.project.auction

import android.os.Bundle
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auth.sign_in.AuthScreen
import android.project.auction.presentation.auth.sign_in.LoginPage
import android.project.auction.presentation.auth.sign_in.SecretScreen
import android.project.auction.presentation.auth.signup.SignUpPage
import android.project.auction.presentation.ui.theme.AuctionProjectTheme
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
            NavHost(navController, startDestination = Screen.LoginScreen.route) {
                composable(route = Screen.LoginScreen.route) {
                    LoginPage(navController = navController)
                }
                composable(route = Screen.SignUpScreen.route) {
                    SignUpPage(navController = navController)
                }
                composable(route = Screen.SecretScreen.route) {
                    SecretScreen(navController = navController)
                }
            }
        }
    }
}
