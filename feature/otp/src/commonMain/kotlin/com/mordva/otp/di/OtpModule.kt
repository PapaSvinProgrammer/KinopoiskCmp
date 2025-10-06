package com.mordva.otp.di

import com.mordva.otp.domain.CreateNewCode
import com.mordva.otp.domain.GetFirstEmptyFieldIndexAfterFocusedIndex
import com.mordva.otp.domain.GetNextFocusedTextFieldIndex
import com.mordva.otp.domain.GetPreviousFocusedIndex
import com.mordva.otp.presentation.OtpViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val otpModule = module {
    viewModelOf(::OtpViewModel)
    factoryOf(::CreateNewCode)
    factoryOf(::GetFirstEmptyFieldIndexAfterFocusedIndex)
    factoryOf(::GetNextFocusedTextFieldIndex)
    factoryOf(::GetPreviousFocusedIndex)
}