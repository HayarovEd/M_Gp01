package com.eyegym.app.domain.repository

interface ServiceController {
    fun vibratePhone(durationMillis: Long = 300)
}