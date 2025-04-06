package com.eyegym.app.ui.screen.form_record_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.eyegym.app.domain.model.mockOptics
import com.eyegym.app.domain.repository.DataStoreRepository
import com.eyegym.app.ui.navigation.NavigationRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    val id = savedStateHandle.toRoute<NavigationRoute.FormRecord>().id

    private val _state = MutableStateFlow(FormScreenState())
    val state = _state
        .onStart {
            _state.value.copy(
                optics = mockOptics.first { it.id == id }
            )
                .updateState()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = FormScreenState()
        )

    fun onAction(action: FormScreenAction) {
        when (action) {
            FormScreenAction.OnSend -> {
                viewModelScope.launch {
                    dataStoreRepository.setCountStarts(0)
                    _state.value.copy(
                        isSending = true
                    )
                        .updateState()
                }
            }

            is FormScreenAction.UpdateDate -> {
                _state.value.copy(
                    dateRecord = action.date
                )
                    .updateState()
            }

            is FormScreenAction.UpdateName -> {
                _state.value.copy(
                    name = action.name
                )
                    .updateState()
            }

            is FormScreenAction.UpdatePhone -> {
                _state.value.copy(
                    phone = action.phone
                )
                    .updateState()
            }

            is FormScreenAction.UpdateReason -> {
                _state.value.copy(
                    reason = action.reason
                )
                    .updateState()
            }

            is FormScreenAction.UpdateTime -> {
                _state.value.copy(
                    timeRecord = action.time
                )
                    .updateState()
            }
        }
    }

    private fun FormScreenState.updateState() {
        _state.update {
            this
        }
    }
}