package com.mordva.movie.presentation.randommovie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RandomMovieScreen() {
    Scaffold(
        topBar = { RandomMovieTopBar(onAction = {}) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            RandomMoviePager(
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
