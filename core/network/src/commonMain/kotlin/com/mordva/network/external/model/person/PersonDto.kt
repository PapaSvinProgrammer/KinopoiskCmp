package com.mordva.network.external.model.person

import com.mordva.network.external.model.movie.FactDto
import com.mordva.network.external.model.movie.ShortMovieDto
import kotlinx.serialization.Serializable

@Serializable
data class PersonDto(
    val id: Int = 0,
    val name: String? = null,
    val enName: String? = null,
    val photo: String? = null,
    val sex: String? = null,
    val growth: Int? = null,
    val birthday: String? = null,
    val death: String? = null,
    val age: Int? = null,
    val countAwards: Int? = null,
    val spouse: List<SpouseDto> = listOf(),
    val birthPlace: List<PlaceDto> = listOf(),
    val deathPlace: List<PlaceDto> = listOf(),
    val profession: List<ProfessionDto> = listOf(),
    val facts: List<FactDto> = listOf(),
    val movies: List<ShortMovieDto> = listOf()
)