package co.uk.nexhub.gimme

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.uk.nexhub.gimme.ui.elements.BottomNavItem
import co.uk.nexhub.gimme.ui.elements.BottomNavigationBar
import co.uk.nexhub.gimme.ui.navigation.Navigation
import co.uk.nexhub.gimme.ui.navigation.Screen
import co.uk.nexhub.gimme.ui.theme.GimmeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GimmeTheme { // A surface container using the 'background' color from the theme
                Surface(color = Color.DarkGray, // or MaterialTheme.colors.background
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = listOf(
                                    BottomNavItem(
                                        name = "Home",
                                        route = Screen.HomeScreen.route,
                                        icon = Icons.Default.Home
                                    ),
                                    BottomNavItem(
                                        name = "About",
                                        route = Screen.AboutScreen.route,
                                        icon = Icons.Default.Info,
                                        badgeCount = 2
                                    ),
                                    BottomNavItem(
                                        name = "Settings",
                                        route = Screen.SettingsScreen.route,
                                        icon = Icons.Default.Settings,
                                        badgeCount = 2096
                                    )
                                ),
                                navController = navController,
                                onItemClick =  {
                                    navController.navigate(it.route)
                                }
                            )
                        }
                    ) {
                        Navigation(navController = navController)
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


