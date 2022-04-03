package co.uk.nexhub.gimme.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun SpaceWidgetItem(modifier: Modifier = Modifier,
                    name: String,
                    icon: Painter,
                    percentage: Float? = null,
                    value: String? = null,
                    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
                    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
                    iconModifier: Modifier = Modifier,
                    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                    textModifier: Modifier = Modifier,
                    color: Color = Color.Unspecified,
                    fontSize: TextUnit = TextUnit.Unspecified,
                    fontStyle: FontStyle? = null,
                    fontWeight: FontWeight? = null,
                    fontFamily: FontFamily? = null,
                    letterSpacing: TextUnit = TextUnit.Unspecified,
                    textDecoration: TextDecoration? = null,
                    textAlign: TextAlign? = null,
                    lineHeight: TextUnit = TextUnit.Unspecified,
                    overflow: TextOverflow = TextOverflow.Clip,
                    softWrap: Boolean = true,
                    maxLines: Int = Int.MAX_VALUE,
                    onTextLayout: (TextLayoutResult) -> Unit = {},
                    style: TextStyle = LocalTextStyle.current
) {
    val progress = if (percentage != null) String.format("%.0f%%",(percentage * 100)) else null
    val text = if (progress == null && value == null)  "$name - Error"
    else if (progress == null)  "$name - $value"
    else if (value == null)  "$name - $progress"
    else  "$name - $value / $progress"

    Row(modifier, horizontalArrangement, verticalAlignment) {
        Image(icon, name, iconModifier.aspectRatio(1.22f))
        Text(text, textModifier, color, fontSize, fontStyle, fontWeight, fontFamily, letterSpacing, textDecoration, textAlign, lineHeight, overflow, softWrap, maxLines, onTextLayout, style)
    }
}