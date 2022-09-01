package android.project.auction.presentation.detailedsearch

import android.project.auction.common.Resource
import android.project.auction.domain.use_case.usecases.AuctionProjectUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class DetailedSearchViewModel @Inject constructor(
    private val useCase: AuctionProjectUseCase
) : ViewModel() {

    private val _state = mutableStateOf(DetailedSearchState())
    val state: State<DetailedSearchState> = _state

    init {
        getSubCategories()
    }

    private fun getSubCategories() {
        useCase.getSubCategories.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        subCategories = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        subCategoryError = result.message ?: "ERROR"
                    )
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isSubCategoriesLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}