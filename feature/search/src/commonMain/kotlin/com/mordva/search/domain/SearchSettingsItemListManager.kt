package com.mordva.search.domain

import com.mordva.domain.repository.CategoryRepository
import com.mordva.model.category.ItemName
import com.mordva.search.presentation.search_settings.widget.SearchSettingsListState
import com.mordva.search.presentation.search_settings_list.SearchSettingsListType
import com.mordva.ui.widget.navigation.BottomBarItems.items
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

internal class SearchSettingsItemListManager(
    private val categoryRepository: CategoryRepository,
) {
    private val currentType = MutableStateFlow<SearchSettingsListType?>(null)
    private val query = MutableStateFlow("")
    private val checkedGenres = MutableStateFlow<Map<ItemName, Boolean>>(mapOf())
    private val checkedCountries = MutableStateFlow<Map<ItemName, Boolean>>(mapOf())
    private val genres = MutableStateFlow<List<ItemName>>(listOf())
    private val countries = MutableStateFlow<List<ItemName>>(listOf())

    private val checkedResult = combine(
        currentType,
        checkedCountries,
        checkedGenres,
    ) { type, countries, genres ->
        when (type) {
            SearchSettingsListType.GENRE -> genres
            SearchSettingsListType.COUNTRY -> countries
            null -> mapOf()
        }
    }

    private val searchResult: Flow<List<ItemName>> = combine(
        currentType,
        query,
        genres,
        countries
    ) { type, query, genres, countries ->
        val list = when (type) {
            SearchSettingsListType.GENRE -> genres
            SearchSettingsListType.COUNTRY -> countries
            null -> listOf()
        }

        if (query.isBlank()) {
            list
        } else {
            list.filter { it.name.contains(query, ignoreCase = true) }
        }
    }


    val state: StateFlow<SearchSettingsListState> = combine(
        query,
        searchResult,
        checkedResult,
        ::SearchSettingsListState
    ).stateIn(
        scope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchSettingsListState()
    )

    fun toggleItem(item: ItemName) {
        when (currentType.value) {
            SearchSettingsListType.GENRE -> toggleGenre(item)
            SearchSettingsListType.COUNTRY -> toggleCountry(item)
            null -> Unit
        }
    }

    fun updateQuery(text: String) {
        query.value = text
    }

    fun reset() {

    }

    suspend fun getCountries() {
        categoryRepository.getCounties().onSuccess { data ->
            countries.value = data
        }
    }

    suspend fun getGenres() {
        categoryRepository.getGenres().onSuccess { data ->
            genres.value = data
        }
    }

    private fun toggleGenre(genre: ItemName) {
        val currentChecked = checkedGenres.value[genre] ?: false

        checkedGenres.value = checkedGenres.value.toMutableMap().apply {
            this[genre] = !currentChecked
        }
    }


    private fun toggleCountry(country: ItemName) {
        val currentChecked = checkedCountries.value[country] ?: false

        checkedCountries.value = checkedCountries.value.toMutableMap().apply {
            this[country] = !currentChecked
        }
    }
}