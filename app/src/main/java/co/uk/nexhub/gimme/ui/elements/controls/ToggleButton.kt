package co.uk.nexhub.gimme.ui.elements.controls

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.uk.nexhub.gimme.ui.elements.base.Circle
import co.uk.nexhub.gimme.ui.model.ToggleButtonBrushes
import co.uk.nexhub.gimme.ui.model.ToggleButtonDefaults

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ToggleButton(onClick: ((Boolean) -> Unit),
                 modifier: Modifier = Modifier,
                 toggled: Boolean = false,
                 width: Dp = 95.dp,
                 enabled: Boolean = true,
                 interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
                 elevation: ButtonElevation? = ButtonDefaults.elevation(23.dp, 30.dp, 1.dp, 25.dp, 24.dp),
                 shape: Shape = CircleShape,
                 border: BorderStroke? = null,
                 brush: ToggleButtonBrushes = ToggleButtonDefaults.toggleButtonBrushes()
) {
    val aspectRatio = 2.8f

    var isToggled by remember { mutableStateOf(toggled) }

    val offsetAnimation: Float by animateFloatAsState(
        if (isToggled) 0.66f else 0.02f
    )

    val contentBrush by brush.contentBrush(enabled)
    val borderBrush by brush.borderBrush(enabled)

    Surface(
        modifier = modifier,
        shape = shape,
        color = Color.Transparent,
        contentColor = Color.Transparent,
        border = border,
        elevation = elevation?.elevation(enabled, interactionSource)?.value ?: 0.dp,
        onClick = {},
        enabled = enabled,
        role = Role.Button,
        interactionSource = interactionSource,
        indication = rememberRipple()
    ) {
        Row(
            Modifier
                .width(width)
                .aspectRatio(aspectRatio)
                .background(brush.backgroundBrush(enabled).value, shape)
                .border(1.dp, borderBrush, shape)
                .clip(shape)
                .clickable {
                    if (enabled) {
                        isToggled = !isToggled
                        onClick.invoke(toggled)
                    }
                },
            Arrangement.Start,
            Alignment.CenterVertically
        ) {
            Spacer(Modifier.fillMaxWidth(offsetAnimation)) // change me
            Circle(width.div(aspectRatio).minus(4.dp), contentBrush, borderBrush)
        }
    }
}