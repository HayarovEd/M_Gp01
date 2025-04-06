package com.eyegym.app.ui.screen.survey_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyegym.app.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SurveyScreenViewModel(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {


    private val _state = MutableStateFlow(SurveyScreenState())
    val state = _state
        .onStart {

        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = SurveyScreenState()
        )

    fun onAction(action: SurveyScreenAction) {
        when (action) {
            is SurveyScreenAction.UpdateCommit -> {
                _state.value.copy(
                    commit = action.value
                )
                    .updateState()
            }
            is SurveyScreenAction.UpdateEffect -> {
                _state.value.copy(
                    effect = action.value
                )
                    .updateState()
            }
            is SurveyScreenAction.UpdateMaterial -> {
                _state.value.copy(
                    material = action.value
                )
                    .updateState()
            }
            is SurveyScreenAction.UpdateRecord -> {
                _state.value.copy(
                    record = action.value
                )
                    .updateState()
            }

            SurveyScreenAction.OnSend -> {
                viewModelScope.launch {
                    dataStoreRepository.setCountStarts(0)
                    _state.value.copy(
                        isSending = true
                    )
                        .updateState()
                }
            }
        }
    }

    private fun SurveyScreenState.updateState() {
        _state.update {
            this
        }
    }
}