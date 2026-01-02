package com.mordva.sqlite.model

data class PersonMovieLocalDto(
    val id: Int,
    val name: String?,
    val enName: String?,
    val photo: String?,
    val description: String?,
    val profession: String?,
    val enProfession: String?
)