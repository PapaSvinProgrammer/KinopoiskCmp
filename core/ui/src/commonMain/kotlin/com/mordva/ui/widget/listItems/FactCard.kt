package com.mordva.ui.widget.listItems

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.ui.theme.Icons
import com.mordva.ui.theme.Strings
import com.mordva.ui.theme.Typography
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FactCard(
    modifier: Modifier = Modifier,
    text: String,
    isSpoiler: Boolean,
    onClick: () -> Unit
) {
    var isChecked by remember { mutableStateOf(!isSpoiler) }

    Box(
        modifier = modifier
            .width(300.dp)
            .height(160.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                if (isChecked) {
                    onClick()
                } else {
                    isChecked = true
                }
            }
            .padding(15.dp)
    ) {
        AnimatedContent(
            targetState = isChecked
        ) { targetState ->
            if (targetState) {
                Text(
                    text = text,
                    fontSize = Typography.bodyMedium.fontSize,
                    overflow = TextOverflow.Ellipsis
                )
            }
            else {
                SpoilerContent()
            }
        }
    }
}

@Composable
private fun SpoilerContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(Icons.VisibilityOff),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = stringResource(Strings.SpoilersTitle),
                fontSize = 13.sp
            )

            Text(
                text = stringResource(Strings.SpoilersDescription),
                fontSize = 13.sp
            )
        }
    }
}