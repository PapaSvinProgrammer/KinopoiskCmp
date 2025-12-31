package com.mordva.movie.domain

import com.mordva.movie.domain.model.PersonMovieExtended
import com.mordva.movie.domain.model.UnionParams
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers

internal class UnionPersonsAndPersonMovie(
) : UseCase<UnionParams, List<PersonMovieExtended>>(Dispatchers.Default) {
    override suspend fun run(params: UnionParams): List<PersonMovieExtended> {
        val personsMap = params.personsList.associateBy { it.id }

        return params.personsScreenObjectList.mapNotNull { screenObject ->
            personsMap[screenObject.id]?.let { person ->
                PersonMovieExtended(
                    id = person.id,
                    name = person.name,
                    enName = person.enName,
                    photo = person.photo,
                    birthday = person.birthday,
                    death = person.death,
                    age = person.age,
                    description = screenObject.description,
                    enProfession = screenObject.enProfession,
                    profession = screenObject.profession
                )
            }
        }
    }
}
