package co.uk.nexhub.gimme.ui.legacy.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination

//@Destination
@Composable
fun AboutScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Hi, this is the about screen for Gimme.\n" +
                "Pretty barron for now, but stay tuned!")
    }
}