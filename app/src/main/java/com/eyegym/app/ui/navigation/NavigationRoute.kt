package com.eyegym.app.ui.navigation
import kotlinx.serialization.Serializable

sealed class NavigationRoute {
    @Serializable
    data object WarmUp : NavigationRoute()


    @Serializable
    data object Trips : NavigationRoute()

    @Serializable
    data object Favorite : NavigationRoute()

    @Serializable
    data class CurrenTrip(
        val id: Int
    ) : NavigationRoute()


    @Serializable
    data object Record : NavigationRoute()

    @Serializable
    data class FormRecord(
        val id: Int
    ) : NavigationRoute()


    @Serializable
    data object Survey : NavigationRoute()

}