package com.mordva.search.presentation.search_screen.widget.listItem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.model.SearchItem
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.listItems.poster.StandardImageSmall
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchHistoryMovieCard(
    modifier: Modifier = Modifier,
    searchItem: SearchItem,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable(onClick = onClick)
            .padding(15.dp)
    ) {
        StandardImageSmall(searchItem.poster)
        Spacer(modifier = Modifier.width(15.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                DetailInfoContent(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(end = 30.dp),
                    searchItem = searchItem
                )

                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = onRemove
                ) {
                    Icon(
                        painter = painterResource(Resources.Icons.Close),
                        contentDescription = null
                    )
                }
            }
        }
    }
}