package com.mordva.settings.di

import com.mordva.settings.presentation.confidential.ConfidentialViewModel
import com.mordva.settings.presentation.decor.DecorViewModel
import com.mordva.settings.presentation.sound.SoundViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsModule = module {
    viewModelOf(::SoundViewModel)
    viewModelOf(::DecorViewModel)
    viewModelOf(::ConfidentialViewModel)
}