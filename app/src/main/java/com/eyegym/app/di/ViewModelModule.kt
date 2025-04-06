package com.eyegym.app.di

import com.eyegym.app.ui.screen.current_tip_screen.CurrentTipScreenViewModel
import com.eyegym.app.ui.screen.favorite_tips_screen.FavoriteScreenViewModel
import com.eyegym.app.ui.screen.form_record_screen.FormScreenViewModel
import com.eyegym.app.ui.screen.record_screen.RecordScreenViewModel
import com.eyegym.app.ui.screen.tips_screen.TripsScreenViewModel
import com.eyegym.app.ui.screen.warmup_screen.WarmUpScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { WarmUpScreenViewModel(get(), get()) }
    viewModel { TripsScreenViewModel(get()) }
    viewModel { FavoriteScreenViewModel(get()) }
    viewModel { CurrentTipScreenViewModel(get(), get()) }
    viewModel { RecordScreenViewModel() }
    viewModel { FormScreenViewModel(get(), get()) }
    /*viewModel { ClientOrderScreenViewModel(get()) }*/

}
