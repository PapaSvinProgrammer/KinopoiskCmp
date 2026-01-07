package com.mordva.sqlite.entities.movie

import com.mordva.sqlite.model.MovieLocalDto
import com.mordva.sqlite.utils.toCountryEntities
import com.mordva.sqlite.utils.toEntities
import com.mordva.sqlite.utils.toEntity
import com.mordva.sqlite.utils.toEpisodeEntities
import com.mordva.sqlite.utils.toGenreEntities
import com.mordva.sqlite.utils.toMovieEntity
import com.mordva.sqlite.utils.toSeasonEntities

class MovieLocalService(
    private val movieDao: MovieDao
) {
    suspend fun insert(movie: MovieLocalDto) {
        val movieEntity = movie.toMovieEntity()
        movieDao.insertMovie(movieEntity)

        movieDao.insertRatings(movie.rating.toEntity(movie.id))
        movieDao.insertVotes(movie.votes.toEntity(movie.id))
        movieDao.insertDistributors(movie.distributors.toEntity(movie.id))
        movieDao.insertPremieres(movie.premiere.toEntity(movie.id))
        movieDao.insertBudgets(movie.budget.toEntity(movie.id))

        movieDao.insertPosters(movie.poster.toEntity(movie.id, "poster"))
        movieDao.insertPosters(movie.backdrop.toEntity(movie.id, "backdrop"))
        movieDao.insertPosters(movie.logo.toEntity(movie.id, "logo"))

        movieDao.insertFacts(movie.facts.toEntities(movie.id))
        movieDao.insertGenres(movie.genres.toGenreEntities(movie.id))
        movieDao.insertCountries(movie.countries.toCountryEntities(movie.id))
        movieDao.insertPersons(movie.persons.toEntities(movie.id))
        movieDao.insertWatchabilityItems(movie.watchability.items.toEntities(movie.id))
        movieDao.insertAudience(movie.audience.toEntities(movie.id))
        movieDao.insertReleaseYears(movie.releaseYears.toEntities(movie.id))

        val seasons = movie.seasonsInfo?.toSeasonEntities() ?: emptyList()
        movieDao.insertSeasons(seasons)

        val episodes = movie.seasonsInfo?.toEpisodeEntities(seasons) ?: emptyList()
        movieDao.insertEpisodes(episodes)
    }

    suspend fun getMovie(movieId: Int): MovieDetails? {
        val movieWithDetails = movieDao.getMovieWithDetails(movieId) ?: return null
        return movieWithDetails
    }
}
