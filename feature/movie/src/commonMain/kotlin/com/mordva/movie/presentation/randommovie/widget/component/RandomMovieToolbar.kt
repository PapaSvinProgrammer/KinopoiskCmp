package com.mordva.movie.presentation.randommovie.widget.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mordva.movie.presentation.randommovie.widget.RandomMovieAction
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.Icons
import com.mordva.ui.theme.haze.hazeBorder
import com.mordva.util.windowWidthPercent
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.rememberHazeState
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun RandomMovieToolbar(
    isExpanded: Boolean,
    onAction: (RandomMovieAction.BottomBarAction) -> Unit,
    hazeState: HazeState,
    modifier: Modifier = Modifier,
) {
    val toolbarWidth = windowWidthPercent(0.7f)

    Box(modifier = Modifier.fillMaxWidth()) {
        CustomHorizontalFloatingToolbar(
            expanded = isExpanded,
            hazeState = hazeState,
            floatingActionButton = {
                IconButton(
                    onClick = { onAction(RandomMovieAction.BottomBarAction.SearchClamped) },
                    modifier = Modifier
                        .hazeBorder()
                        .hazeEffect(hazeState)
                ) {
                    Icon(
                        painter = painterResource(Icons.Search),
                        contentDescription = null,
                    )
                }
            },
            modifier = modifier
                .width(toolbarWidth)
                .align(Alignment.Center)
        ) {
            IconButton(onClick = { onAction(RandomMovieAction.BottomBarAction.SearchClicked) }) {
                Icon(
                    painter = painterResource(Icons.Star),
                    contentDescription = null,
                )
            }

            IconButton(onClick = { onAction(RandomMovieAction.BottomBarAction.MoviePackageAdded) }) {
                Icon(
                    painter = painterResource(Icons.BookmarkAdd),
                    contentDescription = null,
                )
            }

            IconButton(onClick = { onAction(RandomMovieAction.BottomBarAction.MoviePackageAdded) }) {
                Icon(
                    painter = painterResource(Icons.Filter),
                    contentDescription = null,
                )
            }

            IconButton(onClick = { /* more */ }) {
                Icon(
                    painter = painterResource(Icons.MoreHoriz),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
internal fun CustomHorizontalFloatingToolbar(
    expanded: Boolean,
    modifier: Modifier = Modifier,
    hazeState: HazeState = rememberHazeState(),
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable RowScope.() -> Unit = {},
) {
    Row(
        modifier = modifier.padding(horizontal = DsSpacer.M8, vertical = DsSpacer.M6),
        horizontalArrangement = Arrangement.End
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = enterAnimation(),
            exit = exitAnimation()
        ) {
            Row(
                modifier = Modifier
                    .height(DsSpacer.M48)
                    .hazeBorder()
                    .hazeEffect(hazeState),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(DsSpacer.M4)
            ) { content() }
        }

        Spacer(modifier = Modifier.width(DsSpacer.M10))

        floatingActionButton()
    }
}

private fun enterAnimation() = slideInHorizontally(
    initialOffsetX = { fullWidth -> fullWidth },
    animationSpec = spring(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow
    )
) + fadeIn(
    animationSpec = tween(300, delayMillis = 60)
)


private fun exitAnimation() = slideOutHorizontally(
    targetOffsetX = { fullWidth -> fullWidth },
    animationSpec = spring(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessLow
    )
) + fadeOut(
    animationSpec = tween(300, delayMillis = 60)
)