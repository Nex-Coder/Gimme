package co.uk.nexhub.gimme.ui.elements.controls

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

/*================================================================================================*/
//region Controls
/*================================================================================================*/
/*-----region Standard Radio Buttons-----*/
@Composable
fun ExtendedRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    fillColors: RadioButtonColors = RadioButtonDefaults.fillColors(),
    dotMultiplier: Float = 1f,
    scale: Float = 1f,
    strokeWidth: Dp = RadioStrokeWidth * scale,
) {
    val buttonRippleRadius = RadioButtonRippleRadius * scale
    val buttonSize = RadioButtonSize * scale
    val buttonDotRadius = (RadioButtonDotSize * dotMultiplier * scale) / 2;
    val radius = (RadioButtonSize * scale) / 2

    val dotRadius by animateDpAsState(
        targetValue = if (selected) buttonDotRadius else 0.dp,
        animationSpec = tween(durationMillis = RadioAnimationDuration)
    )
    val radioColors = colors.radioColor(enabled, selected)
    val radioFillColors = fillColors.radioColor(enabled, selected)
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
                    radius = buttonRippleRadius
                )
            )
        } else {
            Modifier
        }
    val hasFill =
        radioFillColors != RadioButtonDefaults.fillColors().radioColor(enabled, selected)
    Canvas(
        modifier
            .then(
                if (onClick != null) {
                    MinimumTouchTargetModifier()
                } else {
                    Modifier
                }
            )
            .then(selectableModifier)
            .wrapContentSize(Alignment.Center)
            .requiredSize(buttonSize)
    ) {
        if (hasFill) {
            drawCircle( // Fill
                radioFillColors.value,
                (radius - (strokeWidth / 2)).toPx(),
            )
        }
        drawCircle( // Stroke
            radioColors.value,
            (radius - (strokeWidth / 2)).toPx(),
            style = Stroke(width = strokeWidth.toPx())
        )
        if (dotRadius > 0.dp) { // Dot
            drawCircle(radioColors.value, (dotRadius - (strokeWidth / 2)).toPx(), style = Fill)
        }
    }
}

@Composable
fun ExtendedRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    brushes: RadioButtonBrushes = RadioButtonBrushDefaults.brushes(),
    dotMultiplier: Float = 1f,
    scale: Float = 1f,
    strokeWidth: Dp = RadioStrokeWidth * scale,
) {
    val buttonRippleRadius = RadioButtonRippleRadius * scale
    val buttonSize = RadioButtonSize * scale
    val buttonDotRadius = (RadioButtonDotSize * dotMultiplier * scale) / 2;
    val radius = (RadioButtonSize * scale) / 2

    val dotRadius by animateDpAsState(
        targetValue = if (selected) buttonDotRadius else 0.dp,
        animationSpec = tween(durationMillis = RadioAnimationDuration)
    )
    val radioBrush = brushes.radioBrush(enabled, selected)
    val radioFillBrush = brushes.radioFillBrush(enabled, selected)
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
                    radius = buttonRippleRadius
                )
            )
        } else {
            Modifier
        }
    val hasFill =
        radioFillBrush != RadioButtonBrushDefaults.brushes().radioFillBrush(enabled, selected)
    Canvas(
        modifier
            .then(
                if (onClick != null) {
                    MinimumTouchTargetModifier()
                } else {
                    Modifier
                }
            )
            .then(selectableModifier)
            .wrapContentSize(Alignment.Center)
            .requiredSize(buttonSize)
    ) {
        if (hasFill) {
            drawCircle(// Fill
                radioFillBrush.value,
                (radius - (strokeWidth / 2)).toPx(),
            )
        }
        drawCircle( // Stroke
            radioBrush.value,
            (radius - (strokeWidth / 2)).toPx(),
            style = Stroke(width = strokeWidth.toPx())
        )
        if (dotRadius > 0.dp) { // Dot
            drawCircle(radioBrush.value, (dotRadius - (strokeWidth / 2)).toPx(), style = Fill)
        }
    }
}
/*-----endregion-----*/

/*-----region Shaped Radio Buttons-----*/
@Composable
fun RoundedExtendedRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    brushes: RadioButtonBrushes = RadioButtonBrushDefaults.brushes(),
    dotMultiplier: Float = 1f,
    scale: Float = 1f,
    strokeWidth: Dp = RadioStrokeWidth * scale,
    cornerRadius: CornerRadius = CornerRadius(
        x = (18.dp * scale).value,
        y = (18.dp * scale).value
    )
) {
    val buttonRippleRadius = RadioButtonRippleRadius * scale;
    val buttonSize = RadioButtonSize * scale;
    val buttonDotSize = RadioButtonDotSize * dotMultiplier * scale;

    val dotRadius by animateDpAsState(
        targetValue = if (selected) buttonDotSize else 0.dp,
        animationSpec = tween(durationMillis = RadioAnimationDuration)
    )
    val radioBrush = brushes.radioBrush(enabled, selected)
    val radioFillBrush = brushes.radioFillBrush(enabled, selected)
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
                    radius = buttonRippleRadius
                )
            )
        } else {
            Modifier
        }

    val strokeWidthRadius = (strokeWidth / 2);
    val hasFill =
        radioFillBrush != RadioButtonBrushDefaults.brushes().radioFillBrush(enabled, selected)
    Canvas(
        modifier
            .then(if (onClick != null) MinimumTouchTargetModifier() else Modifier)
            .then(selectableModifier)
            .wrapContentSize(Alignment.Center)
            .requiredSize(buttonSize)
    ) {
        val size = Size(
            width = (buttonSize - (strokeWidth / 1)).toPx(),
            height = (buttonSize - (strokeWidth / 1)).toPx()
        )
        if (hasFill) {
            drawRoundRect(
                // Fill
                brush = radioFillBrush.value,
                size = size,
                cornerRadius = cornerRadius,
            )
        }
        drawRoundRect( // Stroke
            brush = radioBrush.value,
            topLeft = Offset(
                x = strokeWidthRadius.toPx(),
                y = strokeWidthRadius.toPx(),
            ),
            size = size,
            cornerRadius = cornerRadius,
            style = Stroke(width = strokeWidth.toPx())
        )
        if (dotRadius > 0.dp) {
            val dotSize = Size(
                width = dotRadius.toPx(),
                height = dotRadius.toPx()
            )
            val offset = Offset(
                x = size.width.toDp().div(2).minus(dotSize.width.div(2).toDp()).plus(strokeWidthRadius).toPx(),
                y = size.height.toDp().div(2).minus(dotSize.width.div(2).toDp()).plus(strokeWidthRadius).toPx(),
            )
            drawRoundRect( // dot
                brush = radioBrush.value,
                size = dotSize,
                topLeft = offset,
                cornerRadius = cornerRadius,
            )
        }
    }
}

@Composable
fun RoundedExtendedRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    fillColors: RadioButtonColors = RadioButtonDefaults.fillColors(),
    dotMultiplier: Float = 1f,
    scale: Float = 1f,
    strokeWidth: Dp = RadioStrokeWidth * scale,
    cornerRadius: CornerRadius = CornerRadius(
        x = (18.dp * scale).value,
        y = (18.dp * scale).value
    )
) {
    val buttonRippleRadius = RadioButtonRippleRadius * scale;
    val buttonSize = RadioButtonSize * scale;
    val buttonDotSize = RadioButtonDotSize * dotMultiplier * scale;

    val dotRadius by animateDpAsState(
        targetValue = if (selected) buttonDotSize else 0.dp,
        animationSpec = tween(durationMillis = RadioAnimationDuration)
    )
    val radioColors = colors.radioColor(enabled, selected)
    val radioFillColors = fillColors.radioColor(enabled, selected)
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
                    radius = buttonRippleRadius
                )
            )
        } else {
            Modifier
        }
    val hasFill =
        radioFillColors != RadioButtonDefaults.fillColors().radioColor(enabled, selected)
    Canvas(
        modifier
            .then(if (onClick != null) MinimumTouchTargetModifier() else Modifier)
            .then(selectableModifier)
            .wrapContentSize(Alignment.Center)
            .requiredSize(buttonSize)
    ) {
        val size = Size(
            width = (buttonSize - (strokeWidth / 2)).toPx(),
            height = (buttonSize - (strokeWidth / 2)).toPx()
        )
        if (hasFill) {
            drawRoundRect(
                // Fill
                color = radioFillColors.value,
                size = size,
                cornerRadius = cornerRadius,
            )
        }
        drawRoundRect( // Stroke
            color = radioColors.value,
            size = size,
            cornerRadius = cornerRadius,
            style = Stroke(width = strokeWidth.toPx())
        )
        if (dotRadius > 0.dp) {
            val dotSize = Size(
                width = dotRadius.toPx(),
                height = dotRadius.toPx()
            )
            val offset = Offset(
                x = size.width.toDp().div(2).minus(dotSize.width.div(2).toDp()).toPx(),
                y = size.height.toDp().div(2).minus(dotSize.height.div(2).toDp()).toPx(),
            )
            drawRoundRect( // dot
                color = radioColors.value,
                size = dotSize,
                topLeft = offset,
                cornerRadius = cornerRadius,
            )
        }
    }
}
/*-----endregion-----*/
//endregion

/*================================================================================================*/
//region Supporting Functions/Fields
/*================================================================================================*/

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
    @Composable
    fun radioFillBrush(enabled: Boolean, selected: Boolean): State<Brush>
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
        disabledGradient: List<Color> =  listOf(MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled), MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)),
        selectedFillGradient: List<Color> =  listOf(MaterialTheme.colors.background.copy(alpha = 0f), MaterialTheme.colors.background.copy(alpha = 0f)),
        unselectedFillGradient: List<Color> = listOf(MaterialTheme.colors.background.copy(alpha = 0f), MaterialTheme.colors.background.copy(alpha = 0f)),
        disabledFillGradient: List<Color> =  listOf(MaterialTheme.colors.background.copy(alpha = 0f), MaterialTheme.colors.background.copy(alpha = 0f))
    ): RadioButtonBrushes {
        return remember(
            selectedGradient,
            unselectedGradient,
            disabledGradient,
            selectedFillGradient,
            unselectedFillGradient,
            disabledFillGradient
        ) {
            DefaultRadioButtonBrushes(selectedGradient, unselectedGradient, disabledGradient, selectedFillGradient, unselectedFillGradient, disabledFillGradient)
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
    private val disabledGradient: List<Color>,
    private val selectedFillGradient: List<Color>,
    private val unselectedFillGradient: List<Color>,
    private val disabledFillGradient: List<Color>

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

    @SuppressLint("UnrememberedMutableState")
    @Composable
    override fun radioFillBrush(enabled: Boolean, selected: Boolean): State<Brush> {
        val target = when {
            !enabled -> disabledFillGradient
            !selected -> unselectedFillGradient
            else -> selectedFillGradient
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
        if (selectedFillGradient != other.selectedFillGradient) return false
        if (unselectedFillGradient != other.unselectedFillGradient) return false
        if (disabledFillGradient != other.disabledFillGradient) return false

        return true
    }

    override fun hashCode(): Int {
        var result = selectedGradient.hashCode()
        result = 31 * result + unselectedGradient.hashCode()
        result = 31 * result + disabledGradient.hashCode()
        result = 31 * result + selectedFillGradient.hashCode()
        result = 31 * result + unselectedFillGradient.hashCode()
        result = 31 * result + disabledFillGradient.hashCode()
        return result
    }
}

private const val RadioBrushAnimationDuration = 100

private class MinimumTouchTargetModifier(val size: DpSize = DpSize(48.dp, 48.dp)) : LayoutModifier {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {

        val placeable = measurable.measure(constraints)

        // Be at least as big as the minimum dimension in both dimensions
        val width = maxOf(placeable.width, size.width.roundToPx())
        val height = maxOf(placeable.height, size.height.roundToPx())

        return layout(width, height) {
            val centerX = ((width - placeable.width) / 2f).roundToInt()
            val centerY = ((height - placeable.height) / 2f).roundToInt()
            placeable.place(centerX, centerY)
        }
    }

    override fun equals(other: Any?): Boolean {
        val otherModifier = other as? MinimumTouchTargetModifier ?: return false
        return size == otherModifier.size
    }

    override fun hashCode(): Int {
        return size.hashCode()
    }
}

@Composable
fun RadioButtonDefaults.fillColors(
        selectedColor: Color = MaterialTheme.colors.background.copy(alpha = 0f),
        unselectedColor: Color = MaterialTheme.colors.background.copy(alpha = 0f),
        disabledColor: Color = MaterialTheme.colors.background.copy(alpha = 0f)
    ): RadioButtonColors {
    return this.colors(selectedColor, unselectedColor, disabledColor)
}
//endregion