package co.uk.nexhub.gimme.ui.elements.controls

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonsGroup(choices: Array<RadioButtonChoice>) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }

    Column {
        choices.forEach { choice ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (choice.name == selectedOption),
                        onClick = {}
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (choice.name == selectedOption),
                    modifier = Modifier.padding(all = Dp(value = 8F)),
                    onClick = {
                        onOptionSelected(choice.name)
                        choice.action.invoke()
                    }
                )
                Text(
                    text = choice.name,
                    modifier = Modifier.padding(start = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

data class RadioButtonChoice(val name: String, val action: (() -> Unit))