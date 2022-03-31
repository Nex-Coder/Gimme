package co.uk.nexhub.gimme.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.uk.nexhub.gimme.ui.screens.SplashScreen

@Composable
fun Navigation() {
    val navControl = rememberNavController()
    NavHost(navController = navControl, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navControl = navControl)
        }
        composable("home_screen") {
            Box(
                modifier = Modifier
            ) {
                Text(text = "Welcome Home", color = Color.White)
            }
        }
    }
}