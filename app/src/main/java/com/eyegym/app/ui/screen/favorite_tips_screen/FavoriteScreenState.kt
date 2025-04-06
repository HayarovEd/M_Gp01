package com.eyegym.app.ui.screen.favorite_tips_screen

import com.eyegym.app.domain.model.Trip

data class FavoriteScreenState(
    val trips: List<Trip> = emptyList(),
)