package co.uk.nexhub.gimme.ui.elements.controls

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShapedRadioButton( // TODO make a function that takesa a RadioButtonBrushes & provide a background feature for inner background
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    size: Dp = 50.dp,
    fillShape: Shape = RoundedCornerShape(40),
    borderShape: Shape = fillShape
) {
    Surface(
        modifier
            .size(size)
            .aspectRatio(1f)
            .selectable(
                selected = selected,
                onClick = if (onClick != null) onClick else {{}},
                enabled = enabled,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = size
                )
            ),
        shape = borderShape,
        border = (BorderStroke(size.div(35), colors.radioColor(enabled, selected).value)),
        color = Color.Transparent
    ) {
        if (selected)
        Box(Modifier, Alignment.Center) {
            Box(
                Modifier
                    .fillMaxSize(0.9f)
                    .background(colors.radioColor(enabled, selected).value, fillShape)
                    .clip(fillShape)) {}
        }
    }
}