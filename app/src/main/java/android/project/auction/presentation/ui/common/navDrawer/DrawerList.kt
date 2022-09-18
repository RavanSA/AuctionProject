package android.project.auction.presentation.ui.common.navDrawer

import android.project.auction.domain.model.userinfo.UserInfo
import android.project.auction.presentation.Screen
import android.project.auction.presentation.auth.AuthUiEvent
import android.project.auction.presentation.auth.AuthViewModel
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@ExperimentalMaterialApi
@Composable
fun DrawerList(
    navController: NavController,
    userInfo: UserInfo,
    viewModel: AuthViewModel = hiltViewModel()
) {

    if (userInfo.id != null) {
        DrawerBody(
            items = listOf(
                NavDrawerItem(
                    id = "username",
                    title = userInfo.email ?: "",
                    contentDescription = "Go to Home Screen",
                    icon = Icons.Outlined.Person
                ),
                NavDrawerItem(
                    id = "participatedauction",
                    title = "Participated Auctions",
                    contentDescription = "Go to Settings Screen",
                    icon = Icons.Outlined.Gavel
                ),
                NavDrawerItem(
                    id = "myauction",
                    title = "My Auctions",
                    contentDescription = "Go to Settings Screen",
                    icon = Icons.Outlined.MyLocation
                ),
                NavDrawerItem(
                    id = "addauction",
                    title = "Add Auction",
                    contentDescription = "Go to Help Screen",
                    icon = Icons.Outlined.AddCircle
                ),
                NavDrawerItem(
                    id = "favorites",
                    title = "Favorites",
                    contentDescription = "Go to Help Screen",
                    icon = Icons.Outlined.Favorite
                ),
                NavDrawerItem(
                    id = "logout",
                    title = "Logout",
                    contentDescription = "Go to Help Screen",
                    icon = Icons.Outlined.Logout
                ),

                NavDrawerItem(
                    id = "contactus",
                    title = "Contact Us",
                    contentDescription = "Go to Help Screen",
                    icon = Icons.Outlined.Forum
                ),

                NavDrawerItem(
                    id = "language",
                    title = "Language",
                    contentDescription = "Go to Help Screen",
                    icon = Icons.Outlined.Language
                ),
                NavDrawerItem(
                    id = "help",
                    title = "Help",
                    contentDescription = "Go to Help Screen",
                    icon = Icons.Outlined.Help
                ),

                ),
            onItemClick = {
                if (it.id == "logout") {
                    viewModel.onEvent(AuthUiEvent.Logout)
//                    navController.navigate(
//                        Screen.LoginScreen.route
//                    )
                }
                if (it.id == "username") {
                    navController.navigate(
                        Screen.UserProfileScreen.route
                    )
                }
                if (it.id == "participatedauction") {
                    navController.navigate(
                        Screen.UserProfileScreen.route
                    )
                }
                if (it.id == "myauction") {
                    navController.navigate(
                        Screen.UserProfileScreen.route
                    )
                }
                if (it.id == "addauction") {
                    navController.navigate(
                        Screen.PostItemScreen.route
                    )
                }
                if (it.id == "favorites") {
                    navController.navigate(
                        Screen.FavoritesScreen.route
                    )
                }
            }
        )
    } else {
        DrawerBody(
            items = listOf(
                NavDrawerItem(
                    id = "login",
                    title = "Login",
                    contentDescription = "Go to Home Screen",
                    icon = Icons.Default.Home
                ),
                NavDrawerItem(
                    id = "myauction",
                    title = "My Auctions",
                    contentDescription = "Go to Settings Screen",
                    icon = Icons.Default.AdminPanelSettings
                ),
                NavDrawerItem(
                    id = "addauction",
                    title = "Add Auction",
                    contentDescription = "Go to Help Screen",
                    icon = Icons.Default.Add
                ),
                NavDrawerItem(
                    id = "contactus",
                    title = "Contact Us",
                    contentDescription = "Go to Help Screen",
                    icon = Icons.Default.Contacts
                ),
                NavDrawerItem(
                    id = "language",
                    title = "Language",
                    contentDescription = "Go to Help Screen",
                    icon = Icons.Default.Language
                ),
                NavDrawerItem(
                    id = "help",
                    title = "Help",
                    contentDescription = "Go to Help Screen",
                    icon = Icons.Default.Help
                ),
            ),
            onItemClick = {
                if (it.title == "login") {
                    navController.navigate(
                        Screen.LoginScreen.route
                    )
                }
                if (it.id == "myauction") {
                    navController.navigate(
                        Screen.UserProfileScreen.route
                    )
                }
                if (it.id == "addauction") {
                    navController.navigate(
                        Screen.PostItemScreen.route
                    )
                }
            }
        )
    }
}