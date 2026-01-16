package com.mordva.domain.model

data class Range<T : Comparable<T>>(val min: T? = null, val max: T? = null) {
    fun isEmpty() = min == null && max == null

    fun toApiString(): String? = when {
        min != null && max != null -> "$min-$max"
        min != null -> "$min-"
        max != null -> "-$max"
        else -> null
    }
}

data class DateRange(val from: String? = null, val to: String? = null) {  // "YYYY-MM-DD"
    fun toApiString(): String? = when {
        from != null && to != null -> "$from-$to"
        from != null -> "$from-"
        to != null -> "-$to"
        else -> null
    }
}