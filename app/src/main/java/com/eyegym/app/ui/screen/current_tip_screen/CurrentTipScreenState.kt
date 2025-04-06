package com.eyegym.app.ui.screen.current_tip_screen

import com.eyegym.app.domain.model.Trip

data class CurrentTipScreenState(
    val trip: Trip? = null,
    val favoriteIds: List<Int> = emptyList(),
)