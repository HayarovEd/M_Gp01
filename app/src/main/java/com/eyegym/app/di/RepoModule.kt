package com.eyegym.app.di

import com.eyegym.app.data.repository.AndroidServiceController
import com.eyegym.app.data.repository.DataStoreRepositoryImpl
import com.eyegym.app.domain.repository.DataStoreRepository
import com.eyegym.app.domain.repository.ServiceController
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repoModule = module {
    singleOf(::AndroidServiceController) { bind<ServiceController>() }
    singleOf(::DataStoreRepositoryImpl) { bind<DataStoreRepository>() }

}
