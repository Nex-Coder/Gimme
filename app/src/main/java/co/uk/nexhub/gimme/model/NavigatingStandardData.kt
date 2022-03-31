package co.uk.nexhub.gimme.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NavigatingStandardData(
    var isNavigable: Boolean = true,
    var lastDestination: String = "none",
    var args: List<String> = listOf()
): Parcelable
