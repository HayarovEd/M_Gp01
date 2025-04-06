package com.eyegym.app.ui.screen.record_screen

import com.eyegym.app.domain.model.Optics
import com.eyegym.app.domain.model.mockOptics

data class RecordScreenState(
    val optics: List<Optics> = mockOptics,
    val query: String = "",
)