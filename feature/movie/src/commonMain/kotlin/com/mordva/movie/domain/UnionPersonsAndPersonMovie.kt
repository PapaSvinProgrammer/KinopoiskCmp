package com.mordva.movie.domain

import com.mordva.domain.model.person.Person
import com.mordva.movie.domain.model.PersonMovieExtended
import com.mordva.movie.domain.model.PersonMovieScreenObject

internal fun List<Person>.mergeWith(
    screenObjects: List<PersonMovieScreenObject>
): List<PersonMovieExtended> {
    val personsMap = associateBy(Person::id)

    return screenObjects.mapNotNull { screen ->
        personsMap[screen.id]?.let { person ->
            PersonMovieExtended(
                id = person.id,
                name = person.name,
                enName = person.enName,
                photo = person.photo,
                birthday = person.birthday,
                death = person.death,
                age = person.age,
                description = screen.description,
                enProfession = screen.enProfession,
                profession = screen.profession
            )
        }
    }
}
