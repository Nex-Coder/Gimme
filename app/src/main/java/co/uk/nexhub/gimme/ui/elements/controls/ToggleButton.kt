package co.uk.nexhub.gimme.ui.elements.controls

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.uk.nexhub.gimme.ui.elements.base.Circle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ToggleButton(onClick: ((Boolean) -> Unit), toggled: Boolean = false, width: Dp = 95.dp) {
    val aspectRatio = 2.8f  // TODO Animate toggle, add outer shadow

    Row(
        Modifier
            .width(width)
            .aspectRatio(aspectRatio)
            .background(MaterialTheme.colors.primary.copy(0.3f), CircleShape)
            .border(1.dp, MaterialTheme.colors.primaryVariant, CircleShape)
            .clip(CircleShape)
            .clickable {onClick.invoke(toggled)},
        Arrangement.Start,
        Alignment.CenterVertically
    ) {
        Spacer(Modifier.fillMaxWidth(if (toggled) 0.66f else 0.02f))
        Circle(width.div(aspectRatio).minus(4.dp))
    }
}