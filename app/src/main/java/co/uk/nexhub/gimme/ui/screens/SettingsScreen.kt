package co.uk.nexhub.gimme.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.uk.nexhub.gimme.R
import co.uk.nexhub.gimme.ui.elements.base.BoxWithContext
import co.uk.nexhub.gimme.ui.elements.controls.*
import co.uk.nexhub.gimme.ui.elements.layout.AppHeader
import co.uk.nexhub.gimme.ui.elements.parents.DefaultScreenWrapper
import co.uk.nexhub.gimme.ui.elements.layout.HeaderDivider
import co.uk.nexhub.gimme.ui.model.GroupHost
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SettingsScreen(arg: String?) { // arg could be used in future to select a setting to scroll to.
    val scrollState = rememberScrollState()
    val contextBoxModifer = Modifier.padding(bottom = 20.dp)

    DefaultScreenWrapper(scrollState) {
        AppHeader(10.dp)

        HeaderDivider(
            "General Settings",
            R.drawable.phone_android,
            Modifier.padding(bottom = 24.dp)
        )
        BoxWithContext(
            "Pingable",
            "Users can see & ping your device when you aren’t looking shares/files.",
            contextBoxModifer
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
            contextBoxModifer,
            Modifier.padding(top = 10.dp),
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

        val optionsHost = remember { GroupHost("") }
        BoxWithContext(
            "Radio Buttons",
            "Users can see & ping your device when you aren’t looking shares/files.",
            contextBoxModifer.fillMaxWidth(),Modifier.fillMaxWidth(),
            inline = false
        ) {
            Column {
                val modifier = Modifier.padding(1.dp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    GimmeRadioButton(optionsHost.createValue("First"))
                    Text("Some Option 1")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    GimmeRadioButton(optionsHost.createValue("Second"))
                    Text("Some Option 2")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    GimmeRadioButton(optionsHost.createValue("Third"), enabled = false)
                    Text("Disabled Option 3")
                }
            }
        }

        BoxWithContext(
            "Link / Button",
            "Description around this button / link.",
            contextBoxModifer.fillMaxWidth(),
            contextWeight = 0.55f
        ) {
            val context = LocalContext.current
            GimmeRectangleButton(
                onClick = {
                    Toast.makeText(
                        context,
                        "Selected Value: " + optionsHost.getSelectedValue(),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                corner = 14.dp
            ) {
                Text(
                    text = "Confirm Selection",
                    color = MaterialTheme.colors.background.copy(0.8f),
                    fontSize = 12.sp
                )
            }
        }
    }
}


@Preview
@Composable
fun ComposablePreview() {
    SettingsScreen(null)
}