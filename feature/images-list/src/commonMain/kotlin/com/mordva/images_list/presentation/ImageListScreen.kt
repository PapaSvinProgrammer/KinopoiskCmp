package com.mordva.images_list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.mordva.images_list.util.imageTypeDropDownItems
import com.mordva.images_list.util.isLongImage
import com.mordva.images_list.util.toAspectRatio
import com.mordva.images_list.util.toStringResource
import com.mordva.model.image.ImageType
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.theme.Resources
import com.mordva.ui.theme.Typography
import com.mordva.ui.uiState.ImageUIState
import com.mordva.ui.widget.component.BasicLoadingBox
import com.mordva.ui.widget.component.customDropDownList.DropDownItem
import com.mordva.ui.widget.component.customDropDownList.SelectableMultiDropDownList
import com.mordva.ui.widget.lazyComponent.EndlessLazyVerticalStaggeredGrid
import com.mordva.ui.widget.other.TitleTopBarText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ImageListScreen(
    navController: NavController,
    viewModel: ImageListViewModel,
    movieId: Int
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.getImages(movieId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleTopBarText(stringResource(Resources.Strings.AllImages)) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(Resources.Strings.FilterImagesTitle),
                    fontSize = Typography.bodyMedium.fontSize
                )

                SelectableMultiDropDownList(
                    current = state.imageTypes.toDropDownItem(),
                    list = imageTypeDropDownItems(),
                    onClick = { item ->
                        viewModel.updateImageTypes((item.type as? ImageType) ?: ImageType.ALL)
                        viewModel.getImages(movieId)
                    }
                )
            }

            RenderMainContent(
                imageState = state.imagesState,
                onLoadMore = { viewModel.loadMoreImages(movieId) }
            )
        }
    }
}

@Composable
private fun RenderMainContent(
    imageState: ImageUIState,
    onLoadMore: () -> Unit
) {
    when (imageState) {
        ImageUIState.Loading -> BasicLoadingBox()
        is ImageUIState.Success -> {
            EndlessLazyVerticalStaggeredGrid(
                list = imageState.data,
                columns = StaggeredGridCells.Fixed(2),
                onLoadMore = onLoadMore,
                key = { it.id.toString() },
                span = { poster ->
                    if (isLongImage(poster.height, poster.width)) {
                        StaggeredGridItemSpan.FullLine
                    } else {
                        StaggeredGridItemSpan.SingleLane
                    }
                }
            ) { poster ->
                val aspectRatio = toAspectRatio(poster.height, poster.width)

                AsyncImage(
                    model = poster.url,
                    placeholder = painterResource(Resources.Icons.Movie),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(aspectRatio)
                )
            }
        }
    }
}

@Composable
private fun Set<ImageType>.toDropDownItem(): List<DropDownItem> {
    return map { type ->
        val resource = type.toStringResource()
        DropDownItem(
            text = stringResource(resource),
            type = type
        )
    }
}
