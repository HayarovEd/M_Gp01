package com.eyegym.app.ui.screen.warmup_screen

import com.eyegym.app.domain.model.TaskDuration

sealed interface WarmUpScreenAction {
    data object OnStartTask : WarmUpScreenAction
    data object OnPauseTask : WarmUpScreenAction
    data object OnResumeTask : WarmUpScreenAction
    data object OnStopTask : WarmUpScreenAction
    data object OnReadyTask : WarmUpScreenAction
    class OnChangeTask(val taskDuration: TaskDuration) : WarmUpScreenAction
}