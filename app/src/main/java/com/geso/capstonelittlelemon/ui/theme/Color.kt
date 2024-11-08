package com.geso.capstonelittlelemon.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Primary1 = Color(0xFF495E57)
val Primary2 = Color(0xFFF4CE14)
val Secondary1 = Color(0xFFEE9972)
val Secondary2 = Color(0xFFFBDABB)
val Highlight1 = Color(0xFFEDEFEE)
val Highlight2 = Color(0xFF333333)

@Immutable
data class CustomColors(
    val primary1: Color,
    val primary2: Color,
    val secondary1: Color,
    val secondary2: Color,
    val highlight1: Color,
    val highlight2: Color,
//    val background: List<Color>
)

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        primary1 = Color.Unspecified,
        primary2 = Color.Unspecified,
        secondary1 = Color.Unspecified,
        secondary2 = Color.Unspecified,
        highlight1 = Color.Unspecified,
        highlight2 = Color.Unspecified,
//        background = emptyList()
    )
}

val littleLemonColors = CustomColors(
    primary1 = Primary1,
    primary2 = Primary2,
    secondary1 = Secondary1,
    secondary2 = Secondary2,
    highlight1 = Highlight1,
    highlight2 = Highlight2,
//        background = listOf(Color.White, Color(0xFFF8BBD0))
)