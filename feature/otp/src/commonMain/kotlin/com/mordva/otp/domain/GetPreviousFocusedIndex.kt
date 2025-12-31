package com.mordva.otp.domain

internal class GetPreviousFocusedIndex {
    fun execute(currentIndex: Int?): Int? {
        return currentIndex?.minus(1)?.coerceAtLeast(0)
    }
}