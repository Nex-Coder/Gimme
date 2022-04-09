package co.uk.nexhub.gimme.ui.elements.functioning

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import co.uk.nexhub.gimme.R
import co.uk.nexhub.gimme.ui.elements.BottomNavItem
import co.uk.nexhub.gimme.ui.elements.BottomNavigationBar
import co.uk.nexhub.gimme.ui.elements.currentDestination
import co.uk.nexhub.gimme.ui.navigation.tools.navigateToDestination
import co.uk.nexhub.gimme.ui.screens.destinations.*
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
                    name = "Send",
                    route = HomeScreenDestination(),
                    iconID =  R.drawable.download_gray,
                    maxWidth = 0.42f
                ),
                BottomNavItem(
                    name = "Receive",
                    route = ReceiveScreenDestination(),
                    iconID =  R.drawable.share_gray,
                    maxWidth = 0.38f
                ),
                BottomNavItem(
                    name = "Storage",
                    route = StorageScreenDestination(),
                    iconID =  R.drawable.phone_android_gray,
                    maxWidth = 0.41f,
                    badgeCount = 2
                ),
                BottomNavItem(
                    name = "Invite",
                    route = InviteScreenDestination(),
                    iconID =  R.drawable.sms_gray,
                    maxWidth = 0.35f,
                    badgeCount = 2
                ),
                BottomNavItem(
                    name = "Settings",
                    route = SettingsScreenDestination("Setting"),
                    iconID =  R.drawable.settings_gray,
                    maxWidth = 0.36f,
                    badgeCount = 2096
                )
            ),
            navController = navController,
            onItemClick = {
                currentDestination.postValue(it.route.route)
                navController.navigateToDestination(it.route)
            }
        )
    }
}

