package co.uk.nexhub.gimme.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.uk.nexhub.gimme.ui.screens.AboutScreen
import co.uk.nexhub.gimme.ui.screens.HomeScreen
import co.uk.nexhub.gimme.ui.screens.ParamTestScreen
import co.uk.nexhub.gimme.ui.screens.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.ParamTestScreen.route + "/{arg}", // optional alternative would be ?arg={arg}
            arguments = listOf(
                navArgument("arg") {
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                }
            )) { entry ->
            ParamTestScreen(arg = entry.arguments?.getString("arg"))
        }
        composable(route = Screen.AboutScreen.route) {
            AboutScreen()
        }
        composable(route = Screen.SettingsScreen.route) {
            AboutScreen()
        }
    }
}