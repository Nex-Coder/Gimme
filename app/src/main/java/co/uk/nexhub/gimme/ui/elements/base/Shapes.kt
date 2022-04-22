package co.uk.nexhub.gimme.ui.elements.base

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Circle(size: Dp) {
    Box(Modifier
        .background(Brush.linearGradient(listOf(MaterialTheme.colors.primaryVariant, MaterialTheme.colors.primary)), CircleShape)
        .border(1.dp, MaterialTheme.colors.primaryVariant, CircleShape)
        .size(size)
        .aspectRatio(1f)) {}
}