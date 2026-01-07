package com.mordva.sqlite.entities.blocked

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mordva.sqlite.entities.movie.entity.MovieEntity

@Entity(
    tableName = "blocked",
    indices = [Index("movie_id")],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"]
        )
    ]
)
data class BlockedEntity(
    @PrimaryKey @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "date") val date: Long
)