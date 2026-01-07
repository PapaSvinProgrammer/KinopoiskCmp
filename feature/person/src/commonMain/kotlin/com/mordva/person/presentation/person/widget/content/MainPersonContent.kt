package com.mordva.person.presentation.widget.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.domain.model.person.Person
import com.mordva.person.presentation.person.widget.content.AgeAndGrowthContent
import com.mordva.ui.theme.Strings
import com.mordva.ui.theme.Typography
import com.mordva.ui.widget.listItems.poster.StandardImageLarge
import com.mordva.ui.widget.other.BirthdayDepthContent
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MainPersonContent(
    person: Person,
    onClickDetail: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        StandardImageLarge(person.photo)

        Spacer(modifier = Modifier.width(15.dp))

        Column(
            modifier = Modifier.defaultMinSize(minHeight = 210.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                NameContent(
                    name = person.name,
                    alternativeName = person.enName
                )

                Spacer(modifier = Modifier.height(10.dp))

                BirthdayDepthContent(
                    birthday = person.birthday,
                    death = person.death
                )

                AgeAndGrowthContent(
                    age = person.age,
                    growth = person.growth
                )
            }

            TextButton(
                contentPadding = PaddingValues(0.dp),
                onClick = onClickDetail
            ) {
                Text(text = stringResource(Strings.Details))
            }
        }
    }
}

@Composable
private fun NameContent(
    name: String?,
    alternativeName: String?
) {
    Text(
        text = name ?: "",
        fontSize = 23.sp,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 40.sp
    )

    Spacer(modifier = Modifier.height(5.dp))

    Text(
        text = alternativeName ?: "",
        fontSize = Typography.bodyMedium.fontSize,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}