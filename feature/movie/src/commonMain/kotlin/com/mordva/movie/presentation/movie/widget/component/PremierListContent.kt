package com.mordva.movie.presentation.movie.widget.component

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.totalValue.Premiere
import com.mordva.movie.presentation.movie.widget.listItem.PremiereCard
import com.mordva.ui.theme.Strings
import com.mordva.ui.util.FormatDate
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun PremierListContent(premiere: Premiere) {
    val state = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(state, SnapPosition.Start)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 15.dp),
        state = state,
        flingBehavior = flingBehavior
    ) {
        items(premiere.toMap().toList()) { pair ->
            pair.second?.let {
                PremiereCard(
                    title = FormatDate.formatDate(it),
                    description = stringResource(pair.first.toNormalText())
                )

                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

private fun Premiere.toMap(): Map<String, String?> {
    return mapOf(
        "bluray" to bluray,
        "digital" to digital,
        "dvd" to dvd,
        "russia" to russia,
        "world" to world
    )
}

private fun String.toNormalText(): StringResource {
    return when (this) {
        "bluray" -> Strings.ReleaseBluRay
        "digital" -> Strings.ReleaseDigital
        "dvd" -> Strings.ReleaseDvd
        "russia" -> Strings.ReleaseRussia
        "world" -> Strings.ReleaseWorld
        else -> Strings.Unknown
    }
}
