package com.eyegym.app.ui.screen.current_tip_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.eyegym.app.domain.model.mockTrips
import com.eyegym.app.domain.repository.LocalRepository
import com.eyegym.app.ui.navigation.NavigationRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CurrentTipScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val localRepository: LocalRepository
) : ViewModel() {

    val id = savedStateHandle.toRoute<NavigationRoute.CurrenTrip>().id

    private val _state = MutableStateFlow(CurrentTipScreenState())
    val state = _state
        .onStart {
            loadLocalData()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = CurrentTipScreenState()
        )

    fun onAction(action: CurrentTipScreenAction) {
        when (action) {
            CurrentTipScreenAction.UpdateFavorite -> {
                state.value.trip?.let { trip ->
                    viewModelScope.launch {
                        if (state.value.favoriteIds.contains(trip.id)) {
                            localRepository.deleteFavorite(trip.id)
                        } else {
                            localRepository.insertFavorite(trip.id)
                        }
                    }
                }
            }
        }
    }

    private fun loadLocalData() {
        localRepository.getAllFavorites()
            .onEach { favorite ->
                _state.value.copy(
                    favoriteIds = favorite,
                    trip = mockTrips.firstOrNull { it.id == id }
                )
                    .updateState()
            }
            .launchIn(viewModelScope)
    }

    private fun CurrentTipScreenState.updateState() {
        _state.update {
            this
        }
    }
}