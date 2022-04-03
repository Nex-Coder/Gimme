package co.uk.nexhub.gimme.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    secondaryVariant = Teal300,
    background = Black700,
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    secondaryVariant = Teal300,
    background = White200,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val DarkExtraColorPalette = ExtraColors(
    solidColor = White100,
    tertiary = Red500,
    tertiaryVariant = Red600,
    quaternary = Orange500,
    quaternaryVariant = Orange600,
    quinary = LimeGreen500,
    quinaryVariant = LimeGreen600,
    senary = Blue500,
    senaryVariant = Blue600,
    topBackground = Grey600
)

private val LightExtraColorPalette = ExtraColors(
    solidColor = Black900,
    tertiary = Red700,
    tertiaryVariant = Red600,
    quaternary = Orange700,
    quaternaryVariant = Orange600,
    quinary = LimeGreen700,
    quinaryVariant = LimeGreen600,
    senary = Blue700,
    senaryVariant = Blue600,
    topBackground = Grey400
)


private val mDarkTheme = MutableLiveData(false)

@Composable
fun GimmeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    mDarkTheme.postValue(darkTheme)
    val colors = if (darkTheme) { DarkColorPalette } else { LightColorPalette }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

data class ExtraColors(val solidColor: Color,
                       val tertiary: Color,
                       val tertiaryVariant: Color,
                       val quaternary: Color,
                       val quaternaryVariant: Color,
                       val quinary: Color,
                       val quinaryVariant: Color,
                       val senary: Color,
                       val senaryVariant: Color,
                       val topBackground: Color)

fun MaterialTheme.extras(): ExtraColors {
    return if (mDarkTheme.value == true) { DarkExtraColorPalette } else { LightExtraColorPalette }
}