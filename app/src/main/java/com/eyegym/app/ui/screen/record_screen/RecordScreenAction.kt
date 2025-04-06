package com.eyegym.app.ui.screen.record_screen

sealed interface RecordScreenAction {
    class UpdateQuery(val query: String) : RecordScreenAction
    data object OnSearch : RecordScreenAction
}