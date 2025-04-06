package com.eyegym.app.ui.screen.tips_screen

import com.eyegym.app.domain.model.Trip
import com.eyegym.app.domain.model.mockTrips

data class TripsScreenState(
    val trips: Map<Int, List<Trip>> = mockTrips.groupBy { it.group },
    val favoriteIds: List<Int> = emptyList(),
)