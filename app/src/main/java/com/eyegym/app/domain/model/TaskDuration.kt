package com.eyegym.app.domain.model

import com.eyegym.app.R

enum class TaskDuration(val duration: Int, val titleInt: Int) {
    FAST(15, R.string.fast),
    STANDART(60, R.string.standard),
    FULL(120, R.string.full)
}