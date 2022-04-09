package co.uk.nexhub.gimme.ui.screens

import android.os.Build
import android.view.animation.OvershootInterpolator
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import co.uk.nexhub.gimme.R
import co.uk.nexhub.gimme.ui.navigation.tools.navigateToDestination
import co.uk.nexhub.gimme.ui.screens.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.R)
@Destination(start = true)
@Composable
fun SplashScreen(navigator: DestinationsNavigator) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 0.75f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                })
        )
        delay(2000L) // Should be 700L
        navigator.navigateToDestination(HomeScreenDestination())
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Gimme | Made By...", fontSize = 10.sp, color = MaterialTheme.colors.onBackground.copy(alpha = scale.value))
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "App Icon",
            modifier = Modifier.scale(scale.value)
        )
    }
}