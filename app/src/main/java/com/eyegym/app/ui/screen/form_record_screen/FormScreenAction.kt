package com.eyegym.app.ui.screen.form_record_screen

sealed interface FormScreenAction {
    class UpdateName(val name: String) : FormScreenAction
    class UpdatePhone(val phone: String) : FormScreenAction
    class UpdateDate(val date: String) : FormScreenAction
    class UpdateTime(val time: String) : FormScreenAction
    class UpdateReason(val reason: String) : FormScreenAction
    data object OnSend : FormScreenAction
}