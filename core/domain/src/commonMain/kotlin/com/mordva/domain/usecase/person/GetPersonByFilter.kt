package com.mordva.domain.usecase.person

import com.mordva.domain.repository.PersonRepository
import com.mordva.model.person.Person
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class GetPersonByFilter(
    private val personRepository: PersonRepository
) : UseCase<List<Pair<String, String>>, Result<List<Person>>>(Dispatchers.IO) {
    override suspend fun run(params: List<Pair<String, String>>): Result<List<Person>> {
        return personRepository.getPersonByFilter(params)
    }
}
