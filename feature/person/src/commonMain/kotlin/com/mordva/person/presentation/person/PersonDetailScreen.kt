package com.mordva.person.presentation.person

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mordva.navigation.PersonGraph
import com.mordva.person.presentation.person.widget.PersonListUIState
import com.mordva.person.presentation.widget.content.SpouseContent
import com.mordva.person.presentation.person.widget.render.RenderPersonDetailState
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.widget.other.TitleTopBarText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PersonDetailScreen(
    navController: NavController,
    viewModel: PersonViewModel,
    id: Int
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getPerson(id)
    }

    LaunchedEffect(uiState.personState) {
        (uiState.personState as? PersonListUIState.Success)?.data?.let { persons ->
            viewModel.getSpouses(persons.first().spouses.map { it.id })
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(
                        text = (uiState.personState as? PersonListUIState.Success)
                            ?.data
                            ?.first()
                            ?.name ?: ""
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = PlatformResources.PlatformIcons.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            RenderPersonDetailState(uiState.personState)

            (uiState.personState as? PersonListUIState.Success)?.data?.let { data ->
                if (data.first().spouses.isEmpty()) return@Scaffold

                SpouseContent(
                    state = uiState.personSpouseState,
                    spouses = data.first().spouses,
                    onClick = {
                        navController.navigate(PersonGraph.PersonRoute(it)) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}