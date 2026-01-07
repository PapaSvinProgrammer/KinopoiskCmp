package com.mordva.data.mapper

import com.mordva.domain.model.person.Award
import com.mordva.domain.model.person.Nomination
import com.mordva.domain.model.person.NominationAward
import com.mordva.domain.model.person.Person
import com.mordva.domain.model.person.PersonMovie
import com.mordva.domain.model.person.Place
import com.mordva.domain.model.person.Profession
import com.mordva.domain.model.person.Spouse
import com.mordva.network.external.model.person.AwardDto
import com.mordva.network.external.model.person.NominationAwardDto
import com.mordva.network.external.model.person.NominationDto
import com.mordva.network.external.model.person.PersonDto
import com.mordva.network.external.model.person.PersonMovieDto
import com.mordva.network.external.model.person.PlaceDto
import com.mordva.network.external.model.person.ProfessionDto
import com.mordva.network.external.model.person.SpouseDto
import kotlin.jvm.JvmName

internal fun AwardDto.toDomain(): Award = Award(
    title = this.title,
    year = this.year
)

internal fun NominationAwardDto.toDomain(): NominationAward = NominationAward(
    nomination = this.nomination?.toDomain(),
    winning = this.winning,
    personId = this.personId,
    movie = this.movie?.toDomain()
)

@JvmName("listNominationAwardDtoToDomain")
internal fun List<NominationAwardDto>.toDomain(): List<NominationAward> = map { it.toDomain() }

internal fun NominationDto.toDomain(): Nomination = Nomination(
    award = this.award?.toDomain(),
    title = this.title
)

internal fun PersonDto.toDomain(): Person = Person(
    id = id,
    name = name,
    enName = enName,
    photo = photo,
    sex = sex,
    growth = growth,
    birthday = birthday,
    death = death,
    age = age,
    countAwards = countAwards,
    spouses = spouse.map { it.toDomain() },
    birthPlace = birthPlace.map { it.toDomain() },
    deathPlace = deathPlace.map { it.toDomain() },
    professions = profession.map { it.toDomain() },
    facts = facts.map { it.toDomain() },
    movies = movies.map { it.toDomain() }
)

@JvmName("listPersonDtoToDomain")
internal fun List<PersonDto>.toDomain() = map { it.toDomain() }

internal fun PersonMovieDto.toDomain(): PersonMovie = PersonMovie(
    id = id,
    name = name,
    enName = enName,
    photo = photo,
    description = description,
    profession = profession,
    enProfession = enProfession
)

internal fun PlaceDto.toDomain(): Place = Place(
    value = value
)

internal fun ProfessionDto.toDomain(): Profession = Profession(
    value = value
)

internal fun SpouseDto.toDomain(): Spouse = Spouse(
    id = id,
    name = name,
    children = children,
    divorced = divorced,
    relation = relation
)
