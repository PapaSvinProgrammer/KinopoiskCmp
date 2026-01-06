package com.mordva.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

actual object PlatformResources {
    actual object PlatformIcons {
        actual val ArrowBack: ImageVector
            get() {
                if (_ArrowBackAndroid != null) {
                    return _ArrowBackAndroid!!
                }
                _ArrowBackAndroid = ImageVector.Builder(
                    name = "ArrowBackAndroid",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 960f,
                    viewportHeight = 960f
                ).apply {
                    path(fill = SolidColor(Color(0xFFE3E3E3))) {
                        moveToRelative(313f, 520f)
                        lineToRelative(196f, 196f)
                        quadToRelative(12f, 12f, 11.5f, 28f)
                        reflectiveQuadTo(508f, 772f)
                        quadToRelative(-12f, 11f, -28f, 11.5f)
                        reflectiveQuadTo(452f, 772f)
                        lineTo(188f, 508f)
                        quadToRelative(-6f, -6f, -8.5f, -13f)
                        reflectiveQuadToRelative(-2.5f, -15f)
                        quadToRelative(0f, -8f, 2.5f, -15f)
                        reflectiveQuadToRelative(8.5f, -13f)
                        lineToRelative(264f, -264f)
                        quadToRelative(11f, -11f, 27.5f, -11f)
                        reflectiveQuadToRelative(28.5f, 11f)
                        quadToRelative(12f, 12f, 12f, 28.5f)
                        reflectiveQuadTo(508f, 245f)
                        lineTo(313f, 440f)
                        horizontalLineToRelative(447f)
                        quadToRelative(17f, 0f, 28.5f, 11.5f)
                        reflectiveQuadTo(800f, 480f)
                        quadToRelative(0f, 17f, -11.5f, 28.5f)
                        reflectiveQuadTo(760f, 520f)
                        lineTo(313f, 520f)
                        close()
                    }
                }.build()

                return _ArrowBackAndroid!!
            }

        private var _ArrowBackAndroid: ImageVector? = null

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