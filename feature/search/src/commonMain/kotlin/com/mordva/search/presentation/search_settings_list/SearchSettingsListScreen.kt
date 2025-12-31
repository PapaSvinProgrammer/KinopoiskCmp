package com.mordva.search.presentation.search_settings_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mordva.model.category.ItemName
import com.mordva.search.presentation.widget.component.DefaultCustomSearchBar
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.theme.Resources
import com.mordva.ui.theme.Typography
import com.mordva.ui.widget.other.TitleTopBarText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchSettingsListScreen(
    type: SearchSettingsListType,
    viewModel: SearchSettingsListViewModel,
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(type.title))
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = PlatformResources.Icons.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = { viewModel.reset() }
                    ) {
                        Text(
                            text = stringResource(Resources.Strings.Reset),
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        MainContent(
            query = uiState.query,
            result = uiState.result,
            checked = uiState.checked,
            onClick = { viewModel.toggleItem(it) },
            onQueryUpdate = { viewModel.updateQuery(it) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun MainContent(
    query: String,
    result: List<ItemName>,
    checked: Map<ItemName, Boolean>,
    onClick: (ItemName) -> Unit,
    onQueryUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        DefaultCustomSearchBar(
            query = query,
            onValueChange = onQueryUpdate,
        )

        Spacer(modifier = Modifier.height(15.dp))

        MainLazyColumn(
            result = result,
            checked = checked,
            onClick = onClick
        )
    }
}

@Composable
private fun MainLazyColumn(
    result: List<ItemName>,
    checked: Map<ItemName, Boolean>,
    onClick: (ItemName) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(
            items = result,
            key = { it.slug },
        ) { item ->
            ListItem(
                headlineContent = {
                    Text(
                        text = item.name,
                        fontSize = Typography.bodyMedium.fontSize,
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick(item) },
                trailingContent = {
                    if (checked[item] == true) {
                        Icon(
                            painter = painterResource(Resources.Icons.Check),
                            contentDescription = null
                        )
                    }
                }
            )

            HorizontalDivider()
        }
    }
}

@Composable
private fun BoxScope.SuccessButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(30.dp),
        onClick = onClick
    ) {
        Text(
            text = stringResource(Resources.Strings.Show),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private val SearchSettingsListType.title get() = when (this) {
    SearchSettingsListType.GENRE -> Resources.Strings.Genres
    SearchSettingsListType.COUNTRY -> Resources.Strings.Countries
}