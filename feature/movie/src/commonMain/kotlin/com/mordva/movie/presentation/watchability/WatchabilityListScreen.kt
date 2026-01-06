package com.mordva.movie.presentation.watchability

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.movie.domain.model.WatchabilityScreenObject
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.theme.Strings
import com.mordva.ui.widget.listItems.poster.SquareImage
import com.mordva.ui.widget.other.TitleTopBarText
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchabilityListScreen(
    watchability: WatchabilityScreenObject,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(stringResource(Strings.WhereCanWatch))
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = PlatformResources.PlatformIcons.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            items(watchability.items) { watchabilityItem ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = watchabilityItem.name.toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    leadingContent = {
                        SquareImage(
                            model = watchabilityItem.logo?.url,
                            modifier = Modifier
                                .size(45.dp)
                                .clip(CircleShape)
                        )
                    },
                    modifier = Modifier.clickable {
//                        val url = watchabilityItem.url ?: ""
//                        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
//                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}
