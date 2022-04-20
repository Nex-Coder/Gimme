package co.uk.nexhub.gimme.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.uk.nexhub.gimme.ui.elements.AppHeader
import co.uk.nexhub.gimme.ui.elements.DefaultScreenWrapper
import co.uk.nexhub.gimme.ui.theme.extras
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun InviteScreen(arg: String?) {
    val scrollState = rememberScrollState()

    DefaultScreenWrapper(scrollState) {
        AppHeader()

            Spacer(Modifier.height(275.dp))
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text("The ability to invite & share this app to other devices will come later.",
                color = MaterialTheme.extras().solidColor.copy(0.6f)
            )
        }
    }
}