package com.mordva.awards_list.domain

import com.mordva.domain.model.person.NominationAward
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers

internal class FilterAwardsByGroup(
) : UseCase<List<NominationAward>, List<Pair<String, List<NominationAward>>>>(Dispatchers.Default) {
    override suspend fun run(params: List<NominationAward>): List<Pair<String, List<NominationAward>>> {
        return params.groupBy { award ->
            award.nomination?.award?.title + ", " + award.nomination?.award?.year
        }.toList()
    }
}
