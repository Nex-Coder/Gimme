package co.uk.nexhub.gimme.ui.navigation

sealed class Screen(val route : String) {
    object SplashScreen : Screen("splash")
    object HomeScreen : Screen("home")
    object ParamTestScreen : Screen("param_test")
    object AboutScreen : Screen("about") //TODO fix this showing settings page
    object SettingsScreen : Screen("settings")

    fun withRequiredArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

