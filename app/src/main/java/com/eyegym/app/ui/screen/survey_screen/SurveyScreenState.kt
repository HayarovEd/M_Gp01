package com.eyegym.app.ui.screen.survey_screen

data class SurveyScreenState(
    val effect:Int = -1,
    val material:Int = -1,
    val record:Int = -1,
    val commit: String = "",
    val isSending: Boolean = false
)