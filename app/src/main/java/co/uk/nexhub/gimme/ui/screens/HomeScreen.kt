package co.uk.nexhub.gimme.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.uk.nexhub.gimme.BuildConfig
import co.uk.nexhub.gimme.ui.elements.VerticalDivider
import co.uk.nexhub.gimme.ui.theme.extras
import com.ramcosta.composedestinations.annotation.Destination

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
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(id = co.uk.nexhub.gimme.R.drawable.logo_alt_textless),
                "Logo alternative text-less"
            )

            Spacer(modifier = Modifier.width(6.dp))

            Image(painterResource(id = co.uk.nexhub.gimme.R.drawable.header), "Logo header")

            val version = BuildConfig.VERSION_NAME
            Column(
                Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text("  v$version", fontSize = 8.sp)
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        GimmeCircleButton(imageId = co.uk.nexhub.gimme.R.drawable.send_gray) {
            Text(
                "Share Files",
                fontSize = 54.sp,
                color = MaterialTheme.colors.background.copy(alpha = 0.85f)
            )
        }

        //Spacer(modifier = Modifier.height(40.dp))

        Text(
            "Extras",
            fontSize = 20.sp,
            color = MaterialTheme.extras().solidColor.copy(alpha = 0.2f)
        )
        VerticalDivider(5.dp)

    }
}

@Composable
fun GimmeCircleButton(
    modifier: Modifier = Modifier,
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
                )
            )
            .padding(bottom = shadowPadding), contentAlignment = Alignment.TopCenter
    ) {

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

@SuppressLint("ComposableModifierFactory", "ModifierFactoryExtensionFunction")
@Composable
fun defaultCoolButtonModifier(
    width: Dp,
    height: Dp,
    shape: Shape,
    borderShine: Boolean = false
): Modifier {
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

@SuppressLint("ComposableModifierFactory", "ModifierFactoryExtensionFunction")
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