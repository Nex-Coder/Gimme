package co.uk.nexhub.gimme.ui.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.uk.nexhub.gimme.ui.theme.extras

@Composable
fun SpaceWidget(modifier: Modifier) {
    Row(modifier = modifier
        .clip(RoundedCornerShape(20))
        .background(
            MaterialTheme.extras().topBackground,
            RoundedCornerShape(20)
        )
        .padding(10.dp),
        verticalAlignment = Alignment.Top) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            //Text("Internal", fontSize = 13.sp)

            Spacer(Modifier.height(2.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = co.uk.nexhub.gimme.R.drawable.phone_android),
                    contentDescription = "Storage",
                    Modifier.height(18.dp)
                )
                Text("Storage", fontSize = 8.sp)
            }

            Spacer(Modifier.padding(vertical = 2.dp))

            TextCircleProgressIndicatorHollow(progress = 1f, modifier = Modifier.width(45.dp))
        }

        HorizontalDivider(10.dp, 1f)


        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            ComposeItem(painterResource(co.uk.nexhub.gimme.R.drawable.android), "App", "500MB", 1f, MaterialTheme.colors.primary)
            ComposeItem(painterResource(co.uk.nexhub.gimme.R.drawable.video_icon_96_xxxhdpi), "Video",  "500MB", 1f, MaterialTheme.colors.secondary)
            ComposeItem(painterResource(co.uk.nexhub.gimme.R.drawable.camera), "Photo", "500MB", 1f, MaterialTheme.extras().tertiary)
            ComposeItem(painterResource(co.uk.nexhub.gimme.R.drawable.file_96_hdpi), "Other", "500MB", 1f, MaterialTheme.extras().quaternary)
        }
    }
}

@Composable
@SuppressLint("ModifierParameter")
private fun ComposeItem(icon: Painter, name: String, byteSize: String, percentage: Float, color: Color, fontSize: TextUnit = 11.sp, iconModifier: Modifier = Modifier.height(24.dp)) {
    SpaceWidgetItem(
        icon,
        name,
        byteSize,
        percentage,
        Modifier
            .clip(CircleShape)
            .background(
                color,
                RoundedCornerShape(50)
            )
            .fillMaxWidth(),
        iconModifier = iconModifier,
        color = MaterialTheme.extras().topBackground,
        textAlign = TextAlign.Left,
        fontSize = fontSize,
        maxLines = 1)

    Spacer(Modifier.height(5.dp))
}