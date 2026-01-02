package com.mordva.ui.widget.renderState

data class RenderStateRowItemMovie<T : Any>(
    val id: T,
    val title: String,
    val image: String,
    val rating: Float?,
    val top250: Int?,
)