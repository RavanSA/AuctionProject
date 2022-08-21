package android.project.auction.presentation.favorites.favoriteslist

import android.project.auction.data.local.entity.Favorites
import android.project.auction.domain.use_case.AuctionProjectUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val useCase: AuctionProjectUseCase
) : ViewModel() {

    private val _state = mutableStateOf(FavoritesState())
    val state: State<FavoritesState> = _state

    private var getItemFavoritesJob: Job? = null

    private var recentlyDeletedItem: Favorites? = null

    init {
        getItemFavorites()
    }

    fun onEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.RestoreItem -> {
                viewModelScope.launch {
                    useCase.addFavoriteItem.invoke(
                        recentlyDeletedItem ?: return@launch
                    )
                    recentlyDeletedItem = null
                }
            }
            is FavoritesEvent.DeleteItem -> {
                viewModelScope.launch {
                    useCase.deleteFavoriteItem.invoke(event.fav)
                    recentlyDeletedItem = event.fav
                }
            }
        }
    }


    private fun getItemFavorites() {
        getItemFavoritesJob?.cancel()
        getItemFavoritesJob = useCase.getFavoriteItems.invoke().onEach { items ->
            _state.value = state.value.copy(
                favoritesItems = items
            )
        }.launchIn(viewModelScope)
    }


}