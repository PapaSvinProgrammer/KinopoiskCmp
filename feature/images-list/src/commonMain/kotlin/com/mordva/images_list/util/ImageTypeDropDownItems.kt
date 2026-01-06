package com.mordva.images_list.util

import androidx.compose.runtime.Composable
import com.mordva.ui.theme.Strings
import com.mordva.ui.widget.component.customDropDownList.DropDownItem
import org.jetbrains.compose.resources.stringResource

@Composable
fun imageTypeDropDownItems(): List<DropDownItem> {
    val all = DropDownItem(stringResource(Strings.All))
    val backdrop = DropDownItem(stringResource(Strings.Backdrop))
    val cover = DropDownItem(stringResource(Strings.Cover))
    val frame = DropDownItem(stringResource(Strings.Frame))
    val promo = DropDownItem(stringResource(Strings.Promo))
    val screenshot = DropDownItem(stringResource(Strings.Screenshot))
    val shooting = DropDownItem(stringResource(Strings.Shooting))
    val still = DropDownItem(stringResource(Strings.Still))
    val wallpaper = DropDownItem(stringResource(Strings.Wallpaper))

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
