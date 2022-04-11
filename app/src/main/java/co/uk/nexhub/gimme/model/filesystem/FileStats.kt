package co.uk.nexhub.gimme.model.filesystem

import androidx.lifecycle.MutableLiveData

data class FileStats(val name: String, // maybe also mutable, i think android users can change the name like on windows.
                     val path: String,
                     val freeSize: MutableLiveData<ByteSize>,
                     val totalSize: MutableLiveData<ByteSize>,
                     val usageApps: MutableLiveData<ByteSize>,
                     val usageVideos: MutableLiveData<ByteSize>,
                     val usagePhotos: MutableLiveData<ByteSize>,
                     val usageDownloads: MutableLiveData<ByteSize>? = null
) {

    fun getPercentage(ofType: FileStatType): Int {
        return when (ofType) {
            FileStatType.FREE_SIZE -> ((getSafely(totalSize) / getSafely(totalSize)) * 100).toInt()
            FileStatType.TOTAL_SIZE -> ((getSafely(totalSize) / getSafely(totalSize)) * 100).toInt()
            FileStatType.USAGE_APPS -> ((getSafely(totalSize) / getSafely(totalSize)) * 100).toInt()
            FileStatType.USAGE_VIDEOS -> ((getSafely(totalSize) / getSafely(totalSize)) * 100).toInt()
            FileStatType.USAGE_PHOTOS -> ((getSafely(totalSize) / getSafely(totalSize)) * 100).toInt()
            FileStatType.USAGE_DOWNLOADS -> ((getSafely(totalSize) / getSafely(totalSize)) * 100).toInt()
            else -> {-1}
        }
    }

    private fun getSafely(byteSize: MutableLiveData<ByteSize>): Double {
        return (byteSize.value?.bytes ?: 0.toDouble())
    }
}

enum class FileStatType(val label: String) {
    NAME("name"),
    PATH("path"),
    FREE_SIZE("free size"),
    TOTAL_SIZE("total size"),
    USAGE_APPS("app usage"),
    USAGE_VIDEOS("video usage"),
    USAGE_PHOTOS("photo usage"),
    USAGE_DOWNLOADS("download usage"),
}