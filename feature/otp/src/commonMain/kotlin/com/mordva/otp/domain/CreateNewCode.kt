package com.mordva.otp.domain

internal class CreateNewCode() {
    fun execute(
        code: List<Int?>,
        number: Int?,
        index: Int
    ): List<Int?> {
        return code.mapIndexed { currentIndex, currentNumber ->
            if (currentIndex == index) {
                number
            } else {
                currentNumber
            }
        }
    }
}
