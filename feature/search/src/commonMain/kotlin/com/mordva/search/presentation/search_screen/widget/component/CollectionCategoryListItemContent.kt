package com.mordva.search.presentation.search_screen.widget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mordva.search.util.collectionCategoryList
import com.mordva.ui.theme.Strings
import com.mordva.ui.theme.Typography
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.collectionCategoryListItemContent(
    onCollectionClick: (String) -> Unit,
) {
    item {
        Text(
            modifier = Modifier.padding(15.dp),
            text = stringResource(Strings.Categories),
            fontSize = Typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            collectionCategoryList.forEach { category ->
                SuggestionChip(
                    label = {
                        Text(
                            text = category,
                            fontSize = Typography.bodySmall.fontSize
                        )
                    },
                    onClick = { onCollectionClick(category) }
                )
            }
        }
    }
}