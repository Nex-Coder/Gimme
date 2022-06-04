package co.uk.nexhub.gimme.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun SettingsScreen(arg: String?) {
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
        BoxWithContext(
            "Radio Buttons",
            "Users can see & ping your device when you aren’t looking shares/files.",
            contextBoxModifer.fillMaxWidth(),Modifier.fillMaxWidth(),
            inline = false
        ) {
            val choices = arrayOf(RadioButtonChoice("Option One", { println("Option One") }), RadioButtonChoice("Option Two", { println("Option Two") }))
            //TODO do grouping functionality. incoropate a generic way to use a list to automatically generate the buttons and groups
            Column() {
                val modifier = Modifier.padding(1.dp)
                val someOptionsHost = remember { GroupHost("") };

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val option1 = someOptionsHost.createValue("First")
                    ExtendedRadioButton(option1.isSelected(), {}, modifier, groupValue = option1)
                    Text("Some Option 1")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val option2 = someOptionsHost.createValue("Second")
                    ExtendedRadioButton(option2.isSelected(), {}, modifier, groupValue = option2)
                    Text("Some Option 2")
                }

                val context = LocalContext.current
                Button(onClick = {
                    Toast.makeText(
                        context,
                        "Selected Value: " + someOptionsHost.getSelectedValue(),
                        Toast.LENGTH_SHORT
                    ).show()
                }, content = {
                    Text(text = "Show Selected (Toast)")
                })
            }
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    SettingsScreen(null)
}