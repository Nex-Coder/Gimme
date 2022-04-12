package co.uk.nexhub.gimme.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Environment
import android.os.storage.StorageVolume
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import co.uk.nexhub.gimme.R
import co.uk.nexhub.gimme.lib.filesystem.GFileSystem
import co.uk.nexhub.gimme.lib.filesystem.GFileSystem.getVolumes
import co.uk.nexhub.gimme.permissionState
import co.uk.nexhub.gimme.ui.elements.AppHeader
import co.uk.nexhub.gimme.ui.elements.DefaultScreenWrapper
import co.uk.nexhub.gimme.ui.elements.HeaderDivider
import co.uk.nexhub.gimme.ui.theme.extras
import co.uk.nexhub.gimme.ui.theme.fontCambay
import co.uk.nexhub.gimme.ui.utility.PermissionUtility
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ramcosta.composedestinations.annotation.Destination

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalPermissionsApi::class)
@Destination
@Composable
fun StorageScreen() {
    val scrollState = rememberScrollState()

    DefaultScreenWrapper(scrollState) {
        AppHeader()

        PermissionUtility.SettingAndRuntimeSwitch(
            permission = Manifest.permission.WRITE_EXTERNAL_STORAGE,
            setting = Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
            settingsCheck = { Environment.isExternalStorageManager() },
            composeWhenGranted = { ComposeWithPermission() },
            composeWhenRationale = { ComposeToAskForPermission() },
            composeWhenDenied = { ComposeWithoutPermission() })
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun ComposeWithPermission() {
    val volumes: MutableList<StorageVolume> = getVolumes()

    HeaderDivider("Internal Storage", R.drawable.phone_android)
    // this requires different permissions i think

    HeaderDivider("SD Card", R.drawable.micro_sd_card)
    SDCardComposeAll(volumes)
}

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ComposeToAskForPermission() {
    Spacer(Modifier.height(20.dp))
    Text(
        "External Storage Permissions Required. Do you wish to give permission?",
        fontFamily = fontCambay,
        color = MaterialTheme.extras().solidColor.copy(alpha = 0.33f),
        fontStyle = FontStyle.Italic
    )
    Button(onClick = { permissionState?.launchPermissionRequest() }) {
        Text("Request permissions")
    }
}

@Composable
fun ComposeWithoutPermission() {
    Spacer(Modifier.height(20.dp))
    Text(
        "You Denied Permissions, Go To app settings if you change your mind.",
        fontFamily = fontCambay,
        color = MaterialTheme.extras().solidColor.copy(alpha = 0.33f),
        fontStyle = FontStyle.Italic
    )
}

////////////////////////////////////////////////////////////////////////////////////////////////////

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun SDCardComposeAll(volumes: MutableList<StorageVolume>?) {
    if(volumes != null && volumes.size >= 2) { // 2 for more than just internal storage
        for (volume in volumes) {
            SDCardComposed(volume)
        }
    } else {
        Spacer(Modifier.height(20.dp))
        Text(
            "No SD Card Detected...",
            fontFamily = fontCambay,
            color = MaterialTheme.extras().solidColor.copy(alpha = 0.33f),
            fontStyle = FontStyle.Italic
        )
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun SDCardComposed(volume: StorageVolume) {
    if (!volume.isPrimary) {
        val card = volume.directory!!
        val name = card.name
        val path = card.absolutePath
        val freeSpace = card.freeSpace
        val totalSpace = card.totalSpace

        Spacer(Modifier.height(20.dp))
        Text(
            "Name: $name \n" +
                    "path: $path \n" +
                    "Free Space: ${String.format("%.1f", GFileSystem.toGB(freeSpace))}GB \n" +
                    "Total Space: ${String.format("%.1f", GFileSystem.toGB(totalSpace))}GB",
            fontFamily = fontCambay,
            color = MaterialTheme.extras().solidColor.copy(alpha = 0.33f),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center
        )
    }
}
