package com.mordva.movie.presentation.randommovie.widget.listComponent

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.person.PersonMovie
import com.mordva.movie.presentation.randommovie.widget.RandomMovieAction
import com.mordva.movie.presentation.randommovie.widget.component.PersonMovieSquare
import com.mordva.ui.theme.DsTextSize
import com.mordva.ui.util.customOffset
import com.mordva.ui.widget.component.TextTitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.LastItemCard
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

