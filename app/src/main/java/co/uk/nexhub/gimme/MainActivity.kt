package co.uk.nexhub.gimme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import co.uk.nexhub.gimme.ui.elements.functioning.BottomBar
import co.uk.nexhub.gimme.ui.navigation.tools.hideNavigation
import co.uk.nexhub.gimme.ui.screens.NavGraphs
import co.uk.nexhub.gimme.ui.theme.GimmeTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Default()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Default() {
    GimmeTheme { // A surface container using the 'background' color from the theme
        Surface(
            color = Color.DarkGray, // or MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize(),
        ) {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = { BottomBar(navController); /*hideNavigation()*/ },
            ) {
                DestinationsNavHost(navGraph = NavGraphs.root, navController = navController)
            }
        }
    }
}

