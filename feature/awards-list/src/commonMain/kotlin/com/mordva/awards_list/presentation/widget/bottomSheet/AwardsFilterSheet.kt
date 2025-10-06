package com.mordva.awards_list.presentation.widget.bottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.component.CheckRow
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AwardsFilterSheet(
    current: AwardsFilterType,
    onClick: (AwardsFilterType) -> Unit,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismissRequest) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(Resources.Strings.Sort),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            CheckRow(
                title = stringResource(Resources.Strings.ByAlphabet),
                isCheck = current == AwardsFilterType.BY_TITLE,
                modifier = Modifier.clickable { onClick(AwardsFilterType.BY_TITLE) }
            )

            CheckRow(
                title = stringResource(Resources.Strings.ByDate),
                isCheck = current == AwardsFilterType.BY_DATE,
                modifier = Modifier.clickable { onClick(AwardsFilterType.BY_DATE) }
            )
        }
    }
}