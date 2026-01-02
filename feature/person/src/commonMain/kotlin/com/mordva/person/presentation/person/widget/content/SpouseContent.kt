package com.mordva.person.presentation.person.widget.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.domain.model.person.Spouse
import com.mordva.person.presentation.person.widget.PersonListUIState
import com.mordva.person.presentation.person.widget.listItem.SpouseCard
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SpouseContent(
    spouses: List<Spouse>,
    state: PersonListUIState,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            text = stringResource(Resources.Strings.Spouse),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        (state as? PersonListUIState.Success)?.let {
            if (it.data.isEmpty()) {
                Text(
                    text = "-",
                    modifier = Modifier.fillMaxWidth().weight(2f)
                )
                return
            }

            Column(modifier = Modifier.weight(2f).fillMaxWidth()) {
                it.data.forEachIndexed { index, person ->
                    val spouse = spouses[index]

                    SpouseCard(
                        name = person.name ?: "",
                        countChild = spouse.children,
                        divorced = spouse.divorced ?: false,
                        photo = person.photo,
                        onClick = { onClick(person.id) }
                    )
                }
            }
        }
    }
}