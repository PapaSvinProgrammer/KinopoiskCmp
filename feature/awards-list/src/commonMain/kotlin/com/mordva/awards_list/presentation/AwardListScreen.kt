package com.mordva.awards_list.presentation

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
import com.mordva.awards_list.presentation.widget.bottomSheet.AwardsFilterSheet
import com.mordva.awards_list.presentation.widget.component.RenderAwardsContent
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.other.TitleTopBarText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AwardListScreen(
    navController: NavController,
    viewModel: AwardListViewModel,
    id: Int,
    isMovie: Boolean
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(Resources.Strings.Awards))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = PlatformResources.Icons.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.updateVisibleBottomSheet(true) }) {
                        Icon(
                            painter = painterResource(Resources.Icons.Filter),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LifecycleEventEffect(Lifecycle.Event.ON_START) {
            if (uiState.groupAwards.isEmpty()) {
                viewModel.getAwards(id, isMovie)
            }
        }

        RenderAwardsContent(
            modifier = Modifier.padding(innerPadding),
            list = uiState.groupAwards,
            onClick = {},
            onLoadMore = { viewModel.loadMoreAwards(id, isMovie) }
        )

        if (uiState.visibleBottomSheet) {
            AwardsFilterSheet(
                current = uiState.currentFilterType,
                onClick = {
                    viewModel.updateCurrentFilter(it)
                    viewModel.getAwards(id, isMovie)
                    viewModel.updateVisibleBottomSheet(false)
                },
                onDismissRequest = { viewModel.updateVisibleBottomSheet(false) }
            )
        }
    }
}