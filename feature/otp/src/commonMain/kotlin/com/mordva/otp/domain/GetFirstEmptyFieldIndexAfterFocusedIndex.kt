package com.mordva.otp.domain

internal class GetFirstEmptyFieldIndexAfterFocusedIndex {
    fun execute(
        code: List<Int?>,
        currentFocusedIndex: Int
    ): Int {
        code.forEachIndexed { index, number ->
            if (index <= currentFocusedIndex) {
                return@forEachIndexed
            }

            if (number == null) {
                return index
            }
        }

        return currentFocusedIndex
    }
}
