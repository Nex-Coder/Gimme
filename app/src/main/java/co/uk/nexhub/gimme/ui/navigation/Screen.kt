package co.uk.nexhub.gimme.ui.navigation

sealed class Screen(val route : String) {
    object SplashScreen : Screen("splash")
    object HomeScreen : Screen("home")
    object AboutScreen : Screen("about")

    fun withRequiredArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

