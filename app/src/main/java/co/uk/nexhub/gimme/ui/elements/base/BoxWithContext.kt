package co.uk.nexhub.gimme.ui.elements.base

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import co.uk.nexhub.gimme.ui.theme.extras
import co.uk.nexhub.gimme.ui.theme.fontCambay

@Composable
fun BoxWithContext(name: String,
                   description: String,
                   modifier: Modifier = Modifier,
                   boxModifier: Modifier = Modifier,
                   inline: Boolean = true,
                   contextWeight: Float = if (inline) 0.68f else 1f,
                   nameColor: Color = MaterialTheme.extras().solidColor.copy(alpha = 0.9f),
                   descriptionColor: Color = nameColor.copy(alpha = 0.6f),
                   fontSize: TextUnit = 30.sp,
                   content: @Composable BoxScope.() -> Unit) {
    val contextWeightFinal: Float = if (contextWeight > 0.98f) 0.98f else contextWeight
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(contextWeightFinal, true), Arrangement.Top, Alignment.Start) {
            Text(name, Modifier, nameColor, fontSize, fontFamily = fontCambay)
            Text(description, Modifier, descriptionColor, fontSize.div(2.1f), fontFamily = fontCambay)
        }
        if (inline) {
            Box(boxModifier.weight(0.99f - contextWeightFinal, false), Alignment.CenterEnd, false, content)
        }
    }
    if (!inline) {
        Box(boxModifier, Alignment.CenterStart, false, content)
    }
}