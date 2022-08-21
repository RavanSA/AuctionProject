package android.project.auction.presentation.favorites.favoriteslist

import android.project.auction.data.local.entity.Favorites

sealed class FavoritesEvent {
    data class DeleteItem(val fav: Favorites) : FavoritesEvent()
    object RestoreItem : FavoritesEvent()
}