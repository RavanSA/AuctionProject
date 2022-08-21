package android.project.auction.presentation.favorites.favoritesdetail

import android.project.auction.domain.use_case.AuctionProjectUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteDetailsViewModel @Inject constructor(
    private val useCase: AuctionProjectUseCase,
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    private val _state = mutableStateOf(FavoriteDetailsState())
    val state: State<FavoriteDetailsState> = _state

    init {
        savedStateHandle.get<String>("itemId")?.let { itemId ->
            getFavoriteDetailItem(id = itemId)
        }
    }

    private fun getFavoriteDetailItem(id: String) {
        viewModelScope.launch {
            val detail = useCase.getFavoriteItemById.invoke(id)

            _state.value = state.value.copy(
                favoriteDetail = detail
            )

        }
    }

}