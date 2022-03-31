package co.uk.nexhub.gimme.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.uk.nexhub.gimme.model.NavigatingStandardData
import co.uk.nexhub.gimme.ui.navigation.tools.*
import co.uk.nexhub.gimme.ui.screens.destinations.ParamTestScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigateTo

@Destination
@Composable
fun HomeScreen(navController: NavController) {
    var text by rememberSaveable {
        mutableStateOf("Test")
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        TextField(value = text, onValueChange = {text = it}, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            navController.navigateTo(ParamTestScreenDestination(arg = NavigatingStandardData(args = listOf(text)))) {
                launchSingleTop = true
            }
        },
            modifier = Modifier.align(Alignment.End)) {
            Text(text = "Some Navigation")
        }

    }
}