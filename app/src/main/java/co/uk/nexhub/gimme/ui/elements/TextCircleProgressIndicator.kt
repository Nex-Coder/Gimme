package co.uk.nexhub.gimme.ui.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas

@Composable
fun TextCircleProgressIndicatorHollow(modifier: Modifier = Modifier,
                                      progress: Float,
                                      color: Color = MaterialTheme.colors.primary,
                                      strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth,
                                      contentAlignment: Alignment = Alignment.Center,
                                      propagateMinConstraints: Boolean = false,
                                      text: String = String.format("%.0f%%",(progress * 100)),
                                      textColor: Color = Color.Unspecified,
                                      fontSize: TextUnit = TextUnit.Unspecified,
                                      fontSizeAverageScaling: Boolean = true,
                                      fontStyle: androidx.compose.ui.text.font.FontStyle? = null,
                                      fontWeight: FontWeight? = null,
                                      fontFamily: FontFamily? = null,
                                      letterSpacing: TextUnit = TextUnit.Unspecified,
                                      textDecoration: TextDecoration? = null,
                                      textAlign: TextAlign = TextAlign.Center,
                                      lineHeight: TextUnit = TextUnit.Unspecified,
                                      overflow: TextOverflow = TextOverflow.Clip,
                                      softWrap: Boolean = true,
                                      maxLines: Int = Int.MAX_VALUE,
                                      onTextLayout: (TextLayoutResult) -> Unit = {},
                                      style: TextStyle = LocalTextStyle.current
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    Box(modifier.aspectRatio(1f).onSizeChanged {
        size = it
    }, contentAlignment, propagateMinConstraints) {
        val modifierSize = Modifier.width(size.width.dp).height(size.height.dp)
        CircularProgressIndicator(progress, modifierSize, color, strokeWidth)
        Text(text,
            Modifier,
            textColor,
            if (fontSize == TextUnit.Unspecified) {size.width.sp / 9} else { if (fontSizeAverageScaling) {fontSize / (size.width / 400)} else {fontSize}},
            fontStyle,
            fontWeight,
            fontFamily,
            letterSpacing,
            textDecoration,
            textAlign,
            lineHeight,
            overflow,
            softWrap,
            maxLines,
            onTextLayout,
            style)
    }
}

@Composable
fun TextCircleProgressIndicator(progress: Float, modifier: Modifier = Modifier, brush: Brush,
                                textColor: Color = Color.Unspecified,
                                fontSize: TextUnit = TextUnit.Unspecified,
                                fontSizeAverageScaling: Boolean = true,
                                fontStyle: androidx.compose.ui.text.font.FontStyle? = null,
                                fontWeight: FontWeight? = null,
                                fontFamily: FontFamily? = null,
                                letterSpacing: TextUnit = TextUnit.Unspecified,
                                textDecoration: TextDecoration? = null,
                                textAlign: TextAlign = TextAlign.Center,
                                lineHeight: TextUnit = TextUnit.Unspecified,
                                overflow: TextOverflow = TextOverflow.Clip,
                                softWrap: Boolean = true,
                                maxLines: Int = Int.MAX_VALUE,
                                onTextLayout: (TextLayoutResult) -> Unit = {},
                                style: TextStyle = LocalTextStyle.current,
    centerPaddingAdjustment: Dp = 12.dp) {
    Box(modifier, Alignment.Center) {
        Canvas(Modifier.width(145.dp).height(145.dp)) {
            drawArc(brush = brush, startAngle = 270f, sweepAngle = progress * 360, useCenter = true)
        }

        Text(String.format("%.0f%%",(progress * 100)),
            Modifier.fillMaxWidth().padding(top = centerPaddingAdjustment),
            textColor,
            fontSize,
            fontStyle,
            fontWeight,
            fontFamily,
            letterSpacing,
            textDecoration,
            textAlign,
            lineHeight,
            overflow,
            softWrap,
            maxLines,
            onTextLayout,
            style)
    }
}