package com.mordva.bottomsheet.packageBottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mordva.domain.model.PackageType
import com.mordva.domain.model.movie.Movie
import com.mordva.ui.theme.Resources
import com.mordva.ui.theme.Typography
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackageBottomSheet(
    movie: Movie,
    packageSize: Map<PackageType, Int>,
    selectedSet: Set<PackageType>,
    onAction: (PackageItemAction) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val listItems = listOf(
        PackageItem(
            title = stringResource(Resources.Strings.FavoriteFilms),
            type = PackageType.FAVORITE
        ),
        PackageItem(
            title = stringResource(Resources.Strings.WillWatching),
            type = PackageType.WILL_WATCH
        )
    )

    ModalBottomSheet(onDismissRequest = onDismissRequest) {
        Column {
            SearchItemCard(searchItem = movie.toSearchItem())

            listItems.forEach { packageItem ->
                val isSelected = packageItem.type in selectedSet

                ListItem(
                    modifier = Modifier.clickable {
                        if (!isSelected) {
                            onAction(PackageItemAction.Add(packageItem.type))
                        } else {
                            onAction(PackageItemAction.Delete(packageItem.type))
                        }
                    },
                    leadingContent = {
                        Icon(
                            painter = painterResource(packageItem.type.toIcon(isSelected)),
                            contentDescription = null,
                            tint = if (isSelected)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface
                        )
                    },
                    headlineContent = {
                        Text(
                            text = packageItem.title,
                            fontSize = Typography.bodyMedium.fontSize
                        )
                    },
                    trailingContent = {
                        Text(
                            text = packageSize[packageItem.type].toString()
                        )
                    },
                    colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceContainerLow)
                )

                HorizontalDivider()
            }
        }

//        DisableChangeStatusBarIconColor()
    }
}
