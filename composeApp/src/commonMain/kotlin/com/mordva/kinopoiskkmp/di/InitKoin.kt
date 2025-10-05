package com.mordva.kinopoiskkmp.di

import com.mordva.data.di.dataModule
import com.mordva.data.di.dataModulePlatform
import com.mordva.network.internal.di.networkModule
import com.mordva.network.internal.di.networkModulePlatform
import com.mordva.search.di.searchModule
import com.mordva.security.internal.di.securityModulePlatform
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.mordva.kinopoiskdev.di.appModule

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this@startKoin)
        modules(
            appModule,

            networkModule,
            networkModulePlatform,

            dataModule,
            dataModulePlatform,

            securityModulePlatform,

//            homeModule,
//            awardsListModule,
//            collectionModule,
//            movieModule,
            searchModule,
//            personModule,
//            otpModule,
//            settingsModule
        )
    }
}