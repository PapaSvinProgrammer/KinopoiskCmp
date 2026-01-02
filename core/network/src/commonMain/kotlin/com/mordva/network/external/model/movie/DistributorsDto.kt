package com.mordva.network.external.model.movie

import kotlinx.serialization.Serializable

@Serializable
data class DistributorsDto(
    val distributor: String?,
    val distributorRelease: String?,
)
