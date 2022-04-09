package co.uk.nexhub.gimme.ui.elements

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import com.ramcosta.composedestinations.spec.Direction

data class BottomNavItem(
    val name: String,
    val route: Direction,
    val badgeCount: Int = 0,
    val iconID: Int,
    val scale: ContentScale = ContentScale.Fit,
    val maxWidth: Float = 1f,
    val alpha: Float = 1f,
    val filter: ColorFilter? = null
)