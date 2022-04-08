package co.uk.nexhub.gimme.ui.legacy.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.uk.nexhub.gimme.model.NavigatingStandardData
import com.ramcosta.composedestinations.annotation.Destination

//@Destination
@Composable
fun ParamTestScreen(arg: NavigatingStandardData) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "You enter '${arg.args[0]}'")
    }
}