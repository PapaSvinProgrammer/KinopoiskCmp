package com.mordva.network.external

import com.mordva.network.external.model.person.PersonDto

interface PersonService {
    suspend fun getPersonById(personId: Int): Result<PersonDto>

    suspend fun searchPersonByName(
        q: String,
        page: Int
    ): Result<List<PersonDto>>

    suspend fun getPersonByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<PersonDto>>
}