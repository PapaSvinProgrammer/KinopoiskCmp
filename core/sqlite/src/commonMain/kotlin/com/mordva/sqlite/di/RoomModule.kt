package com.mordva.sqlite.di

import com.mordva.sqlite.AppDatabase
import com.mordva.sqlite.entities.blocked.BlockedDao
import com.mordva.sqlite.entities.favorite_package.FavoritePackageDao
import com.mordva.sqlite.entities.history.HistoryDao
import com.mordva.sqlite.entities.movie.MovieDao
import com.mordva.sqlite.entities.movie.MovieLocalService
import com.mordva.sqlite.entities.rated.RatedMovieDao
import com.mordva.sqlite.entities.viewed.ViewedDao
import com.mordva.sqlite.entities.will_watch_package.WillWatchPackageDao
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val roomModule = module {
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

    singleOf(::MovieLocalService)
}