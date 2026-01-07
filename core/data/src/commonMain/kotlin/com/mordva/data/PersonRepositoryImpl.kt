package com.mordva.data

import com.mordva.data.mapper.toDomain
import com.mordva.domain.model.person.Person
import com.mordva.domain.repository.PersonRepository
import com.mordva.network.external.PersonService

internal class PersonRepositoryImpl(
    private val service: PersonService
) : PersonRepository {
    override suspend fun getPersonById(personId: Int): Result<Person> {
        return service.getPersonById(personId).map { it.toDomain() }
    }

    override suspend fun getPersonByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Person>> {
        return service.getPersonByFilter(queryParameters).map { it.toDomain() }
    }

    override suspend fun searchPersonByName(
        q: String,
        page: Int
    ): Result<List<Person>> {
        return service.searchPersonByName(
            q = q,
            page = page
        ).map { it.toDomain() }
    }
}