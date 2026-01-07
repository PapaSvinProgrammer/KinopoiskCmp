package com.mordva.movie.domain

import com.mordva.movie.domain.model.PersonMovieExtended

internal fun List<PersonMovieExtended>.groupPersonsByProfession() = groupBy {
    it.enProfession ?: " "
}
