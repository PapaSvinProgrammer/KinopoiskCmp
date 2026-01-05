package com.mordva.movie.domain

import com.mordva.domain.model.image.CollectionMovie
import com.mordva.domain.model.person.PersonMovie

internal fun List<CollectionMovie>.filterCollection() = filter { it.slug != "hd" }

internal fun List<PersonMovie>.filterPersonsLikeActors() = filter { it.enProfession == "actor" }

internal fun List<PersonMovie>.filterPersonsLikeSupport() = filter {
    it.enProfession != "actor" && it.enProfession != "voice_actor"
}

internal fun List<PersonMovie>.filterPersonsLikeVoiceActors() = filter {
    it.enProfession == "voice_actor"
}
