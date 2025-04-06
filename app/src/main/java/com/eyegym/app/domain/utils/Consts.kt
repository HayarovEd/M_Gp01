package com.eyegym.app.domain.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

const val APP_PREFERENCES = "app_prefs"
const val COUNT_STARTS = "count_starts"
const val REPEATE_TASK = 500

const val TABLE_FAVORITE = "tb_favorite"
const val TABLE_FAVORITE_ID = "id"
const val DATABASE = "favorite_db"

const val RUSSIAN_PHONE_MASK = "+7 (###) ### ##-##"

fun Long.toDdMmYyyy(): String {
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
}