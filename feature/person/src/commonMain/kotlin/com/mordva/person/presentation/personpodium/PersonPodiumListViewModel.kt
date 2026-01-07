package com.mordva.person.presentation.personpodium

import com.mordva.util.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class PersonPodiumListViewModel : BaseViewModel<Unit>() {
    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()
}