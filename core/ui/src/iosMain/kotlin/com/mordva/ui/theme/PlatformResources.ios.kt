package com.mordva.ui.theme

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

actual object PlatformResources {
    actual object Icons {
        actual val ArrowBack: ImageVector
            get() {
                if (_ArrowBackIos != null) {
                    return _ArrowBackIos!!
                }
                _ArrowBackIos = ImageVector.Builder(
                    name = "ArrowBackIos",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 960f,
                    viewportHeight = 960f
                ).apply {
                    path(fill = SolidColor(Color(0xFFE3E3E3))) {
                        moveToRelative(382f, 480f)
                        lineToRelative(294f, 294f)
                        quadToRelative(15f, 15f, 14.5f, 35f)
                        reflectiveQuadTo(675f, 844f)
                        quadToRelative(-15f, 15f, -35f, 15f)
                        reflectiveQuadToRelative(-35f, -15f)
                        lineTo(297f, 537f)
                        quadToRelative(-12f, -12f, -18f, -27f)
                        reflectiveQuadToRelative(-6f, -30f)
                        quadToRelative(0f, -15f, 6f, -30f)
                        reflectiveQuadToRelative(18f, -27f)
                        lineToRelative(308f, -308f)
                        quadToRelative(15f, -15f, 35.5f, -14.5f)
                        reflectiveQuadTo(676f, 116f)
                        quadToRelative(15f, 15f, 15f, 35f)
                        reflectiveQuadToRelative(-15f, 35f)
                        lineTo(382f, 480f)
                        close()
                    }
                }.build()

                return _ArrowBackIos!!
            }

        private var _ArrowBackIos: ImageVector? = null

        actual val Search: ImageVector
            get() {
                if (_Search != null) {
                    return _Search!!
                }
                _Search = ImageVector.Builder(
                    name = "Search",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 960f,
                    viewportHeight = 960f
                ).apply {
                    path(fill = SolidColor(Color(0xFFE3E3E3))) {
                        moveTo(380f, 640f)
                        quadToRelative(-109f, 0f, -184.5f, -75.5f)
                        reflectiveQuadTo(120f, 380f)
                        quadToRelative(0f, -109f, 75.5f, -184.5f)
                        reflectiveQuadTo(380f, 120f)
                        quadToRelative(109f, 0f, 184.5f, 75.5f)
                        reflectiveQuadTo(640f, 380f)
                        quadToRelative(0f, 44f, -14f, 83f)
                        reflectiveQuadToRelative(-38f, 69f)
                        lineToRelative(224f, 224f)
                        quadToRelative(11f, 11f, 11f, 28f)
                        reflectiveQuadToRelative(-11f, 28f)
                        quadToRelative(-11f, 11f, -28f, 11f)
                        reflectiveQuadToRelative(-28f, -11f)
                        lineTo(532f, 588f)
                        quadToRelative(-30f, 24f, -69f, 38f)
                        reflectiveQuadToRelative(-83f, 14f)
                        close()
                        moveTo(380f, 560f)
                        quadToRelative(75f, 0f, 127.5f, -52.5f)
                        reflectiveQuadTo(560f, 380f)
                        quadToRelative(0f, -75f, -52.5f, -127.5f)
                        reflectiveQuadTo(380f, 200f)
                        quadToRelative(-75f, 0f, -127.5f, 52.5f)
                        reflectiveQuadTo(200f, 380f)
                        quadToRelative(0f, 75f, 52.5f, 127.5f)
                        reflectiveQuadTo(380f, 560f)
                        close()
                    }
                }.build()

                return _Search!!
            }

        private var _Search: ImageVector? = null
    }
}