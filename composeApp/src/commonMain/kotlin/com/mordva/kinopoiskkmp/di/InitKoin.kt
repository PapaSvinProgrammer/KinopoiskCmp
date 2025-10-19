package com.mordva.kinopoiskkmp.di

import com.mordva.awards_list.di.awardsListModule
import com.mordva.collection_list.di.collectionModule
import com.mordva.data.di.dataModule
import com.mordva.data.di.dataModulePlatform
import com.mordva.home.di.homeModule
import com.mordva.images_list.di.imageListViewModel
import com.mordva.movie.di.movieModule
import com.mordva.movie_list.di.movieListModule
import com.mordva.network.internal.di.networkModule
import com.mordva.network.internal.di.networkModulePlatform
import com.mordva.otp.di.otpModule
import com.mordva.person.di.personModule
import com.mordva.search.di.searchModule
import com.mordva.security.internal.di.securityModulePlatform
import com.mordva.settings.di.settingsModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

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

            homeModule,
            awardsListModule,
            collectionModule,
            movieModule,
            searchModule,
            personModule,
            otpModule,
            settingsModule,
            imageListViewModel,
            movieListModule,
        )
    }
}