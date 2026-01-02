package com.mordva.person.presentation.personpodium

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mordva.navigation.PersonGraph
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.widget.other.TitleTopBarText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PersonPodiumListScreen(
    navController: NavController,
    viewModel: PersonPodiumListViewModel,
    title: String,
    queryParameters: List<Pair<String, String>>
) {
    val personState by viewModel.state.collectAsStateWithLifecycle()

//    LifecycleEventEffect(Lifecycle.Event.ON_START) {
//        viewModel.getPersons(queryParameters)
//    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = title)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = PlatformResources.Icons.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
//        RenderPersonResult(
//            modifier = Modifier.padding(innerPadding),
//            state = personState,
//            onClick = {
//                navController.navigate(PersonGraph.PersonRoute(it.id)) {
//                    launchSingleTop = true
//                }
//            },
//            onLoadMore = { viewModel.loadMorePersons(queryParameters) }
//        )
    }
}