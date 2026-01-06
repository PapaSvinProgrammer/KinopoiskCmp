package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mordva.movie.presentation.movie.widget.component.PremierListContent
import com.mordva.model.totalValue.Premiere
import com.mordva.ui.theme.Strings
import com.mordva.ui.theme.Typography
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.premierItem(premiere: Premiere?) {
    if (premiere == null || !premiere.hasAnyNonNull()) {
        return
    }

    item(key = 14) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(Strings.Rental),
            fontSize = Typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp)
        )

        PremierListContent(premiere)
    }
}

private fun Premiere.hasAnyNonNull(): Boolean {
    return listOf(bluray, digital, dvd, russia, world).any { it != null }
}