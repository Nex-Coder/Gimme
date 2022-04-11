package co.uk.nexhub.gimme

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import co.uk.nexhub.gimme.ui.elements.functioning.BottomBar
import co.uk.nexhub.gimme.ui.screens.NavGraphs
import co.uk.nexhub.gimme.ui.theme.GimmeTheme
import com.google.accompanist.permissions.*
import com.ramcosta.composedestinations.DestinationsNavHost
import java.lang.ref.WeakReference
import java.util.jar.Manifest


fun getMainContext(): Context? { return context?.get()!! }
private var context: WeakReference<Context>? = null

//@OptIn(ExperimentalPermissionsApi::class)
//var permissionStates: MultiplePermissionsState? = null
@OptIn(ExperimentalPermissionsApi::class)
var permissionState: PermissionState? = null

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*permissionStates = rememberMultiplePermissionsState( // Only do this for unimportant ones
                permissions = listOf(
                    android.Manifest.permission.ACCESS_WIFI_STATE,
                    android.Manifest.permission.MANAGE_EXTERNAL_STORAGE
                )
            )*/

            permissionState = rememberPermissionState(android.Manifest.permission.WRITE_EXTERNAL_STORAGE )

            Default()
        }
    }
}


@Composable
fun Default() {
    context = WeakReference<Context>(LocalContext.current)

    GimmeTheme { // A surface container using the 'background' color from the theme
        Surface(
            color = Color.DarkGray, // or MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize(),
        ) {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = { BottomBar(navController); /*hideNavigation()*/ },
            ) {
                DestinationsNavHost(navGraph = NavGraphs.root, navController = navController)
            }
        }
    }
}

