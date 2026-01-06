package com.mordva.otp.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mordva.otp.presentation.widget.content.MainOtpContent
import com.mordva.otp.utils.actionHandler
import com.mordva.ui.theme.PlatformResources
import com.mordva.ui.theme.Strings
import com.mordva.ui.widget.other.TitleTopBarText
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateOtpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: OtpViewModel
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val focusRequesters = remember {
        List(OtpViewModel.DEFAULT_LENGTH) {
            FocusRequester()
        }
    }
    val keyboardManager = LocalSoftwareKeyboardController.current

    var isRepeatInput by remember { mutableStateOf(false) }
    var localCode: List<Int?> by remember {
        mutableStateOf((1..OtpViewModel.DEFAULT_LENGTH).map { null })
    }

    LaunchedEffect(focusRequesters) {
        focusRequesters[0].requestFocus()
    }

    LaunchedEffect(state.isValid) {
        if (state.isValid == true) {
            navController.popBackStack()
        }
    }

    LaunchedEffect(state.focusedIndex) {
        state.focusedIndex?.let { index ->
            focusRequesters.getOrNull(index)?.requestFocus()
        }
    }

    LaunchedEffect(state.code, keyboardManager) {
        val allNumbersEntered = state.code.none { it == null }

        if (allNumbersEntered) {
            focusRequesters.forEach { it.freeFocus() }
            focusRequesters[0].requestFocus()

            if (state.code == localCode) {
                viewModel.setDefaultPinCode()
            }

            if (!isRepeatInput) {
                localCode = state.code
                isRepeatInput = true
                viewModel.resetCode()
            }

            if (state.code != localCode) {
                viewModel.resetCode()
                viewModel.updateValidState(false)
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(Strings.CodePassword))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = PlatformResources.PlatformIcons.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isRepeatInput) {
                MainOtpContent(
                    state = state,
                    focusRequesters = focusRequesters,
                    onAction = { action ->
                        actionHandler(
                            action = action,
                            viewModel = viewModel,
                            focusRequesters = focusRequesters
                        )
                    },
                    modifier = modifier
                        .padding(innerPadding)
                        .consumeWindowInsets(innerPadding),
                    isError = !(state.isValid ?: true)
                )
            }

            AnimatedVisibility(
                visible = isRepeatInput,
                enter = slideInHorizontally(initialOffsetX = { it }),
                exit = slideOutHorizontally(targetOffsetX = { it })
            ) {
                MainOtpContent(
                    state = state,
                    hint = stringResource(Strings.RepeatPassword),
                    focusRequesters = focusRequesters,
                    onAction = { action ->
                        if (state.isValid == false) {
                            viewModel.updateValidState(null)
                        }

                        actionHandler(
                            action = action,
                            viewModel = viewModel,
                            focusRequesters = focusRequesters
                        )
                    },
                    modifier = modifier
                        .padding(innerPadding)
                        .consumeWindowInsets(innerPadding),
                    isError = !(state.isValid ?: true)
                )
            }
        }
    }
}
