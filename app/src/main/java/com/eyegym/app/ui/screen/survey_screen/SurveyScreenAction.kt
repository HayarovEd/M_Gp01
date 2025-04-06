package com.eyegym.app.ui.screen.survey_screen


sealed interface SurveyScreenAction {
    class UpdateEffect(val value: Int): SurveyScreenAction
    class UpdateMaterial(val value: Int): SurveyScreenAction
    class UpdateRecord(val value: Int): SurveyScreenAction
    class UpdateCommit(val value: String): SurveyScreenAction
    data object OnSend : SurveyScreenAction
}