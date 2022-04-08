package co.uk.nexhub.gimme.ui.screens

import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import co.uk.nexhub.gimme.BuildConfig
import co.uk.nexhub.gimme.model.NavigatingStandardData
import co.uk.nexhub.gimme.ui.elements.HorizontalDivider
import co.uk.nexhub.gimme.ui.elements.InviteWidget
import co.uk.nexhub.gimme.ui.elements.SpaceWidget
import co.uk.nexhub.gimme.ui.elements.VerticalDivider
import co.uk.nexhub.gimme.ui.screens.destinations.ParamTestScreenDestination
import co.uk.nexhub.gimme.ui.theme.extras
import com.manueldidonna.wavestimeranimation.WavesLoadingIndicator
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigateTo

@Destination
@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(25.dp))

        Row(
            Modifier
                .fillMaxWidth(0.75f)
                .height(50.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Image(painterResource(id = co.uk.nexhub.gimme.R.drawable.logo_alt_textless), "Logo alternative text-less")

            Spacer(modifier = Modifier.width(6.dp))

            Image(painterResource(id = co.uk.nexhub.gimme.R.drawable.header), "Logo header")

            val version = BuildConfig.VERSION_NAME
            Column(Modifier.fillMaxHeight(), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Bottom) {
                Text("  v$version", fontSize = 8.sp)
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        GimmeCircleButton(imageId = co.uk.nexhub.gimme.R.drawable.send_gray) {
            Text(
                "Share Files", fontSize = 54.sp, color = MaterialTheme.colors.background.copy(alpha = 0.75f)
            )
        }

        //Spacer(modifier = Modifier.height(40.dp))

        Text("Extras", fontSize = 20.sp, color = MaterialTheme.extras().solidColor.copy(alpha = 0.2f))
        VerticalDivider(5.dp)


        /*CoolButton(width = 140.dp, height = 60.dp,imageId = co.uk.nexhub.gimme.R.drawable.send_gray, shape = RoundedCornerShape(10.dp), fontSize = 24.sp,
            shadowGradiant = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colors.background.copy(alpha = 0.001f),
                    MaterialTheme.extras().solidColor.copy(alpha = 0.9f)
                ),
                start = Offset(0f, Float.POSITIVE_INFINITY),
                end = Offset(Float.POSITIVE_INFINITY, 0f)
            ))*/


    }
}

@Composable
fun GimmeCircleButton(modifier: Modifier = Modifier,
                      width: Dp = 380.dp,
                      height: Dp = 380.dp,
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
                      content: @Composable BoxScope.() -> Unit) {
    val shadowColors: List<Color> = remember {
        var i = shadowStrength;
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
                )
            )
            .padding(bottom = shadowPadding), contentAlignment = Alignment.TopCenter) {

        OutlinedButton(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            border = border,
            shape = CircleShape,
            contentPadding = contentPadding,
            modifier = defaultCoolButtonModifier(width, height, CircleShape, true),
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
fun defaultCoolButtonModifier(width: Dp, height: Dp, shape: Shape, borderShine: Boolean = false): Modifier {
    val modifier = defaultCoolButtonModifier(width, height, shape)
    if (borderShine) {
        return modifier
            .border(
                width,
                Brush.linearGradient(
                    0f to MaterialTheme.colors.background.copy(alpha = 0.8f),
                    0.4f to Color.Transparent
                ),
                shape
            )
    }
    return modifier
}

@Composable
private fun defaultCoolButtonModifier(width: Dp, height: Dp, shape: Shape): Modifier {
    return Modifier
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
            shape
        )
}