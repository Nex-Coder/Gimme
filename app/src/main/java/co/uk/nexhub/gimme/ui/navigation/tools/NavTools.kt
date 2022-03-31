package co.uk.nexhub.gimme.ui.navigation.tools

fun hideNavigation() {
    co.uk.nexhub.gimme.ui.elements.functioning.show(false)
}

fun showNavigation() {
    co.uk.nexhub.gimme.ui.elements.functioning.show(true)
}

fun isNavigationShown(): Boolean? {
    return co.uk.nexhub.gimme.ui.elements.functioning.isShowing()
}