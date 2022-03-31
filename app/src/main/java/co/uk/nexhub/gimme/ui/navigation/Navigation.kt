package co.uk.nexhub.gimme.ui.navigation


/*@Composable
fun Navigation(navigator: DestinationsNavigator) {
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
            SettingsScreen("Settings")
        }
    }
}*/