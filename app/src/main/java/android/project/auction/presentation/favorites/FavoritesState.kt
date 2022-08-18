package android.project.auction.presentation.favorites

import android.project.auction.data.local.entity.Favorites

data class FavoritesState(
    val favoritesItems: List<Favorites> = emptyList()
)