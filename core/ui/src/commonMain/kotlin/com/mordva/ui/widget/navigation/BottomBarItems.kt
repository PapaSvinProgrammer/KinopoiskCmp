package com.mordva.ui.widget.navigation

import androidx.compose.ui.graphics.Color
import com.mordva.navigation.FavoriteGraph
import com.mordva.navigation.HomeGraph
import com.mordva.navigation.ProfileGraph
import com.mordva.navigation.RootGraph
import com.mordva.navigation.SearchGraph
import com.mordva.ui.theme.ColorGradient1
import com.mordva.ui.theme.ColorGradient2
import com.mordva.ui.theme.ColorGradient3
import com.mordva.ui.theme.ColorGradient4
import com.mordva.ui.theme.Resources
import org.jetbrains.compose.resources.DrawableResource

sealed class BottomBarTab(
    val title: String,
    val icon: DrawableResource,
    val color: Color,
    val route: RootGraph
) {
    data object Account: BottomBarTab(
        title = "Профиль",
        icon = Resources.Icons.PersonFill,
        color = ColorGradient1,
        route = ProfileGraph
    )

    data object Home: BottomBarTab(
        title = "Главная",
        icon = Resources.Icons.HomeFill,
        color = ColorGradient2,
        route = HomeGraph
    )

    data object Search: BottomBarTab(
        title = "Поиск",
        icon = Resources.Icons.Search,
        color = ColorGradient3,
        route = SearchGraph
    )

    data object Favorite: BottomBarTab(
        title = "Избранное",
        icon = Resources.Icons.BookmarkFill,
        color = ColorGradient4,
        route = FavoriteGraph
    )
}

object BottomBarItems {
    val items = listOf(
        BottomBarTab.Home,
        BottomBarTab.Favorite,
        BottomBarTab.Search,
        BottomBarTab.Account
    )
}