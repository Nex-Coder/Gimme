package co.uk.nexhub.gimme.lib.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import co.uk.nexhub.gimme.getMainContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


object PermissionRequester {
    @RequiresApi(Build.VERSION_CODES.R)
    val MANAGE_EXTERNAL_STORAGE_PERMS = arrayOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
    const val MANAGE_EXTERNAL_STORAGE_CODE = 0

    val VIBRATE_PERMS = arrayOf(Manifest.permission.VIBRATE)
    const val VIBRATE_CODE = 1

    val MANAGE_CONNECTIVITY_PERMS = arrayOf(Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.INTERNET,
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN)
    const val MANAGE_CONNECTIVITY_CODE = 2


    @OptIn(ExperimentalPermissionsApi::class)
    @RequiresApi(Build.VERSION_CODES.R)
    @Composable
    fun manageExternalStorage(): Boolean {
        val permissionState = rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE
            )
        )

        //request(MANAGE_EXTERNAL_STORAGE_PERMS, MANAGE_EXTERNAL_STORAGE_CODE)
        return true;
    }

    private fun showInContextUI(any: Any?) {

    }

    fun vibrate() {
        request(VIBRATE_PERMS, VIBRATE_CODE)
    }

    fun manageConnectivity() {
        request(MANAGE_CONNECTIVITY_PERMS, MANAGE_CONNECTIVITY_CODE)
    }

    fun verifyPermissions(grantResults: Array<String>): Boolean {
        // At least one result must be checked.
        if (grantResults.size < 1) {
            return false
        }

        // Verify that each required permission has been granted, otherwise return false.
        val context = getMainContext() as Activity
        for (result in grantResults) {
            if (ContextCompat.checkSelfPermission(context, result) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun request(permissions: String, requestCode: Int) {
        request(arrayOf(permissions), requestCode)
    }

    private fun request(permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(getMainContext() as Activity, permissions, requestCode)
    }
}

fun Context.findActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}