package com.eyegym.app.ui.screen.form_record_screen

import com.eyegym.app.domain.model.Optics

data class FormScreenState(
    val optics: Optics? = null,
    val name: String = "",
    val phone: String = "",
    val dateRecord: String = "--.--.----",
    val timeRecord: String = "--:--",
    val reason: String = "",
    val isSending: Boolean = false
)