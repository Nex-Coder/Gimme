package co.uk.nexhub.gimme.ui.elements.controls

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// TODO Make a rounded radio button that can take a float and draw a rounded corner square in a canvas that also can take a brush. Not answer but starting point https://stackoverflow.com/questions/69748987/how-to-draw-rounded-corner-polygons-in-jetpack-compose-canvas
@Composable
fun RadioButtonBrush(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    brushes: RadioButtonBrushes = RadioButtonBrushDefaults.brushes()
) {
    val dotRadius = animateDpAsState(
        targetValue = if (selected) RadioButtonDotSize / 2 else 0.dp,
        animationSpec = tween(durationMillis = RadioAnimationDuration)
    )
    val radioColor = brushes.radioBrush(enabled, selected)
    val selectableModifier =
        if (onClick != null) {
            Modifier.selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = RadioButtonRippleRadius
                )
            )
        } else {
            Modifier
        }
    Canvas(
        modifier
            .then(Modifier)
            .then(selectableModifier)
            .wrapContentSize(Alignment.Center)
            .padding(RadioButtonPadding)
            .requiredSize(RadioButtonSize)
    ) {
        // Draw the radio button
        val strokeWidth = RadioStrokeWidth.toPx()
        drawCircle(
            radioColor.value,
            RadioRadius.toPx() - strokeWidth / 2,
            style = Stroke(strokeWidth)
        )
        if (dotRadius.value > 0.dp) {
            drawCircle(radioColor.value, dotRadius.value.toPx() - strokeWidth / 2, style = Fill)
        }
    }
}

/* --- */

private const val RadioAnimationDuration = 100

private val RadioButtonRippleRadius = 24.dp
private val RadioButtonPadding = 2.dp
private val RadioButtonSize = 20.dp
private val RadioRadius = RadioButtonSize / 2
private val RadioButtonDotSize = 12.dp
private val RadioStrokeWidth = 2.dp
private val brushDefaultSpring = spring<Brush>()
/**
 * Represents the brush used by a [RadioButton] in different states.
 *
 * See [RadioButtonDefaults.Brushs] for the default implementation that follows Material
 * specifications.
 */
@Stable
interface RadioButtonBrushes {
    /**
     * Represents the main brush used to draw the outer and inner circles, depending on whether
     * the [RadioButton] is [enabled] / [selected].
     *
     * @param enabled whether the [RadioButton] is enabled
     * @param selected whether the [RadioButton] is selected
     */
    @Composable
    fun radioBrush(enabled: Boolean, selected: Boolean): State<Brush>
    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun animateGradientAsState(targetGradient: List<Color>,
                               animationSpec: AnimationSpec<Brush> = brushDefaultSpring,
                               finishedListener: ((Brush) -> Unit)? = null
    ): MutableList<Color> {

        val newGradient = mutableListOf<Color>()

        var i = 0;
        for (targetValue in targetGradient) {
            newGradient.add(i, animateColorAsState(targetValue, tween(durationMillis = RadioBrushAnimationDuration)).value)
            i++
        }

        return newGradient
    }
}

/**
 * Defaults used in [RadioButton].
 */
object RadioButtonBrushDefaults {
    /**
     * Creates a [RadioButtonBrushes] that will animate between the provided brushes according to
     * the Material specification.
     *
     * @param selectedBrush the Brush to use for the RadioButton when selected and enabled.
     * @param unselectedBrush the Brush to use for the RadioButton when unselected and enabled.
     * @param disabledBrush the Brush to use for the RadioButton when disabled.
     * @return the resulting [RadioButtonBrushes] used for the RadioButton
     */
    @Composable
    fun brushes(
        selectedGradient: List<Color> = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.primaryVariant),
        unselectedGradient: List<Color> = listOf(MaterialTheme.colors.onSurface.copy(alpha = 0.6f), MaterialTheme.colors.onSurface.copy(alpha = 0.3f)),
        disabledGradient: List<Color> =  listOf(MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled), MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled))
    ): RadioButtonBrushes {
        return remember(
            selectedGradient,
            unselectedGradient,
            disabledGradient
        ) {
            DefaultRadioButtonBrushes(selectedGradient, unselectedGradient, disabledGradient)
        }
    }
}



/**
 * Default [RadioButtonBrushes] implementation.
 */
@Immutable
private class DefaultRadioButtonBrushes(
    private val selectedGradient: List<Color>,
    private val unselectedGradient: List<Color>,
    private val disabledGradient: List<Color>
) : RadioButtonBrushes {
    @SuppressLint("UnrememberedMutableState")
    @Composable
    override fun radioBrush(enabled: Boolean, selected: Boolean): State<Brush> {
        val target = when {
            !enabled -> disabledGradient
            !selected -> unselectedGradient
            else -> selectedGradient
        }

        return if (enabled) {
            mutableStateOf(Brush.radialGradient(animateGradientAsState(target, tween(durationMillis = RadioBrushAnimationDuration)) as List<Color>))
        } else {
            mutableStateOf(Brush.radialGradient(animateGradientAsState(target)))
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DefaultRadioButtonBrushes

        if (selectedGradient != other.selectedGradient) return false
        if (unselectedGradient != other.unselectedGradient) return false
        if (disabledGradient != other.disabledGradient) return false

        return true
    }

    override fun hashCode(): Int {
        var result = selectedGradient.hashCode()
        result = 31 * result + unselectedGradient.hashCode()
        result = 31 * result + disabledGradient.hashCode()
        return result
    }
}

private const val RadioBrushAnimationDuration = 100