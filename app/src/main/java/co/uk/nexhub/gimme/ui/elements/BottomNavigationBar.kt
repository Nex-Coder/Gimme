package co.uk.nexhub.gimme.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController

var currentDestination = MutableLiveData("")

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    show: Boolean = true,
    onItemClick: (BottomNavItem) -> Unit
) {
    if (show) {
        enableDestinationChangeListening(false, navController)
        BottomNavigation(
            modifier = modifier,
            backgroundColor = Color.DarkGray.copy(alpha = 0.97f),
            elevation = 0.dp
        ) {
            items.forEach { item ->
                val obvCurrentDestination: String? by currentDestination.observeAsState()
                val selected = item.route.route == obvCurrentDestination
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = Color.Red,
                    unselectedContentColor = Color.Gray,
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            val colorFilter: ColorFilter? = if (selected) {
                                ColorFilter.lighting(MaterialTheme.colors.background, MaterialTheme.colors.primary) } else {item.filter}

                            if (item.badgeCount > 0) {
                                BadgedBox(badge = { Badge { Text(text = item.badgeCount.toString()) } }) {
                                    Image(painter = painterResource(id = item.iconID), contentDescription = item.name, alignment = Alignment.Center, contentScale = item.scale, colorFilter = colorFilter, alpha = item.alpha, modifier = Modifier
                                        .fillMaxWidth(item.maxWidth)
                                        .aspectRatio(1f))
                                }
                            } else {
                                Image(painter = painterResource(id = item.iconID), contentDescription = item.name, alignment = Alignment.Center, contentScale = item.scale, colorFilter = colorFilter, alpha = item.alpha, modifier = Modifier
                                    .fillMaxWidth(item.maxWidth)
                                    .aspectRatio(1f))
                            }
                            if (selected) {
                                Text(
                                    text = item.name,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colors.primary.copy(alpha = 0.33f),
                                    fontWeight = FontWeight(1000),
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                )
            }
        }
    } else {
        enableDestinationChangeListening(false, navController)
    }
}

private fun enableDestinationChangeListening(isEnable: Boolean, navController: NavController) {
    if (isEnable) {
        navController.addOnDestinationChangedListener(mainDestinationChangedListener)
    } else {
        navController.removeOnDestinationChangedListener(mainDestinationChangedListener)
    }
}

private val mainDestinationChangedListener =
    NavController.OnDestinationChangedListener { _, destination, _ ->
        currentDestination.postValue(destination.route)
    }