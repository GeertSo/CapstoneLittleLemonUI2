package com.geso.capstonelittlelemon.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.geso.capstonelittlelemon.R


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontDancingScript = GoogleFont("Dancing Script")
val fontMarkazi = GoogleFont("Markazi")
val fontKarla = GoogleFont("Karla")

val fontFamilyKarla = FontFamily(
    Font(
        googleFont = fontKarla,
        fontProvider = provider,
    )
)

val fontFamilyMarkazi = FontFamily(
    Font(
        googleFont = fontMarkazi,
        fontProvider = provider,
    )
)

val fontFamilyDancingScript = FontFamily(   //just for testing purposes
    Font(
        googleFont = fontDancingScript,
        fontProvider = provider,
    )
)

@Immutable
data class CustomTypography(
    val displayTitle: TextStyle,
    val subTitle: TextStyle,
    val leadText: TextStyle,
    val sectionTitle: TextStyle,
    val sectionCategory: TextStyle,
    val cardTitle: TextStyle,
    val paragraph: TextStyle,
    val highlight: TextStyle
)

val LocalCustomTypography = staticCompositionLocalOf {
    CustomTypography(
        displayTitle = TextStyle.Default,
        subTitle = TextStyle.Default,
        leadText = TextStyle.Default,
        sectionTitle = TextStyle.Default,
        sectionCategory = TextStyle.Default,
        cardTitle = TextStyle.Default,
        paragraph = TextStyle.Default,
        highlight = TextStyle.Default,
    )
}

val littleLemonTypography = CustomTypography(
    displayTitle = TextStyle(                  //Display Title
        fontFamily = fontFamilyMarkazi,
        fontWeight = FontWeight.Medium,
        fontSize = 64.sp
    ),
    subTitle = TextStyle(                  //Sub Title
        fontFamily = fontFamilyMarkazi,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    leadText = TextStyle(                  //Lead Text
        fontFamily = fontFamilyKarla,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    sectionTitle = TextStyle(                 // Section Title - Uppercase!
        fontFamily = fontFamilyKarla,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
    ),
    sectionCategory = TextStyle(                 // Section Category
        fontFamily = fontFamilyKarla,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
    ),
    cardTitle = TextStyle(                 // Card Title
        fontFamily = fontFamilyKarla,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),
    paragraph = TextStyle(                 // Paragraph Text
        fontFamily = fontFamilyKarla,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    highlight = TextStyle(                 // Highlight
        fontFamily = fontFamilyKarla,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
)

