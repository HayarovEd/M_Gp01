package com.eyegym.app.ui.screen.record_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyegym.app.domain.model.mockOptics
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RecordScreenViewModel : ViewModel() {


    private val _state = MutableStateFlow(RecordScreenState())
    val state = _state
        .onStart {

        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = RecordScreenState()
        )

    fun onAction(action: RecordScreenAction) {
        when (action) {
            RecordScreenAction.OnSearch -> {
                _state.value.copy(
                    optics = mockOptics.filter {
                        it.name
                            .contains(
                                other = state.value.query,
                                ignoreCase = true
                            )
                                ||
                                it.address
                                    .contains(
                                        other = state.value.query,
                                        ignoreCase = true
                                    )
                    }
                )
                    .updateState()
            }

            is RecordScreenAction.UpdateQuery -> {
                _state.value.copy(
                    query = action.query
                )
                    .updateState()
            }
        }
    }

    private fun RecordScreenState.updateState() {
        _state.update {
            this
        }
    }
}