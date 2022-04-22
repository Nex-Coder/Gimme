package co.uk.nexhub.gimme.lib.filesystem

import android.app.Activity
import android.content.Context
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import androidx.lifecycle.MutableLiveData
import co.uk.nexhub.gimme.getMainContext
import co.uk.nexhub.gimme.model.filesystem.ByteSize

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