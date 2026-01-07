package com.mordva.ui.widget.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.mordva.ui.theme.DsSpacer
import com.mordva.ui.theme.Images
import com.mordva.ui.theme.Strings
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(Images.ServerError),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = DsSpacer.M70)
        )

        Spacer(modifier = Modifier.height(DsSpacer.M30))

        Text(
            text = stringResource(Strings.ErrorScreenDescription),
            textAlign = TextAlign.Center
        )
    }
}