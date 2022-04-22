package co.uk.nexhub.gimme.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.uk.nexhub.gimme.R
import co.uk.nexhub.gimme.ui.elements.controls.GimmeCircleButton
import co.uk.nexhub.gimme.ui.elements.controls.GimmeRectangleButton
import co.uk.nexhub.gimme.ui.elements.layout.AppHeader
import co.uk.nexhub.gimme.ui.elements.layout.VerticalDivider
import co.uk.nexhub.gimme.ui.elements.parents.DefaultScreenWrapper
import co.uk.nexhub.gimme.ui.theme.extras
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ReceiveScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    DefaultScreenWrapper(scrollState) {
        AppHeader()

        GimmeCircleButton(imageId = R.drawable.radar_download_gray) {
            Text(
                "Gimme Files",
                fontSize = 54.sp,
                color = MaterialTheme.colors.background.copy(alpha = 0.85f)
            )
        }

        Text(
            "Extras",
            fontSize = 20.sp,
            color = MaterialTheme.extras().solidColor.copy(alpha = 0.2f)
        )
        VerticalDivider(5.dp)

        Spacer(Modifier.height(8.dp))

        val height = 80.dp
        GimmeRectangleButton(imageId = R.drawable.contacts_gray, imageScale = 0.15f, width = 390.dp, height = height, corner = 15.dp) {
            Text(
                "Add Auto-Receiver",
                fontSize = 22.sp,
                color = MaterialTheme.colors.background.copy(alpha = 0.85f)
            )
        }

        Spacer(Modifier.height(8.dp))

        GimmeRectangleButton(imageId = R.drawable.prset_download_gray, imageScale = 0.15f, width = 390.dp, height = height, corner = 15.dp) {
            Text(
                "Gimme Via Preset",
                fontSize = 22.sp,
                color = MaterialTheme.colors.background.copy(alpha = 0.85f)
            )
        }
    }
}