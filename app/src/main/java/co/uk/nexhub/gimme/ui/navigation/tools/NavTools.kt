package co.uk.nexhub.gimme.ui.navigation.tools

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import co.uk.nexhub.gimme.ui.elements.navigation.isShowing
import co.uk.nexhub.gimme.ui.elements.navigation.show
import co.uk.nexhub.gimme.ui.elements.parents.currentDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.Direction


fun hideNavigation() {
    show(false)
}

fun showNavigation() {
    show(true)
}

fun toggleNavigation() {
    if (isNavigationShown() != null) {
        show(!isNavigationShown()!!)
    }
}

fun DestinationsNavigator.navigateToDestination(direction: Direction,
                                                onlyIfResumed: Boolean = false,
                                                builder: NavOptionsBuilder.() -> Unit = {}) {
    currentDestination.postValue(direction.route)
    this.navigate(direction, onlyIfResumed, builder)
}

fun NavController.navigateToDestination(route: Direction) {
    co.uk.nexhub.gimme.ui.elements.parents.currentDestination.postValue(route.route)
    this.navigateTo(route)
}

fun NavController.navigateToDestination(route: Direction, navOptionsBuilder: NavOptionsBuilder.() -> Unit) {
    co.uk.nexhub.gimme.ui.elements.parents.currentDestination.postValue(route.route)
    this.navigateTo(route, navOptionsBuilder)
}

fun isNavigationShown(): Boolean? {
    return isShowing()
}