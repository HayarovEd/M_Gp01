package com.eyegym.app.ui.screen.warmup_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyegym.app.domain.repository.DataStoreRepository
import com.eyegym.app.domain.repository.ServiceController
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WarmUpScreenViewModel(
    private val serviceController: ServiceController,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private var countdownJob: Job? = null

    private val _state = MutableStateFlow(WarmUpScreenState())
    val state = _state
        .onStart {
            loadLocalData()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = WarmUpScreenState()
        )

    private fun loadLocalData() {
        dataStoreRepository.getCountStarts()
            .onEach {
                _state.value.copy(
                    countStarts = it
                )
                    .updateState()
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: WarmUpScreenAction) {
        when (action) {
            is WarmUpScreenAction.OnChangeTask -> {
                _state.value.copy(
                    taskDuration = action.taskDuration,
                    remainingTime = state.value.taskDuration.duration
                )
                    .updateState()
                serviceController.vibratePhone()
            }

            WarmUpScreenAction.OnPauseTask -> {
                pauseCountdown()
            }
            WarmUpScreenAction.OnResumeTask -> {
                startCountdown(state.value.remainingTime)
            }
            WarmUpScreenAction.OnStartTask -> {
                viewModelScope.launch {
                    dataStoreRepository.setCountStarts(state.value.countStarts+1)
                    delay(300)
                    if (state.value.countStarts<3) {
                        startCountdown(state.value.taskDuration.duration)
                    }
                }
            }
            WarmUpScreenAction.OnStopTask -> {
                stopCountdown()
            }

            WarmUpScreenAction.OnReadyTask -> {
                readyTask()
            }
        }
    }

    private fun startCountdown(durationSeconds: Int) {
        countdownJob?.cancel()

        _state.value.copy(
            warmUpState = WarmUpState.STARTED,
            remainingTime = durationSeconds
        )
            .updateState()

        countdownJob = viewModelScope.launch {
            while (state.value.remainingTime > 0) {
                delay(1000)
                _state.value.copy(
                    remainingTime = state.value.remainingTime-1
                )
                    .updateState()
            }

            if (state.value.remainingTime <= 0) {
               stopCountdown()
            }
        }
        countdownJob?.start()
    }


    private fun pauseCountdown() {
        _state.value.copy(
            warmUpState = WarmUpState.PAUSED
        )
            .updateState()
        countdownJob?.cancel()
        countdownJob = null
    }

    private fun stopCountdown() {
        countdownJob?.cancel()
        countdownJob = null
        _state.value.copy(
            warmUpState = WarmUpState.COMPLETED,
            remainingTime = state.value.taskDuration.duration
        )
            .updateState()
    }

    private fun WarmUpScreenState.updateState() {
        _state.update {
            this
        }
    }

    private fun readyTask() {
        _state.value.copy(
            warmUpState = WarmUpState.READY,
            remainingTime = state.value.taskDuration.duration
        )
            .updateState()
    }

    override fun onCleared() {
        super.onCleared()
        countdownJob?.cancel()
    }
}