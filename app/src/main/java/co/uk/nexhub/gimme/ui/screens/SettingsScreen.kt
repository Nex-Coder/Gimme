package co.uk.nexhub.gimme.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.uk.nexhub.gimme.R
import co.uk.nexhub.gimme.ui.elements.base.BoxWithContext
import co.uk.nexhub.gimme.ui.elements.controls.ToggleButton
import co.uk.nexhub.gimme.ui.elements.layout.AppHeader
import co.uk.nexhub.gimme.ui.elements.parents.DefaultScreenWrapper
import co.uk.nexhub.gimme.ui.elements.layout.HeaderDivider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SettingsScreen(arg: String?) {
    val scrollState = rememberScrollState()

    DefaultScreenWrapper(scrollState) {
        AppHeader(10.dp)

        HeaderDivider("General Settings", R.drawable.phone_android, Modifier.padding(bottom = 24.dp))
        BoxWithContext("Pingable",
            "Users can see & ping your device when you aren’t looking shares/files."
        ) {
            var toggled by remember { mutableStateOf(false) }

            ToggleButton({}, enabled = true)
        }
    }
}