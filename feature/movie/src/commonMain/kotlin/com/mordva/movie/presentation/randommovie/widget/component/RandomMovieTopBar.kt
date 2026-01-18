package com.mordva.movie.presentation.randommovie.widget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.mordva.movie.presentation.randommovie.widget.RandomMovieAction
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.Icons
import com.mordva.ui.theme.haze.hazeBorder
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun RandomMovieTopBar(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    onAction: (RandomMovieAction) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .statusBarsPadding()
            .height(DsSpacer.M64)
            .fillMaxWidth()
            .padding(horizontal = DsSpacer.M4)
    ) {
        IconButton(
            onClick = { onAction(RandomMovieAction.TopBarAction.GoBackClicked) },
            modifier = Modifier
                .clip(CircleShape)
                .hazeBorder()
                .hazeEffect(hazeState)
        ) {
            Icon(
                painter = painterResource(Icons.Close),
                contentDescription = null,
            )
        }

        IconButton(
            onClick = { },
            modifier = Modifier
                .clip(CircleShape)
                .hazeBorder()
                .hazeEffect(hazeState)
        ) {
            Icon(
                painter = painterResource(Icons.Settings),
                contentDescription = null,
            )
        }
    }
}