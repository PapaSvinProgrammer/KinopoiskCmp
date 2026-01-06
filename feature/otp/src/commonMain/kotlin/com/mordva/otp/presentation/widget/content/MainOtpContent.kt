package com.mordva.otp.presentation.widget.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mordva.otp.presentation.widget.state.OtpAction
import com.mordva.otp.presentation.widget.state.UiState
import com.mordva.ui.theme.Strings
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MainOtpContent(
    state: UiState,
    focusRequesters: List<FocusRequester>,
    onAction: (OtpAction) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean,
    hint: String = stringResource(Strings.InputPinCode)
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = hint,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        MainPinCodeContent(
            state = state,
            focusRequesters = focusRequesters,
            onAction = onAction,
            isError = isError
        )
    }
}
