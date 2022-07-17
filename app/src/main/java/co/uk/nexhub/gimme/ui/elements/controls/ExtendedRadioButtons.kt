package co.uk.nexhub.gimme.ui.elements.controls

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import co.uk.nexhub.gimme.ui.model.GroupValue
import kotlin.math.roundToInt

/*================================================================================================*/
//region Controls
/*================================================================================================*/
/*-----region Standard Radio Buttons-----*/
@Composable
fun <T> ExtendedRadioButton(
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
    groupValue: GroupValue<T>? = null
) {
    val buttonStrokeWidth = strokeWidth * scale
    val buttonRippleRadius = RadioButtonRippleRadius * scale
    val buttonSize = RadioButtonSize * scale
    val buttonDotRadius = (RadioButtonDotSize * dotMultiplier * scale) / 2
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
                onClick = { groupValue?.setHostValue(); onClick.invoke()},
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
                (radius - (buttonStrokeWidth / 2)).toPx(),
            )
        }
        drawCircle( // Stroke
            radioColors.value,
            (radius - (buttonStrokeWidth / 2)).toPx(),
            style = Stroke(width = buttonStrokeWidth.toPx())
        )
        if (dotRadius > 0.dp) { // Dot
            drawCircle(radioColors.value, (dotRadius - (buttonStrokeWidth / 2)).toPx(), style = Fill)
        }
    }
}

@Composable
fun <T> ExtendedRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    brushes: RadioButtonBrushes = RadioButtonBrushDefaults.brushes(),
    dotMultiplier: Float = 1f,
    scale: Float = 1f,
    strokeWidth: Dp = RadioStrokeWidth * scale,
    groupValue: GroupValue<T>? = null
) {
    val buttonStrokeWidth = strokeWidth * scale
    val buttonRippleRadius = RadioButtonRippleRadius * scale
    val buttonSize = RadioButtonSize * scale
    val buttonDotRadius = (RadioButtonDotSize * dotMultiplier * scale) / 2
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
                onClick = { groupValue?.setHostValue(); onClick.invoke()},
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
                (radius - (buttonStrokeWidth / 2)).toPx(),
            )
        }
        drawCircle( // Stroke
            radioBrush.value,
            (radius - (buttonStrokeWidth / 2)).toPx(),
            style = Stroke(width = buttonStrokeWidth.toPx())
        )
        if (dotRadius > 0.dp) { // Dot
            drawCircle(radioBrush.value, (dotRadius - (buttonStrokeWidth / 2)).toPx(), style = Fill)
        }
    }
}
/*-----endregion-----*/

/*-----region Shaped Radio Buttons-----*/
@Composable
fun <T> RoundedExtendedRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    brushes: RadioButtonBrushes = RadioButtonBrushDefaults.brushes(),
    dotMultiplier: Float = 1f,
    scale: Float = 1f,
    strokeWidth: Dp = RadioStrokeWidth,
    cornerRadius: CornerRadius = CornerRadius(
        x = (18.dp * scale).value,
        y = (18.dp * scale).value
    ),
    cornerRadiusDot: CornerRadius = cornerRadius,
    groupValue: GroupValue<T>? = null
) {
    val buttonStrokeWidth = strokeWidth * scale
    val buttonRippleRadius = RadioButtonRippleRadius * scale
    val buttonSize = RadioButtonSize * scale
    val buttonDotSize = RadioButtonDotSize * dotMultiplier * scale

    val dotRadius by animateDpAsState(
        targetValue = if (selected) buttonDotSize else 0.dp,
        animationSpec = tween(durationMillis = RadioAnimationDuration)
    )
    val radioBrush = brushes.radioBrush(enabled, selected)
    val radioFillBrush = brushes.radioFillBrush(enabled, selected)
    val radioStrokeBrush = brushes.radioStrokeBrush(enabled, selected)
    val selectableModifier =
        if (onClick != null) {
            Modifier.selectable(
                selected = selected,
                onClick = { groupValue?.setHostValue(); onClick.invoke()},
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

    val strokeWidthRadius = (buttonStrokeWidth / 2)
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
            width = (buttonSize - (buttonStrokeWidth / 1)).toPx(),
            height = (buttonSize - (buttonStrokeWidth / 1)).toPx()
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
            brush = radioStrokeBrush.value,
            topLeft = Offset(
                x = strokeWidthRadius.toPx(),
                y = strokeWidthRadius.toPx(),
            ),
            size = size,
            cornerRadius = cornerRadius,
            style = Stroke(width = buttonStrokeWidth.toPx())
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
                cornerRadius = cornerRadiusDot,
            )
        }
    }
}

@Composable
fun <T> RoundedExtendedRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    fillColors: RadioButtonColors = RadioButtonDefaults.fillColors(),
    strokeColor: RadioButtonColors = colors,
    dotMultiplier: Float = 1f,
    scale: Float = 1f,
    strokeWidth: Dp = RadioStrokeWidth,
    cornerRadius: CornerRadius = CornerRadius(
        x = (18.dp * scale).value,
        y = (18.dp * scale).value
    ),
    cornerRadiusDot: CornerRadius = cornerRadius,
    groupValue: GroupValue<T>? = null
) {
    val buttonStrokeWidth = strokeWidth * scale
    val buttonRippleRadius = RadioButtonRippleRadius * scale
    val buttonSize = RadioButtonSize * scale
    val buttonDotSize = RadioButtonDotSize * dotMultiplier * scale

    val dotRadius by animateDpAsState(
        targetValue = if (selected) buttonDotSize else 0.dp,
        animationSpec = tween(durationMillis = RadioAnimationDuration)
    )
    val radioColors = colors.radioColor(enabled, selected)
    val radioFillColors = fillColors.radioColor(enabled, selected)
    val strokeColors = strokeColor.radioColor(enabled, selected)

    val selectableModifier =
        if (onClick != null) {
            Modifier.selectable(
                selected = selected,
                onClick = { groupValue?.setHostValue(); onClick.invoke()},
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
            width = (buttonSize - (buttonStrokeWidth / 2)).toPx(),
            height = (buttonSize - (buttonStrokeWidth / 2)).toPx()
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
            color = strokeColors.value,
            size = size,
            cornerRadius = cornerRadius,
            style = Stroke(width = buttonStrokeWidth.toPx())
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
                cornerRadius = cornerRadiusDot,
            )
        }
    }
}
/*-----endregion-----*/
//endregion

/*================================================================================================*/
//region Presets Built On-Top
/*================================================================================================*/

/*-----region Shaped Radio Buttons-----*/
@Composable
fun <T> GimmeRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    brushes: RadioButtonBrushes = RadioButtonBrushDefaults.brushes(
        gradientType = GradientType.Horizontal,
        gradientFillType = GradientType.Horizontal,
        selectedFillGradient = listOf(MaterialTheme.colors.primary.copy(0.4f), MaterialTheme.colors.primary.copy(0.3f)),
        unselectedFillGradient = listOf(MaterialTheme.colors.primary.copy(0.2f), MaterialTheme.colors.primary.copy(0.13f)),
        selectedStrokeGradient = listOf(MaterialTheme.colors.primaryVariant, MaterialTheme.colors.primary),
        unselectedStrokeGradient = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.primary)
    ),
    dotMultiplier: Float = 1.35f,
    scale: Float = 2f,
    strokeWidth: Dp = 1.dp,
    cornerRadius: CornerRadius = CornerRadius(
        x = (20.dp * scale).value,
        y = (20.dp * scale).value
    ),
    cornerRadiusDot: CornerRadius = CornerRadius(
        x = (18.dp * scale).value,
        y = (18.dp * scale).value
    ),
    groupValue: GroupValue<T>? = null
){
    RoundedExtendedRadioButton(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        brushes = brushes,
        dotMultiplier = dotMultiplier,
        scale = scale,
        strokeWidth = strokeWidth,
        cornerRadius = cornerRadius,
        cornerRadiusDot = cornerRadiusDot,
        groupValue = groupValue
    )
}
//endregion
//endregion
/*================================================================================================*/
//region Supporting Functions/Fields
/*================================================================================================*/

private const val RadioAnimationDuration = 100

private val RadioButtonRippleRadius = 24.dp
private val RadioButtonSize = 20.dp
private val RadioButtonDotSize = 12.dp
private val RadioStrokeWidth = 2.dp
private val brushDefaultSpring = spring<Brush>()
/**
 * Represents the brush used by a [RadioButton] in different states.
 *
 * See [RadioButtonBrushDefaults.brushes] for the default implementation that follows Material
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
    @Composable
    fun radioStrokeBrush(enabled: Boolean, selected: Boolean): State<Brush>
    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun animateGradientAsState(targetGradient: List<Color>,
                               animationSpec: AnimationSpec<Brush> = brushDefaultSpring,
                               finishedListener: ((Brush) -> Unit)? = null
    ): MutableList<Color> {

        val newGradient = mutableListOf<Color>()

        for ((i, targetValue) in targetGradient.withIndex()) {
            newGradient.add(i, animateColorAsState(targetValue, tween(durationMillis = RadioBrushAnimationDuration)).value)
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
     * @param selectedGradient the Brush to use for the RadioButton when selected and enabled.
     * @param unselectedGradient the Brush to use for the RadioButton when unselected and enabled.
     * @param disabledGradient the Brush to use for the RadioButton when disabled.
     * @return the resulting [RadioButtonBrushes] used for the RadioButton
     */
    @Composable
    fun brushes(
        selectedGradient: List<Color> = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.primaryVariant),
        unselectedGradient: List<Color> = listOf(MaterialTheme.colors.onSurface.copy(alpha = 0.6f), MaterialTheme.colors.onSurface.copy(alpha = 0.3f)),
        disabledGradient: List<Color> =  listOf(MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled), MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)),
        gradientType: GradientType = GradientType.Radial,
        selectedFillGradient: List<Color> =  listOf(MaterialTheme.colors.background.copy(alpha = 0f), MaterialTheme.colors.background.copy(alpha = 0f)),
        unselectedFillGradient: List<Color> = listOf(MaterialTheme.colors.background.copy(alpha = 0f), MaterialTheme.colors.background.copy(alpha = 0f)),
        disabledFillGradient: List<Color> =  listOf(MaterialTheme.colors.background.copy(alpha = 0f), MaterialTheme.colors.background.copy(alpha = 0f)),
        gradientFillType: GradientType = GradientType.Radial,
        selectedStrokeGradient: List<Color> =  selectedGradient,
        unselectedStrokeGradient: List<Color> = unselectedGradient,
        disabledStrokeGradient: List<Color> =  disabledGradient,
        gradientStrokeType: GradientType = gradientType,
    ): RadioButtonBrushes {
        return remember(
            selectedGradient,
            unselectedGradient,
            disabledGradient,
            gradientType,
            selectedFillGradient,
            unselectedFillGradient,
            disabledFillGradient,
            gradientFillType,
            selectedStrokeGradient,
            unselectedStrokeGradient,
            disabledStrokeGradient,
            gradientStrokeType
        ) {
            DefaultRadioButtonBrushes(selectedGradient, unselectedGradient, disabledGradient, gradientType, selectedFillGradient, unselectedFillGradient, disabledFillGradient, gradientFillType, selectedStrokeGradient, unselectedStrokeGradient, disabledStrokeGradient, gradientStrokeType)
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
    private val gradientType: GradientType,
    private val selectedFillGradient: List<Color>,
    private val unselectedFillGradient: List<Color>,
    private val disabledFillGradient: List<Color>,
    private val gradientFillType: GradientType,
    private val selectedStrokeGradient: List<Color>,
    private val unselectedStrokeGradient: List<Color>,
    private val disabledStrokeGradient: List<Color>,
    private val gradientStrokeType: GradientType,

    ) : RadioButtonBrushes {
    @SuppressLint("UnrememberedMutableState")
    @Composable
    override fun radioBrush(enabled: Boolean, selected: Boolean): State<Brush> {
        val target: List<Color> = when {
            !enabled -> disabledGradient
            !selected -> unselectedGradient
            else -> selectedGradient
        }

        return getGradientTarget(enabled, target, gradientType)
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    override fun radioFillBrush(enabled: Boolean, selected: Boolean): State<Brush> {
        val target = when {
            !enabled -> disabledFillGradient
            !selected -> unselectedFillGradient
            else -> selectedFillGradient
        }

        return getGradientTarget(enabled, target, gradientFillType)
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    override fun radioStrokeBrush(enabled: Boolean, selected: Boolean): State<Brush> {
        val target = when {
            !enabled -> disabledStrokeGradient
            !selected -> unselectedStrokeGradient
            else -> selectedStrokeGradient
        }

        return getGradientTarget(enabled, target, gradientStrokeType)
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    private fun getGradientTarget(enabled: Boolean, target: List<Color>, gradient: GradientType): MutableState<Brush> {
        return mutableStateOf(
            when (gradient) {
                GradientType.Linear -> Brush.linearGradient(getAnimatedGradientTarget(enabled, target))
                GradientType.Horizontal -> Brush.horizontalGradient(getAnimatedGradientTarget(enabled, target))
                GradientType.Vertical -> Brush.verticalGradient(getAnimatedGradientTarget(enabled, target))
                GradientType.Radial -> Brush.radialGradient(getAnimatedGradientTarget(enabled, target))
            }
        )
    }

    @Composable
    private fun getAnimatedGradientTarget(enabled: Boolean, target: List<Color>): List<Color> {
        return if (enabled)
            animateGradientAsState(target, tween(durationMillis = RadioBrushAnimationDuration)) else
            animateGradientAsState(target)
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

enum class GradientType {
    Linear,
    Horizontal,
    Vertical,
    Radial
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