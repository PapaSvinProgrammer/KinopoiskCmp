package com.mordva.sqlite.entities.movie

import androidx.room.Embedded
import androidx.room.Relation
import com.mordva.sqlite.entities.movie.entity.AudienceEntity
import com.mordva.sqlite.entities.movie.entity.BudgetEntity
import com.mordva.sqlite.entities.movie.entity.CountryEntity
import com.mordva.sqlite.entities.movie.entity.DistributorEntity
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

data class MovieDetails(
    @Embedded val movie: MovieEntity,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val ratings: List<RatingEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val votes: List<VotesEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val distributors: List<DistributorEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val premieres: List<PremiereEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val budgets: List<BudgetEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val posters: List<PosterEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val facts: List<FactEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val genres: List<GenreEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val countries: List<CountryEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val persons: List<PersonMovieEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val watchability: List<WatchabilityItemEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val audience: List<AudienceEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val releaseYears: List<ReleaseYearsEntity>,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val seasons: List<SeasonEntity>
)
