package com.mordva.movie.domain.model

import com.mordva.domain.model.person.Person

internal data class UnionParams(
    val personsList: List<Person>,
    val personsScreenObjectList: List<PersonMovieScreenObject>
)
