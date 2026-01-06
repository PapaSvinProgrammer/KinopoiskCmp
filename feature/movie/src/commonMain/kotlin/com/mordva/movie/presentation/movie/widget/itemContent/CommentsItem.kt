package com.mordva.movie.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.domain.model.movie.Comment
import com.mordva.movie.presentation.movie.widget.listItem.CommentCard
import com.mordva.ui.theme.Strings
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import org.jetbrains.compose.resources.stringResource

internal fun LazyListScope.commentsItem(comments: List<Comment>) {
    if (comments.isEmpty()) return

    item(key = 10) {
        TitleRow(title = stringResource(Strings.Review)) {}

        DefaultLazyRow(
            list = comments,
            lastItemCard = {}
        ) { comment ->
            CommentCard(comment)
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}