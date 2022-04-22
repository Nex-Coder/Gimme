package co.uk.nexhub.gimme.ui.model

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.compositeOver


@Stable
interface ToggleButtonBrushes {
    @Composable
    fun backgroundBrush(enabled: Boolean): State<Brush>

    @Composable
    fun contentBrush(enabled: Boolean): State<Brush>

    @Composable
    fun borderBrush(enabled: Boolean): State<Brush>
}


object ToggleButtonDefaults {

    /**
     * Creates a [ToggleButtonBrushes] that represents the default background and content Brushs used in
     * a [ToggleButton].
     *
     * @param backgroundBrush the background Brush of this [ToggleButton] when enabled
     * @param contentBrush the content Brush of this [ToggleButton] when enabled
     * @param disabledBackgroundBrush the background Brush of this [ToggleButton] when not enabled
     * @param disabledContentBrush the content Brush of this [ToggleButton] when not enabled
     */
    @Composable
    fun toggleButtonBrushes(
        backgroundBrush: Brush = Brush.linearGradient(listOf(MaterialTheme.colors.primaryVariant.copy(0.75f), MaterialTheme.colors.onBackground.copy(0.15f))),
        contentBrush: Brush = Brush.linearGradient(listOf(MaterialTheme.colors.primary, MaterialTheme.colors.primaryVariant)),
        borderBrush: Brush = Brush.linearGradient(listOf(MaterialTheme.colors.primaryVariant, MaterialTheme.colors.primaryVariant.copy(0.80f))),
        disabledBackgroundBrush: Brush = Brush.linearGradient(listOf(MaterialTheme.colors.onBackground.copy(0.40f)
            .compositeOver(MaterialTheme.colors.surface),
            MaterialTheme.colors.onBackground.copy(0.30f)
                .compositeOver(MaterialTheme.colors.surface))),
        disabledContentBrush: Brush = Brush.linearGradient(listOf(MaterialTheme.colors.primary.copy(0.40f)
            .compositeOver(MaterialTheme.colors.surface),
            MaterialTheme.colors.primaryVariant.copy(0.30f)
                .compositeOver(MaterialTheme.colors.surface))),
        disabledBorderBrush: Brush = Brush.linearGradient(listOf(MaterialTheme.colors.primaryVariant.copy(0.40f)
            .compositeOver(MaterialTheme.colors.surface),
            MaterialTheme.colors.primaryVariant.copy(0.30f)
                .compositeOver(MaterialTheme.colors.surface)))
    ): ToggleButtonBrushes = DefaultToggleButtonBrushes(
        backgroundBrush = backgroundBrush,
        contentBrush = contentBrush,
        borderBrush = borderBrush,
        disabledBackgroundBrush = disabledBackgroundBrush,
        disabledContentBrush = disabledContentBrush,
        disabledBorderBrush = disabledBorderBrush
    )
}

/**
 * Default [ToggleButtonBrushes] implementation.
 */
@Immutable
private class DefaultToggleButtonBrushes(
    private val backgroundBrush: Brush,
    private val contentBrush: Brush,
    private val borderBrush: Brush,
    private val disabledBackgroundBrush: Brush,
    private val disabledContentBrush: Brush,
    private val disabledBorderBrush: Brush
) : ToggleButtonBrushes {

    @Composable
    override fun backgroundBrush(enabled: Boolean): State<Brush> {
        return rememberUpdatedState(if (enabled) backgroundBrush else disabledBackgroundBrush)
    }

    @Composable
    override fun contentBrush(enabled: Boolean): State<Brush> {
        return rememberUpdatedState(if (enabled) contentBrush else disabledContentBrush)
    }

    @Composable
    override fun borderBrush(enabled: Boolean): State<Brush> {
        return rememberUpdatedState(if (enabled) borderBrush else disabledBorderBrush)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DefaultToggleButtonBrushes

        if (backgroundBrush != other.backgroundBrush) return false
        if (contentBrush != other.contentBrush) return false
        if (disabledBackgroundBrush != other.disabledBackgroundBrush) return false
        if (disabledContentBrush != other.disabledContentBrush) return false

        return true
    }

    override fun hashCode(): Int {
        var result = backgroundBrush.hashCode()
        result = 31 * result + contentBrush.hashCode()
        result = 31 * result + disabledBackgroundBrush.hashCode()
        result = 31 * result + disabledContentBrush.hashCode()
        return result
    }
}
