package com.mordva.movie.domain.model

data class CommentParams(
    val movieId: Int,
    val page: Int = 1,
    val sort: Int = 1 // 1 or -1
)