package com.mordva.network.internal.service

import com.mordva.network.external.PersonService
import com.mordva.network.internal.core.LIMIT_API_COUNT
import com.mordva.network.internal.core.safeCall
import com.mordva.network.external.model.Docs
import com.mordva.network.external.model.person.PersonDto
import com.mordva.util.Constants.LIMIT_FIELD
import com.mordva.util.Constants.PAGE_FIELD
import com.mordva.util.Constants.QUERY_FIELD
import com.mordva.util.Constants.SELECT_FILED
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class PersonServiceImpl(
    private val client: HttpClient
) : PersonService {
    override suspend fun getPersonById(personId: Int): Result<PersonDto> {
        return safeCall<PersonDto> {
            client.get("v1.4/person/$personId")
        }
    }

    override suspend fun searchPersonByName(
        q: String,
        page: Int
    ): Result<List<PersonDto>> {
        return safeCall<Docs<PersonDto>> {
            client.get("v1.4/person/search") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(QUERY_FIELD, q)
                    parameters.append(PAGE_FIELD, page.toString())
                }
            }
        }.map { doc -> doc.docs }
    }

    override suspend fun getPersonByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<PersonDto>> {
        val selectsList = listOf("id", "name", "enName", "photo", "sex", "birthday", "age")

        return safeCall<Docs<PersonDto>> {
            client.get("v1.4/person") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    selectsList.forEach { parameters.append(SELECT_FILED, it) }
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { doc -> doc.docs }
    }
}