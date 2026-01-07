package com.mordva.sqlite

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.mordva.sqlite.entities.blocked.BlockedDao
import com.mordva.sqlite.entities.blocked.BlockedEntity
import com.mordva.sqlite.entities.favorite_package.FavoritePackageDao
import com.mordva.sqlite.entities.favorite_package.FavoritePackageEntity
import com.mordva.sqlite.entities.history.HistoryDao
import com.mordva.sqlite.entities.history.HistoryEntity
import com.mordva.sqlite.entities.movie.MovieDao
import com.mordva.sqlite.entities.movie.entity.AudienceEntity
import com.mordva.sqlite.entities.movie.entity.BudgetEntity
import com.mordva.sqlite.entities.movie.entity.CountryEntity
import com.mordva.sqlite.entities.movie.entity.DistributorEntity
import com.mordva.sqlite.entities.movie.entity.EpisodeEntity
import com.mordva.sqlite.entities.movie.entity.FactEntity
import com.mordva.sqlite.entities.movie.entity.GenreEntity
import com.mordva.sqlite.entities.movie.entity.MovieEntity
import com.mordva.sqlite.entities.movie.entity.PersonMovieEntity
import com.mordva.sqlite.entities.movie.entity.PosterEntity
import com.mordva.sqlite.entities.movie.entity.PremiereEntity
import com.mordva.sqlite.entities.movie.entity.RatingEntity
import com.mordva.sqlite.entities.movie.entity.ReleaseYearsEntity
import com.mordva.sqlite.entities.movie.entity.SeasonEntity
import com.mordva.sqlite.entities.movie.entity.VotesEntity
import com.mordva.sqlite.entities.movie.entity.WatchabilityItemEntity
import com.mordva.sqlite.entities.rated.RatedMovieDao
import com.mordva.sqlite.entities.rated.RatedMovieEntity
import com.mordva.sqlite.entities.viewed.ViewedDao
import com.mordva.sqlite.entities.viewed.ViewedEntity
import com.mordva.sqlite.entities.will_watch_package.WillWatchPackageDao
import com.mordva.sqlite.entities.will_watch_package.WillWatchPackageEntity

@Database(
    entities = [
        HistoryEntity::class,
        RatedMovieEntity::class,
        MovieEntity::class,
        RatingEntity::class,
        VotesEntity::class,
        DistributorEntity::class,
        PremiereEntity::class,
        BudgetEntity::class,
        PosterEntity::class,
        FactEntity::class,
        GenreEntity::class,
        CountryEntity::class,
        PersonMovieEntity::class,
        WatchabilityItemEntity::class,
        AudienceEntity::class,
        ReleaseYearsEntity::class,
        SeasonEntity::class,
        EpisodeEntity::class,
        WillWatchPackageEntity::class,
        FavoritePackageEntity::class,
        ViewedEntity::class,
        BlockedEntity::class,
    ],
    version = 1,
    exportSchema = true
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
    abstract fun getRatedDao(): RatedMovieDao
    abstract fun getMovieDao(): MovieDao
    abstract fun getWillWatchPackageDao(): WillWatchPackageDao
    abstract fun getFavoritePackageDao(): FavoritePackageDao
    abstract fun getViewedDao(): ViewedDao
    abstract fun getBlockedDao(): BlockedDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}