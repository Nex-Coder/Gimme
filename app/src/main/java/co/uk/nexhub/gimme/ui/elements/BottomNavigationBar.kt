package co.uk.nexhub.gimme.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    show: Boolean = true,
    onItemClick: (BottomNavItem) -> Unit
) {
    if (show) {
        BottomNavigation(
            modifier = modifier,
            backgroundColor = Color.DarkGray,
            elevation = 5.dp
        ) {
            items.forEach { item ->
                val selected = item.route == navController.currentDestination
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = Color.Red,
                    unselectedContentColor = Color.Gray,
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (item.badgeCount > 0) {
                                BadgedBox(badge = { Badge { Text(text = item.badgeCount.toString()) } }) {
                                    Icon(imageVector = item.icon, contentDescription = item.name)
                                }
                            } else {
                                Icon(imageVector = item.icon, contentDescription = item.name)
                            }
                            if (selected) {
                                Text(
                                    text = item.name,
                                    textAlign = TextAlign.Center,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}