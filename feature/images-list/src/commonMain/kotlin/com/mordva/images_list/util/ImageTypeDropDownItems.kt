package com.mordva.images_list.util

import androidx.compose.runtime.Composable
import com.mordva.ui.theme.Resources
import com.mordva.ui.widget.component.customDropDownList.DropDownItem
import org.jetbrains.compose.resources.stringResource

@Composable
fun imageTypeDropDownItems(): List<DropDownItem> {
    val all = DropDownItem(stringResource(Resources.Strings.All))
    val backdrop = DropDownItem(stringResource(Resources.Strings.Backdrop))
    val cover = DropDownItem(stringResource(Resources.Strings.Cover))
    val frame = DropDownItem(stringResource(Resources.Strings.Frame))
    val promo = DropDownItem(stringResource(Resources.Strings.Promo))
    val screenshot = DropDownItem(stringResource(Resources.Strings.Screenshot))
    val shooting = DropDownItem(stringResource(Resources.Strings.Shooting))
    val still = DropDownItem(stringResource(Resources.Strings.Still))
    val wallpaper = DropDownItem(stringResource(Resources.Strings.Wallpaper))

    return listOf(
        all,
        backdrop,
        cover,
        frame,
        promo,
        screenshot,
        shooting,
        still,
        wallpaper
    )
}
