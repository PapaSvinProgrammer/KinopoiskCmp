package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.model.movie.Fact
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.FactCard
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.factsItem(facts: List<Fact>) {
    if (facts.isEmpty()) return

    item {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(Resources.Strings.FactsTitle),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp)
        )

        DefaultLazyRow(
            list = facts,
            key = { it.value },
            lastItemCard = {}
        ) {
            FactCard(
                text = it.value,
                isSpoiler = it.spoiler ?: false,
                onClick = { }
            )
        }
    }
}