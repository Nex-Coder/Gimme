package co.uk.nexhub.gimme.ui.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.uk.nexhub.gimme.ui.theme.extras

@SuppressLint("ModifierParameter")
@Composable

        /**
         * VerticalDivider is essentially a pre-composed Divider made for dividing content vertically. Useful because it
         * has margin properties available.
         *
         * @param verticalMargin The amount of space between the divider and the vertical content.
         * Defaults to none or 0.dp.
         * @param fillMaxWidth The percentage width value to take that is available. Defaults to
         * 100% or 1f.
         * @param colors This property is only used if no modifier is given. If no modifier is
         * given, it will set the background colours for the default background's horizontal
         * gradient.
         * @param modifier The internal dividers modifier. Best left as default.
         * @param color  The colour of the divider.
         * @param thickness The thickness of the divider.
         *
         * @author Nex-Coder
         */
fun VerticalDivider(
    verticalMargin: Dp = 0.dp,
    fillMaxWidth: Float = 1f,
    colors: List<Color> = listOf(
        Color.Transparent,
        MaterialTheme.colors.primaryVariant,
        Color.Transparent),
    modifier: Modifier = Modifier
        .background(
            brush = Brush.horizontalGradient(
                colors = colors
            )
        )
        .fillMaxWidth(fillMaxWidth),
    color: Color = Color.Transparent,
    thickness: Dp = 1.dp)
{
    Spacer(modifier = Modifier.height(verticalMargin))
    Divider(
        modifier,
        color = color,
        thickness = thickness)
    Spacer(modifier = Modifier.height(verticalMargin))
}

@Composable
        /**
         * This composable will create an Icon & Text above a VerticalDivider. It is mainly intended
         * to divide content under is and clearly label the content within the header. This
         * behaviour is useful within a settings menu for example. All that is needed to get going
         * is the text string & integer ID of the drawable.
         *
         * The widths are
         *
         * @param text The header text to set.
         * @param drawableID The id for the drawable to be used in the internal painter. This ID
         * when then be used to display the icon/image.
         * @param modifier Used to set the modifier for the internal column that wraps this
         * composable. The fillMaxWidth modifier will be overwritten unless noDefaultModifications
         * is set to true.
         * @param imageWidth The width to set the image to. Best left default unless the icon
         * doesn't play nice.
         * @param imageHeight The height to set the image to. Best left default unless the icon
         * doesn't play nice. When set, it will also change the internal ratio that is automatically
         * set for the image, this can cause problems if you purposely set imageWidth to
         * null/unspecified manually.
         * @param noDefaultModifications If true then no fillMaxWidth will be set which will prevent
         * it being overwritten when supplying a custom modifier. Otherwise, fillMaxWidth will be
         * set and overwritten to 1f.
         * @param textModifier The modifier to be set for the internal text composable.
         * @param color [Color] to apply to the text. If [Color.Unspecified], and [style] has no color set,
         * this will be [LocalContentColor].
         * @param fontSize The size of glyphs to use when painting the text. See [TextStyle.fontSize].
         * @param fontStyle The typeface variant to use when drawing the letters (e.g., italic).
         * See [TextStyle.fontStyle].
         * @param fontWeight The typeface thickness to use when painting the text (e.g., [FontWeight.Bold]).
         * @param fontFamily The font family to be used when rendering the text. See [TextStyle.fontFamily].
         * @param letterSpacing The amount of space to add between each letter.
         * See [TextStyle.letterSpacing].
         * @param textDecoration The decorations to paint on the text (e.g., an underline).
         * See [TextStyle.textDecoration].
         * @param textAlign The alignment of the text within the lines of the paragraph.
         * See [TextStyle.textAlign].
         * @param lineHeight Line height for the Paragraph in [TextUnit] unit, e.g. SP or EM.
         * See [TextStyle.lineHeight].
         * @param overflow How visual overflow should be handled.
         * @param softWrap Whether the text should break at soft line breaks. If false, the glyphs in the
         * text will be positioned as if there was unlimited horizontal space. If [softWrap] is false,
         * [overflow] and TextAlign may have unexpected effects.
         * @param maxLines An optional maximum number of lines for the text to span, wrapping if
         * necessary. If the text exceeds the given number of lines, it will be truncated according to
         * [overflow] and [softWrap]. If it is not null, then it must be greater than zero.
         * @param onTextLayout Callback that is executed when a new text layout is calculated. A
         * [TextLayoutResult] object that callback provides contains paragraph information, size of the
         * text, baselines and other details. The callback can be used to add additional decoration or
         * functionality to the text. For example, to draw selection around the text.
         * @param style Style configuration for the text such as color, font, line height etc.
         */
fun HeaderDivider(text: String,
                  drawableID: Int,
                  modifier: Modifier = Modifier,
                  imageWidth: Dp = 30.dp,
                  imageHeight: Dp? = null,
                  iconSpace: Dp = 5.dp,
                  noDefaultModifications: Boolean = false,
                  textModifier: Modifier = Modifier,
                  color: Color = MaterialTheme.extras().solidColor.copy(alpha = 0.6f),
                  fontSize: TextUnit = 44.sp,
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
                  style: TextStyle = LocalTextStyle.current) {
    if (!noDefaultModifications) {
        modifier.fillMaxWidth(1f)
    }

    Column(modifier) {
        Row(Modifier, Arrangement.Start, Alignment.CenterVertically) {
            val painter = painterResource(drawableID)
            val intrinsicRatio = painter.intrinsicSize.width / painter.intrinsicSize.height
            var imageModifier = Modifier
                .width(imageWidth)
                .aspectRatio(intrinsicRatio)

            if (imageHeight == null) {
                imageModifier = imageModifier.aspectRatio(intrinsicRatio)
            } else {
                imageModifier = imageModifier.height(imageHeight)
            }

            Image(painter,
                "Settings Icon",
                imageModifier,
                Alignment.CenterStart,
                ContentScale.Fit,
                0.75f
            )

            Spacer(Modifier.width(iconSpace))

            Text(
                text,
                textModifier,
                color,
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
                style
            )
        }
        VerticalDivider(2.dp, 1f, listOf(
            MaterialTheme.colors.primary,
            MaterialTheme.colors.primaryVariant,
            MaterialTheme.colors.primary))
    }
}

@SuppressLint("ModifierParameter")
@Composable
        /**
         * Essentially a pre-composed Divider made for dividing content vertically. Useful because it
         * has margin properties available.
         * @param colors This property is only used if no modifier is given.
         */
fun HorizontalDivider(
    horizontalMargin: Dp = 0.dp,
    fillMaxHeight: Float = 1f,
    colors: List<Color> = listOf(
        Color.Transparent,
        MaterialTheme.colors.primaryVariant,
        Color.Transparent),
    modifier: Modifier = Modifier
        .background(
            brush = Brush.horizontalGradient(
                colors = colors
            )
        )
        .fillMaxHeight(fillMaxHeight)
        .width(1.dp),
    color: Color = Color.Transparent,
    thickness: Dp = 1.dp, )
{
    Spacer(modifier = Modifier.width(horizontalMargin))
    Divider(
        modifier,
        color = color,
        thickness = thickness)
    Spacer(modifier = Modifier.width(horizontalMargin))
}