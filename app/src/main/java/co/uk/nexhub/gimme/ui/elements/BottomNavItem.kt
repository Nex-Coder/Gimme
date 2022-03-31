package co.uk.nexhub.gimme.ui.elements

import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.Direction

data class BottomNavItem(
    val name: String,
    val route: Direction,
    val icon: ImageVector,
    val badgeCount: Int = 0
)