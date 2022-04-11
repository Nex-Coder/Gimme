package co.uk.nexhub.gimme.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import co.uk.nexhub.gimme.R
import co.uk.nexhub.gimme.getMainContext
import co.uk.nexhub.gimme.permissionState
import co.uk.nexhub.gimme.ui.elements.AppHeader
import co.uk.nexhub.gimme.ui.elements.DefaultScreenWrapper
import co.uk.nexhub.gimme.ui.elements.HeaderDivider
import co.uk.nexhub.gimme.ui.theme.extras
import co.uk.nexhub.gimme.ui.theme.fontCambay
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale
import com.ramcosta.composedestinations.annotation.Destination
import java.math.RoundingMode
import java.util.*


var statFs = StatFs(Environment.getExternalStorageDirectory().absolutePath)
var blockSize = statFs.blockSizeLong
var totalSizeGB: Double = ((statFs.blockCountLong * blockSize) / (1073741824).toDouble()).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
var availableSizeGB = ((statFs.availableBlocksLong * blockSize) / (1073741824).toDouble()).toBigDecimal().setScale(2, RoundingMode.FLOOR).toDouble()
//var freeSize = statFs.freeBlocksLong * blockSize

//var externals = getExternalSdCardSize()
//var eSize = externals.size

var isSDPresent = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
var isSDSupportedDevice = (Environment.isExternalStorageRemovable() || Environment.isExternalStorageEmulated())
var isSD = isSDPresent && isSDSupportedDevice

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalPermissionsApi::class)
@Destination
@Composable
fun StorageScreen(arg: String?) {
    val scrollState = rememberScrollState()

    DefaultScreenWrapper(scrollState) {
        AppHeader()



        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(
            key1 = lifecycleOwner,
            effect = {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_START) {
                        permissionState?.launchPermissionRequest()
                    }
                }

                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
            }
        )
        if (Environment.isExternalStorageManager()) { // YO! THIS SHIT SUCCESSFULLY GETS MANAGE_EXTERNAL_STORAGE
            Log.d("XDD","PERMISSION YAY")
        } else {
            //request for the permission
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            val uri = Uri.fromParts("package", getMainContext()?.packageName, null)
            intent.data = uri
            startActivity(getMainContext()!!, intent, null)
        }
        when {
            permissionState?.status?.isGranted == true -> { // Permission Granted
                Log.d("Permission Info","Granted detected")
                ComposeWithPermission()
            }
            permissionState?.status?.shouldShowRationale == true -> { // Denied but not forever
                ComposeToAskForPermission()
            }
            permissionState?.status?.isGranted != true && permissionState?.status?.shouldShowRationale != true  -> { // Permission Permanently Denied.
                Log.d("Permission Info","Permanently Denied... ${permissionState?.status?.isGranted} | ${permissionState?.status?.shouldShowRationale}")
                ComposeWithoutPermission()
            }
        }

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (PermissionRequester.verifyPermissions(PermissionRequester.MANAGE_EXTERNAL_STORAGE_PERMS)) { /*TODO PERMS*/
                val volumes = checkSdCardPermission(LocalContext.current)
                SDCardComposeAll(volumes)
            } else {
                PermissionRequester.manageExternalStorage()
                Text("Manage External Permissions Not allowed")
            }
        }*/
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun ComposeWithPermission() {
    HeaderDivider("Internal Storage", R.drawable.phone_android)
    // this requires different permissions i think

    HeaderDivider("SD Card", R.drawable.micro_sd_card)
    SDCardComposeAll(getVolumes(getMainContext() as Activity))
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
    if (volume.isPrimary){} else {
        val card = volume.directory!!
        val name = card.name
        val path = card.absolutePath
        val freeSpace = card.freeSpace
        val totalSpace = card.totalSpace

        Spacer(Modifier.height(20.dp))
        Text(
            "Name: $name \n" +
                    "path: $path \n" +
                    "Free Space: ${String.format("%.1f", toGB(freeSpace))}GB \n" +
                    "Total Space: ${String.format("%.1f", toGB(totalSpace))}GB",
            fontFamily = fontCambay,
            color = MaterialTheme.extras().solidColor.copy(alpha = 0.33f),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center
        )
    }
}


fun toGB(bytes: Long): Double { // Improve because its inaccurate and inflexible (1073741824)
    return (bytes / 1000000000.toDouble())
}

@RequiresApi(Build.VERSION_CODES.R)
private fun checkSdCardPermission(context: Context): MutableList<StorageVolume>? {


    requestPerms(context)

    if (ContextCompat.checkSelfPermission(context, Manifest.permission.MANAGE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        return getVolumes(context)
    } else {
        return null
    }
}

private fun getVolumes(context: Context): MutableList<StorageVolume> {
    return (context.getSystemService(Context.STORAGE_SERVICE) as StorageManager).storageVolumes
}

@RequiresApi(Build.VERSION_CODES.R)
fun requestPerms(activity: Context) { //TODO in observation it nevers asks user for permissiom, it just denies. confirm this so useres dont have to manuall enable permission in settings.

    ActivityCompat.requestPermissions(
        activity as Activity,
        arrayOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE),
        1
    )
}

