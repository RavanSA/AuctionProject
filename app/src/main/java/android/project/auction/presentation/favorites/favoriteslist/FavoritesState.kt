package android.project.auction.presentation.favorites.favoriteslist

import android.project.auction.data.local.entity.Bids
import android.project.auction.data.local.entity.Favorites

data class FavoritesState(
    val favoritesItems: List<Favorites> = emptyList(),
    val highestBid: List<Bids> = emptyList()
)