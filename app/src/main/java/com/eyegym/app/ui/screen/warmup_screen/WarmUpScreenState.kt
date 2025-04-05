package com.eyegym.app.ui.screen.warmup_screen

import com.eyegym.app.domain.model.TaskDuration

data class WarmUpScreenState(
    val warmUpState: WarmUpState = WarmUpState.READY,
    val taskDuration: TaskDuration = TaskDuration.FAST,
    val countStarts: Int = 0,
    val remainingTime: Int = taskDuration.duration
)

enum class WarmUpState{
    READY,
    STARTED,
    PAUSED,
    COMPLETED
}