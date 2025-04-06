package com.eyegym.app.ui.screen.tips_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyegym.app.domain.repository.LocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TripsScreenViewModel(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TripsScreenState())
    val state = _state
        .onStart {
            loadLocalData()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = TripsScreenState()
        )

    fun onAction(action: TripsScreenAction) {
        when (action) {
            is TripsScreenAction.UpdateFavorite -> {
                viewModelScope.launch {
                    if (state.value.favoriteIds.contains(action.id)) {
                        localRepository.deleteFavorite(action.id)
                    } else {
                        localRepository.insertFavorite(action.id)
                    }
                }
            }
        }
    }

    private fun loadLocalData() {
        localRepository.getAllFavorites()
            .onEach {
                _state.value.copy(
                    favoriteIds = it
                )
                    .updateState()
            }
            .launchIn(viewModelScope)
    }

    private fun TripsScreenState.updateState() {
        _state.update {
            this
        }
    }
}