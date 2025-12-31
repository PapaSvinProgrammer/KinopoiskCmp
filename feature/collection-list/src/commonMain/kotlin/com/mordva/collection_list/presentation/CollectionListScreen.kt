package com.mordva.collection_list.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mordva.collection_list.presentation.widget.content.MainCollectionContent
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.other.TitleTopBarText
import kinopoiskcmp.feature.collection_list.generated.resources.Res
import kinopoiskcmp.feature.collection_list.generated.resources.collection
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CollectionListScreen(
    navController: NavController,
    viewModel: CollectionListViewModel,
) {
    val collectionState by viewModel.state.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getAllCollections()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(stringResource(Resources.Strings.Collections))
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
        MainCollectionContent(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            collectionState = collectionState.collectionState,
            navController = navController,
            onLoadMore = { viewModel.loadMoreAllCollections() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CollectionListScreen(
    navController: NavController,
    viewModel: CollectionListViewModel,
    category: String
) {
    val collectionState by viewModel.state.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getCollectionsByCategory(category)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(stringResource(Res.string.collection, category))
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
        MainCollectionContent(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            collectionState = collectionState.collectionState,
            navController = navController,
            onLoadMore = { viewModel.loadMoreCollectionsByCategory(category) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CollectionListScreen(
    navController: NavController,
    viewModel: CollectionListViewModel,
    listId: List<String>
) {
    val collectionState by viewModel.state.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getCollectionsByListId(listId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(stringResource(Resources.Strings.Collections))
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
        MainCollectionContent(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            collectionState = collectionState.collectionState,
            navController = navController
        )
    }
}
