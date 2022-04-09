package co.uk.nexhub.gimme.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.uk.nexhub.gimme.ui.theme.extras

@Composable
fun GimmeCircleButton(
    modifier: Modifier = Modifier,
    width: Dp = 380.dp,
    height: Dp = width,
    border: BorderStroke = BorderStroke(3.dp, MaterialTheme.colors.primary),
    contentPadding: PaddingValues = PaddingValues(0.dp),

    imageId: Int,
    contentDescription: String = "Gimme Circle button's image.",
    imageScale: Float = 0.675f,
    imageAlignment: Alignment = Alignment.Center,
    imageContentScale: ContentScale = ContentScale.Fit,
    imageAlpha: Float = 0.25f,
    imageColorFilter: ColorFilter? = ColorFilter.lighting(Color.White, Color.White),

    shadowStrength: Int = 8,
    shadowColor: Color = MaterialTheme.extras().solidColor,
    shadowPadding: Dp = 20.dp,

    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    val shadowColors: List<Color> = remember {
        var i = shadowStrength
        var shadow = listOf(
            if (shadowStrength <= 0) {
                Color.Transparent
            } else {
                shadowColor
            }
        )
        while (i > 1) {
            shadow = shadow.plus(shadowColor)
            i--
        }
        shadow = shadow.plus(Color.Transparent)
        shadow
    }

    Box(
        modifier
            .background(
                brush = Brush.radialGradient(
                    colors = shadowColors
                ),
            )
            .padding(bottom = shadowPadding), contentAlignment = Alignment.TopCenter
    ) {
        var lModifier = Modifier
            .width(width)
            .height(height)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        MaterialTheme.colors.primaryVariant.copy(alpha = 0.9f),
                        MaterialTheme.colors.primary
                    ),
                    radius = 440.dp.value
                ),
                CircleShape
            )
        if (true) {
            lModifier = lModifier.border(
                width,
                Brush.linearGradient(
                    0f to MaterialTheme.colors.background.copy(alpha = 0.8f),
                    0.4f to Color.Transparent
                ),
                CircleShape
            )
        }

        OutlinedButton(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            border = border,
            shape = CircleShape,
            contentPadding = contentPadding,
            modifier = lModifier,
            onClick = onClick,
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painterResource(imageId), contentDescription,
                    Modifier
                        .fillMaxWidth(imageScale)
                        .aspectRatio(1f),
                    imageAlignment, imageContentScale, imageAlpha, imageColorFilter
                )
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center, content = content)
            }
        }
    }
}

@Composable
fun GimmeRectangleButton(
    modifier: Modifier = Modifier,
    width: Dp = 380.dp,
    height: Dp = width,
    border: BorderStroke = BorderStroke(1.dp, MaterialTheme.colors.primary),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    corner: Dp = 20.dp,

    imageId: Int,
    contentDescription: String = "Gimme Rectangle button's image.",
    imageScale: Float = 0.675f,
    imageAlignment: Alignment = Alignment.Center,
    imageContentScale: ContentScale = ContentScale.Fit,
    imageAlpha: Float = 0.25f,
    imageColorFilter: ColorFilter? = ColorFilter.lighting(Color.White, Color.White),

    shadowStrength: Int = 11,
    shadowColor: Color = MaterialTheme.extras().solidColor,
    shadowPadding: Dp = 5.dp,

    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    val shape = RoundedCornerShape(corner)

    val shadowColors: List<Color> = remember {
        var i = shadowStrength
        var shadow = listOf(
            Color.Transparent,
        )
        while (i > 1) {
            shadow = shadow.plus(shadowColor.copy(alpha= 0.5f))
            i--
        }
        shadow = shadow.plus(shadowColor.copy(alpha = 0.1f))
        shadow
    }


    Box(
        modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = shadowColors,
                    startY = Float.POSITIVE_INFINITY,
                    endY = 0f,
                ),
                RoundedCornerShape(corner + 6.dp)
            )
            .padding(bottom = shadowPadding), contentAlignment = Alignment.TopCenter
    ) {

        var lModifier = Modifier
            .width(width)
            .height(height)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colors.primaryVariant,
                        MaterialTheme.colors.primary.copy(alpha = 0.8f)
                    ),
                    start = Offset.Zero,
                    end = Offset(600f, 460f)
                ),
                shape
            )
        if (false) {
            lModifier = lModifier.border(
                width,
                Brush.linearGradient(
                    0f to MaterialTheme.colors.background.copy(alpha = 0.8f),
                    0.4f to Color.Transparent,
                ),
                shape
            )
        }
        OutlinedButton(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            border = border,
            shape = shape,
            contentPadding = contentPadding,
            modifier = lModifier,
            onClick = onClick,
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painterResource(imageId), contentDescription,
                    Modifier
                        .fillMaxWidth(imageScale)
                        .aspectRatio(1f),
                    imageAlignment, imageContentScale, imageAlpha, imageColorFilter
                )
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center, content = content)
            }
        }
        //Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center, content = content)
    }
}
