package com.eyegym.app.domain.model

import com.eyegym.app.R

enum class TaskDuration(
    val duration: Int,
    val titleInt: Int,
    val image1Int: Int,
    val image2Int: Int,
    val descriptionInt: Int,
) {
    FAST(
        duration = 15,
        titleInt = R.string.fast,
        image1Int = R.drawable.eye_open,
        image2Int = R.drawable.eye_close,
        descriptionInt = R.string.fast_desc
    ),
    STANDARD(
        duration = 60,
        titleInt = R.string.standard,
        image1Int = R.drawable.eye_rignt,
        image2Int = R.drawable.eye_left,
        descriptionInt = R.string.standard_desc
    ),
    FULL(
        duration = 120,
        titleInt = R.string.full,
        image1Int = R.drawable.eye_top,
        image2Int = R.drawable.eye_bottom,
        descriptionInt = R.string.full_desc
    )
}