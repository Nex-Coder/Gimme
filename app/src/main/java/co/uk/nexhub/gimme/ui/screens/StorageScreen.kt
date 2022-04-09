package co.uk.nexhub.gimme.ui.screens

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import co.uk.nexhub.gimme.MainActivity
import co.uk.nexhub.gimme.R
import co.uk.nexhub.gimme.ui.elements.AppHeader
import co.uk.nexhub.gimme.ui.elements.DefaultScreenWrapper
import co.uk.nexhub.gimme.ui.elements.HeaderDivider
import co.uk.nexhub.gimme.ui.theme.extras
import co.uk.nexhub.gimme.ui.theme.fontCambay
import com.ramcosta.composedestinations.annotation.Destination
import java.math.BigDecimal
import java.math.MathContext
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

@Destination
@Composable
fun StorageScreen(arg: String?) {
    val scrollState = rememberScrollState()

    DefaultScreenWrapper(scrollState) {
        AppHeader()
        HeaderDivider("Internal Storage", R.drawable.phone_android)

        HeaderDivider("SD Card", R.drawable.micro_sd_card)
        var temp ="asd"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            var volumes = checkSdCardPermission(LocalContext.current)

            if(volumes?.size ?: 0 >= 2) {
                for (volume in volumes!!) {
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
            } else {
                Spacer(Modifier.height(20.dp))
                Text(
                    "No SD Card Detected...",
                    fontFamily = fontCambay,
                    color = MaterialTheme.extras().solidColor.copy(alpha = 0.33f),
                    fontStyle = FontStyle.Italic
                )
            }
        } else {
            TODO("VERSION.SDK_INT < R")
        }
    }
}

fun toGB(bytes: Long): Double { // Improve because its inaccurate and inflexible (1073741824)
    return (bytes / 1000000000.toDouble())
}

@RequiresApi(Build.VERSION_CODES.R)
private fun checkSdCardPermission(context: Context): MutableList<StorageVolume>? {
    val activity = context.findActivity()

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

fun Context.findActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}