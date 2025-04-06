package com.eyegym.app.ui.screen.favorite_tips_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyegym.app.domain.model.mockTrips
import com.eyegym.app.domain.repository.LocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteScreenViewModel(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteScreenState())
    val state = _state
        .onStart {
            loadLocalData()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = FavoriteScreenState()
        )

    fun onAction(action: FavoriteScreenAction) {
        when (action) {
            is FavoriteScreenAction.UpdateFavorite -> {
                viewModelScope.launch {
                    localRepository.deleteFavorite(action.id)
                }
            }
        }
    }

    private fun loadLocalData() {
        localRepository.getAllFavorites()
            .onEach { favorites ->
                _state.value.copy(
                    trips = mockTrips.filter { favorites.contains(it.id) }
                )
                    .updateState()
            }
            .launchIn(viewModelScope)
    }

    private fun FavoriteScreenState.updateState() {
        _state.update {
            this
        }
    }
}