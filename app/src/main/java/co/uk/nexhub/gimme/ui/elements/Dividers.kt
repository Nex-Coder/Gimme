package co.uk.nexhub.gimme.ui.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("ModifierParameter")
@Composable
        /**
         * Essentially a pre-composed Divider made for dividing content vertically. Useful because it
         * has margin properties available.
         * @param colors This property is only used if no modifier is given.
         */
fun VerticalDivider(
    verticalMargin: Dp = 0.dp,
    fillMaxWidth: Float = 1f,
    colors: List<Color> = listOf(
        Color.Transparent,
        MaterialTheme.colors.primaryVariant,
        Color.Transparent),
    modifier: Modifier = Modifier
        .background(brush = Brush.horizontalGradient(
            colors = colors
        ))
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
        .background(brush = Brush.horizontalGradient(
            colors = colors
        ))
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