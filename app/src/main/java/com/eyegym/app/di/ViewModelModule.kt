package com.eyegym.app.di

import com.eyegym.app.ui.screen.warmup_screen.WarmUpScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WarmUpScreenViewModel(get(), get()) }
    /*viewModel { RegistrationScreenViewModel(get(), get()) }
    viewModel { AdminProfileScreenViewModel() }
    viewModel { ClientProfileScreenViewModel() }
    viewModel { ClientExcursionScreenViewModel() }
    viewModel { ExcursionScreenViewModel(get(), get()) }
    viewModel { ClientOrderScreenViewModel(get()) }*/

}
