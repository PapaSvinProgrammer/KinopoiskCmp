package com.mordva.network.external.model.totalValue

import kotlinx.serialization.Serializable

@Serializable
data class BudgetDto(
    val value: Int?,
    val currency: String?,
)