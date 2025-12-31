package com.mordva.sqlite.internal.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.mordva.sqlite.external.BlockedService
import com.mordva.sqlite.external.FavoritePackageService
import com.mordva.sqlite.external.HistoryService
import com.mordva.sqlite.external.RatedMovieService
import com.mordva.sqlite.external.WillWatchPackageService
import com.mordva.sqlite.internal.AppDatabase
import com.mordva.sqlite.internal.entities.blocked.BlockedDao
import com.mordva.sqlite.internal.entities.blocked.BlockedServiceImpl
import com.mordva.sqlite.internal.entities.favorite_package.FavoritePackageDao
import com.mordva.sqlite.internal.entities.favorite_package.FavoritePackageServiceImpl
import com.mordva.sqlite.internal.entities.history.HistoryDao
import com.mordva.sqlite.internal.entities.history.HistoryServiceImpl
import com.mordva.sqlite.internal.entities.movie.MovieDao
import com.mordva.sqlite.internal.entities.movie.MovieLocalServiceImpl
import com.mordva.sqlite.internal.entities.rated.RatedMovieDao
import com.mordva.sqlite.internal.entities.rated.RatedMovieServiceImpl
import com.mordva.sqlite.internal.entities.viewed.ViewedDao
import com.mordva.sqlite.internal.entities.viewed.ViewedServiceImpl
import com.mordva.sqlite.internal.entities.will_watch_package.WillWatchPackageDao
import com.mordva.sqlite.internal.entities.will_watch_package.WillWatchPackageServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val roomModule = module {
    single<AppDatabase> {
        val builder = get<RoomDatabase.Builder<AppDatabase>>()
        builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single<HistoryDao> {
        get<AppDatabase>().getHistoryDao()
    }

    single<RatedMovieDao> {
        get<AppDatabase>().getRatedDao()
    }

    single<MovieDao> {
        get<AppDatabase>().getMovieDao()
    }

    single<WillWatchPackageDao> {
        get<AppDatabase>().getWillWatchPackageDao()
    }

    single<FavoritePackageDao> {
        get<AppDatabase>().getFavoritePackageDao()
    }

    single<ViewedDao> {
        get<AppDatabase>().getViewedDao()
    }

    single<BlockedDao> {
        get<AppDatabase>().getBlockedDao()
    }

    singleOf(::BlockedServiceImpl).bind<BlockedService>()
    singleOf(::FavoritePackageServiceImpl).bind<FavoritePackageService>()
    singleOf(::HistoryServiceImpl).bind<HistoryService>()
    singleOf(::MovieLocalServiceImpl).bind<MovieLocalServiceImpl>()
    singleOf(::RatedMovieServiceImpl).bind<RatedMovieService>()
    singleOf(::ViewedServiceImpl).bind<ViewedServiceImpl>()
    singleOf(::WillWatchPackageServiceImpl).bind<WillWatchPackageService>()
}