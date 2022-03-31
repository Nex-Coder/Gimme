package co.uk.nexhub.gimme.ui.elements.functioning

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import co.uk.nexhub.gimme.ui.elements.BottomNavItem
import co.uk.nexhub.gimme.ui.elements.BottomNavigationBar
import co.uk.nexhub.gimme.ui.screens.destinations.AboutScreenDestination
import co.uk.nexhub.gimme.ui.screens.destinations.HomeScreenDestination
import co.uk.nexhub.gimme.ui.screens.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.navigation.navigateTo



val isShown = MutableLiveData(true)

fun show(isShowing: Boolean) {
    isShown.postValue(isShowing)
}

fun isShowing(): Boolean? {
    return isShown.value
}

@Composable
fun BottomBar(navController: NavController) {
    val isShown: Boolean? by isShown.observeAsState()

    isShown?.let {
        BottomNavigationBar(
            show = it,
            items = listOf(
                BottomNavItem(
                    name = "Home",
                    route = HomeScreenDestination(),
                    icon = Icons.Default.Home
                ),
                BottomNavItem(
                    name = "About",
                    route = AboutScreenDestination(),
                    icon = Icons.Default.Info,
                    badgeCount = 2
                ),
                BottomNavItem(
                    name = "Settings",
                    route = SettingsScreenDestination("Setting"),
                    icon = Icons.Default.Settings,
                    badgeCount = 2096
                )
            ),
            navController = navController,
            onItemClick = {
                navController.navigateTo(it.route) {
                    launchSingleTop = true
                }
            }
        )
    }
}