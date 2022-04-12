package co.uk.nexhub.gimme.ui.utility

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import co.uk.nexhub.gimme.getMainContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

object PermissionUtility {
    @OptIn(ExperimentalPermissionsApi::class)
    @RequiresApi(Build.VERSION_CODES.R)
    @Composable
    fun RuntimeSwitch(permission: String,
                      composeWhenGranted: @Composable () -> Unit,
                      composeWhenRationale: @Composable () -> Unit,
                      composeWhenDenied: @Composable () -> Unit
    ) {
        val permissionState = rememberPermissionState(permission)

        /*--- Setup Permission Request To User ---*/
        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(
            key1 = lifecycleOwner,
            effect = {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_START) {
                        permissionState.launchPermissionRequest()
                    }
                }

                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
            }
        )

        /*--- Check Permission ---*/
        when {
            permissionState.status.isGranted -> {
                Log.d("Permission Utility", "Permission Is Granted")
                composeWhenGranted.invoke()
            }
            permissionState.status.shouldShowRationale -> { // Denied but not forever
                Log.d("Permission Utility", "Permission not granted, Show Rational")
                composeWhenRationale.invoke()
            }
            !permissionState.status.isGranted && !permissionState.status.shouldShowRationale -> { // Permission Permanently Denied.
                Log.d(
                    "Permission Utility", "Permanently Permanently Denied"
                )
                composeWhenDenied.invoke()
            }
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @RequiresApi(Build.VERSION_CODES.R)
    @Composable
    fun SettingAndRuntimeSwitch(permission: String,
                                setting: String,
                                settingsCheck: () -> Boolean, // { true }
                                composeWhenGranted: @Composable () -> Unit,
                                composeWhenRationale: @Composable () -> Unit,
                                composeWhenDenied: @Composable () -> Unit
    ) {
        val permissionState = rememberPermissionState(permission)

        /*--- Setup Permission Request To User ---*/
        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(
            key1 = lifecycleOwner,
            effect = {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_START) {
                        permissionState.launchPermissionRequest()
                    }
                }

                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
            }
        )

        /*--- Request Setting To User ---*/
        if (settingsCheck.invoke()) {
            Log.d("Permission Utility", "Setting Check was passed successfully")
        } else {
            //request for the permission
            val intent = Intent(setting) // Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
            val uri = Uri.fromParts("package", getMainContext()?.packageName, null)
            intent.data = uri
            startActivity(getMainContext()!!, intent, null)
        }

        /*--- Check Permission ---*/
        when {
            permissionState.status.isGranted -> {
                Log.d("Permission Utility", "Permission Is Granted")
                composeWhenGranted.invoke()
            }
            permissionState.status.shouldShowRationale -> { // Denied but not forever
                Log.d("Permission Utility", "Permission not granted, Show Rational")
                composeWhenRationale.invoke()
            }
            !permissionState.status.isGranted && !permissionState.status.shouldShowRationale -> { // Permission Permanently Denied.
                Log.d(
                    "Permission Utility", "Permanently Permanently Denied"
                )
                composeWhenDenied.invoke()
            }
        }
    }
}