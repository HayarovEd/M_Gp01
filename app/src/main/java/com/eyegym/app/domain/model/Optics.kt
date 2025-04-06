package com.eyegym.app.domain.model

import java.time.LocalTime

data class Optics(
    val id: Int,
    val name: String,
    val address: String,
    val openTime: LocalTime,
    val closeTime: LocalTime
)

val mockOptics = listOf(
    Optics(
        id = 1,
        name = "Визус",
        address = "проспект Мира, 40, Омск",
        openTime = LocalTime.of(10, 0),
        closeTime = LocalTime.of(19, 0)
    ),
    Optics(
        id = 2,
        name = "Optilens",
        address = "проспект Мира, 19, Омск",
        openTime = LocalTime.of(10, 0),
        closeTime = LocalTime.of(19, 0)
    ),
    Optics(
        id = 3,
        name = "STILNO",
        address = "проспект Мира, 9Б, Омск",
        openTime = LocalTime.of(10, 0),
        closeTime = LocalTime.of(19, 0)
    ),
    Optics(
        id = 4,
        name = "Монель",
        address = "проспект Мира, 40, Омск",
        openTime = LocalTime.of(10, 0),
        closeTime = LocalTime.of(19, 0)
    ),
    Optics(
        id = 5,
        name = "Vidial",
        address = "проспект Мира, 62, Омск",
        openTime = LocalTime.of(10, 0),
        closeTime = LocalTime.of(19, 0)
    ),
    Optics(
        id = 6,
        name = "mosoptika-servis",
        address = "проспект Мира, 33А, Омск",
        openTime = LocalTime.of(10, 0),
        closeTime = LocalTime.of(19, 0)
    ),
)
