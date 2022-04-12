package co.uk.nexhub.gimme.lib.filesystem

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import co.uk.nexhub.gimme.getMainContext
import co.uk.nexhub.gimme.model.filesystem.ByteSize
import co.uk.nexhub.gimme.ui.theme.extras
import co.uk.nexhub.gimme.ui.theme.fontCambay

object GFileSystem {
    val mutableFileStats: MutableList<MutableLiveData<ByteSize>>? = null

    fun getStub() {

    }

    private fun getAllFileStats() {

    }

    fun getRemovableStorage()  {

    }

    fun toGB(bytes: Long): Double { // Improve because its inaccurate and inflexible (1073741824)
        return (bytes / 1000000000.toDouble())
    }

    fun getVolumes(): MutableList<StorageVolume> {
        return ((getMainContext() as Activity).getSystemService(Context.STORAGE_SERVICE) as StorageManager).storageVolumes
    }
}