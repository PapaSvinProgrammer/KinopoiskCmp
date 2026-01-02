package com.mordva.sqlite.entities.rated

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mordva.sqlite.entities.movie.entity.MovieEntity

@Entity(
    tableName = "rated_movies",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"]
        )
    ],
    indices = [Index("movie_id")]
)
data class RatedMovieEntity(
    @PrimaryKey @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "date") val date: Long
)