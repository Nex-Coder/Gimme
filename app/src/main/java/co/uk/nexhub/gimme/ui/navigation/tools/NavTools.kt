package co.uk.nexhub.gimme.ui.navigation.tools

import android.R
import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.Direction
import java.security.AccessController.getContext


fun hideNavigation() {
    co.uk.nexhub.gimme.ui.elements.functioning.show(false)
}

fun showNavigation() {
    co.uk.nexhub.gimme.ui.elements.functioning.show(true)
}

fun toggleNavigation() {
    if (isNavigationShown() != null) {
        co.uk.nexhub.gimme.ui.elements.functioning.show(!isNavigationShown()!!)
    }
}

fun DestinationsNavigator.navigateToDestination(direction: Direction,
                                                onlyIfResumed: Boolean = false,
                                                builder: NavOptionsBuilder.() -> Unit = {}) {
    co.uk.nexhub.gimme.ui.elements.currentDestination.postValue(direction.route)
    this.navigate(direction, onlyIfResumed, builder)
}

fun NavController.navigateToDestination(route: Direction) {
    co.uk.nexhub.gimme.ui.elements.currentDestination.postValue(route.route)
    this.navigateTo(route)
}

fun NavController.navigateToDestination(route: Direction, navOptionsBuilder: NavOptionsBuilder.() -> Unit) {
    co.uk.nexhub.gimme.ui.elements.currentDestination.postValue(route.route)
    this.navigateTo(route, navOptionsBuilder)
}

fun isNavigationShown(): Boolean? {
    return co.uk.nexhub.gimme.ui.elements.functioning.isShowing()
}