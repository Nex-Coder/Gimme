package co.uk.nexhub.gimme.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.uk.nexhub.gimme.ui.elements.AppHeader
import co.uk.nexhub.gimme.ui.elements.DefaultScreenWrapper
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun InviteScreen(arg: String?) {
    val scrollState = rememberScrollState()

    DefaultScreenWrapper(scrollState) {
        AppHeader()
    }
}