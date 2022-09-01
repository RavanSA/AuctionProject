package android.project.auction.presentation


sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("sign_up_screen")
    object AuctionListScreen : Screen("auth_screen")
    object AuctionItemDetailScreen : Screen("item_detail_screen")
    object PlaceBidAmountScreen : Screen("place_bid_amount_screen")
    object PostItemScreen : Screen("post_item_screen")
    object SubCategoriesScreen : Screen("sub_categories_screen")
    object CreateItemScreen : Screen("create_item_screen")
    object FavoritesScreen : Screen("favorites_screen")
    object DetailedSearchCategoriesScreen : Screen("detailed_categories_search_screen")
    object DetailedSearchSubCategoriesScreen : Screen("detailed_sub_categories_screen")
    object DetailedSearchScreen : Screen("detailed_search_screen")
    object HomeScreen : Screen("home_screen")
    object CategoriesScreen : Screen("categories_screen")
    object FavoriteDetailsScreen : Screen("favorites_screen")
    object ProfileScreen : Screen("profile_screen")
}