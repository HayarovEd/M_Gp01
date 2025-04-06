package com.eyegym.app.ui.screen.current_tip_screen


sealed interface CurrentTipScreenAction {
    data object UpdateFavorite : CurrentTipScreenAction
}