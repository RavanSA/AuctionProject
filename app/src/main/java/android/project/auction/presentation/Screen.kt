package android.project.auction.presentation


sealed class Screen(val route: String) {
    object LoginScreen: Screen("login_screen")
    object SignUpScreen: Screen("sign_up_screen")
    object SecretScreen: Screen("auth_screen")
    object HomeScreen: Screen("home_screen")
    object CategoriesScreen: Screen("categories_screen")
    object FavoritesScreen: Screen("favorites_screen")
    object ProfileScreen: Screen("profile_screen")
}