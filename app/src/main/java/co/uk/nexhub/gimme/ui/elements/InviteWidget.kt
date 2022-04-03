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
fun InviteWidget(modifier: Modifier) {
    Box(contentAlignment = Alignment.Center) {
        Row(modifier = modifier
            .clip(RoundedCornerShape(20))
            .background(
                MaterialTheme.extras().topBackground,
                RoundedCornerShape(20)
            )
            .padding(10.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Local", fontSize = 13.sp)

                Spacer(Modifier.height(20.dp))
            }
            HorizontalDivider()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Internet", fontSize = 13.sp)

                Spacer(Modifier.height(20.dp))
            }
        }
        Box(modifier.fillMaxSize()
            .clip(RoundedCornerShape(20))
            .background(
                Color.Gray.copy(alpha = 0.8f),
                RoundedCornerShape(20)
            ),
            contentAlignment = Alignment.Center) {
            Text("Invite Feature Coming in version 0.2.0", textAlign = TextAlign.Center)
        }
    }
}
