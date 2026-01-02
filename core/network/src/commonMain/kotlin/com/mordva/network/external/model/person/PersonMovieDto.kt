package com.mordva.network.external.model.person

import kotlinx.serialization.Serializable

@Serializable
data class PersonMovieDto(
    val id: Int = 0,
    val name: String? = null,
    val enName: String? = null,
    val photo: String? = null,
    val description: String? = null,
    val profession: String? = null,
    val enProfession: String? = null
)