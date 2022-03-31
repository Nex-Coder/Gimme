package co.uk.nexhub.gimme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import co.uk.nexhub.gimme.ui.elements.functioning.BottomBar
import co.uk.nexhub.gimme.ui.screens.NavGraphs
import co.uk.nexhub.gimme.ui.theme.GimmeTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GimmeTheme { // A surface container using the 'background' color from the theme
                Surface(color = Color.DarkGray, // or MaterialTheme.colors.background
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()

                    Scaffold(
                        bottomBar = { BottomBar(navController) }
                    ) {
                        DestinationsNavHost(navGraph = NavGraphs.root, navController = navController)
                    }
                }
            }
        }
    }
}


/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GimmeTheme {

    }
}*/


