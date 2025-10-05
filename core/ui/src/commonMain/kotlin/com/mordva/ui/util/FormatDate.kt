package com.mordva.ui.util

expect object FormatDate {
    fun getCurrentYear(): Int
    fun formatDate(date: String): String
}
