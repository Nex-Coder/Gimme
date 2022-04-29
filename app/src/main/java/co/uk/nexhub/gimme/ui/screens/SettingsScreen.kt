package co.uk.nexhub.gimme.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
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

        HeaderDivider(
            "General Settings",
            R.drawable.phone_android,
            Modifier.padding(bottom = 24.dp)
        )
        BoxWithContext(
            "Pingable",
            "Users can see & ping your device when you aren’t looking shares/files."
        ) {
            ToggleButton({})
        }

        HeaderDivider(
            "Text Field",
            R.drawable.phone_android,
            Modifier.padding(bottom = 24.dp)
        )
        BoxWithContext(
            "Text Field Setting",
            "Here is a text field setting example within this settings menu.",
            Modifier,
            false
        ) {
            var text by remember { mutableStateOf(TextFieldValue("")) }
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("e.g. my.host.co.uk") },
                colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = MaterialTheme.colors.primaryVariant)
            )
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    SettingsScreen(null)
}