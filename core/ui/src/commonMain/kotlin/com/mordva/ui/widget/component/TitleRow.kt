package com.mordva.ui.widget.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.mordva.ui.theme.Resources
import com.mordva.ui.theme.Typography
import org.jetbrains.compose.resources.painterResource

@Composable
fun TitleRow(
    modifier: Modifier = Modifier,
    title: String,
    fontSize: TextUnit = Typography.titleMedium.fontSize,
    fontWeight: FontWeight = FontWeight.Bold,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Text(
            text = title,
            fontSize = fontSize,
            fontWeight = fontWeight,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 15.dp, vertical = 10.dp)
        )

        Icon(
            painter = painterResource(Resources.Icons.KeyboardArrowRight),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 15.dp, vertical = 10.dp)
        )
    }
}