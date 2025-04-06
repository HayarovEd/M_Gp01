package com.eyegym.app.ui.screen.favorite_tips_screen

sealed interface FavoriteScreenAction {
    class UpdateFavorite(val id: Int) : FavoriteScreenAction
}