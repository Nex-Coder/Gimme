package co.uk.nexhub.gimme.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.uk.nexhub.gimme.BuildConfig
import co.uk.nexhub.gimme.R

@Composable
fun AppHeader(spacers: Dp = 25.dp) {
    Spacer(modifier = Modifier.height(spacers))

    Row(
        Modifier
            .fillMaxWidth(0.75f)
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.logo_alt_textless),
            "Logo alternative text-less"
        )

        Spacer(modifier = Modifier.width(6.dp))

        Image(painterResource(id = R.drawable.header), "Logo header")

        val version = BuildConfig.VERSION_NAME
        Column(
            Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text("  v$version", fontSize = 8.sp)
        }
    }

    Spacer(modifier = Modifier.height(spacers))
}