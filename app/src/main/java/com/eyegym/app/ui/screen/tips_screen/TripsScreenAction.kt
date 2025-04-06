package com.eyegym.app.ui.screen.tips_screen

sealed interface TripsScreenAction {
    class UpdateFavorite(val id: Int) : TripsScreenAction
}