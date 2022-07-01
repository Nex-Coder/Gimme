package co.uk.nexhub.gimme.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Environment
import android.os.storage.StorageVolume
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.uk.nexhub.gimme.R
import co.uk.nexhub.gimme.lib.filesystem.GFileSystem.getVolumes
import co.uk.nexhub.gimme.model.filesystem.ByteSize
import co.uk.nexhub.gimme.permissionState
import co.uk.nexhub.gimme.ui.elements.items.BarItem
import co.uk.nexhub.gimme.ui.elements.items.TextCircleProgressIndicator
import co.uk.nexhub.gimme.ui.elements.layout.AppHeader
import co.uk.nexhub.gimme.ui.elements.layout.HeaderDivider
import co.uk.nexhub.gimme.ui.elements.layout.HorizontalDivider
import co.uk.nexhub.gimme.ui.elements.layout.VerticalDivider
import co.uk.nexhub.gimme.ui.elements.parents.DefaultScreenWrapper
import co.uk.nexhub.gimme.ui.theme.extras
import co.uk.nexhub.gimme.ui.theme.fontCambay
import co.uk.nexhub.gimme.ui.utility.PermissionUtility
import co.uk.nexhub.gimme.ui.utility.coloredShadow
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

//region Starting Composers
//---------------------------------------------------------------------------------------

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun ComposeWithPermission() {
    val volumes: MutableList<StorageVolume> = getVolumes()

    HeaderDivider("Internal Storage", R.drawable.phone_android)
    // this can require a lower & different permissions i think
    InternalStorageCompose(volumes)

    Spacer(Modifier.height(20.dp))

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

//endregion

//region Composable Helpers
//---------------------------------------------------------------------------------------


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun InternalStorageCompose(volumes: MutableList<StorageVolume>?) {
    if(volumes != null && volumes.size >= 1) {
        for (volume in volumes) {
            SDCardComposed(volume, true)
            Spacer(Modifier.height(20.dp))
            BiggestFilesComposed(volume, true)
        }
    } else {
        Spacer(Modifier.height(20.dp))
        Text(
            "No Storage detected somehow! Possibly an error.",
            fontFamily = fontCambay,
            color = MaterialTheme.extras().solidColor.copy(alpha = 0.33f),
            fontStyle = FontStyle.Italic
        )
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun SDCardComposeAll(volumes: MutableList<StorageVolume>?) {
    if(volumes != null && volumes.size >= 2) { // 2 for more than just internal storage
        for (volume in volumes) {
            SDCardComposed(volume, false)
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
fun SDCardComposed(volume: StorageVolume, primaryOnly: Boolean) {
    if (if (primaryOnly) volume.isPrimary else !volume.isPrimary ) {
        val card = volume.directory!!
        val name = card.name
        val path = card.absolutePath
        val freeSpace = card.freeSpace
        val totalSpace = card.totalSpace
        //var usedAsPercentage = ((totalSpace - freeSpace.toDouble()) / totalSpace).toFloat()

        Spacer(Modifier.height(20.dp))

        val rowSpacing = 8.dp

        Text(
            "Name: $name ($path)",
            Modifier.fillMaxWidth(),
            MaterialTheme.extras().solidColor.copy(alpha = 0.33f),
            fontFamily = fontCambay,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Left
        )

        Spacer(Modifier.height(4.dp))

        Box(Modifier.coloredShadow(MaterialTheme.extras().solidColor, offsetY = 6.dp, alpha = 0.25f, borderRadius = 10.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(185.dp)
                    .background(
                        Brush.linearGradient(
                            0f to MaterialTheme.extras().primaryHardLighting,
                            0.2f to MaterialTheme.extras().primaryMedianLighting,
                            0.4f to MaterialTheme.extras().primarySoftLighting,
                            1f to MaterialTheme.extras().primarySoftLighting,
                            start = Offset(0f, Float.POSITIVE_INFINITY),
                            end = Offset(Float.POSITIVE_INFINITY, 0f)
                        ),
                        RoundedCornerShape(8)
                    )
                    .border(1.dp, MaterialTheme.colors.primaryVariant, RoundedCornerShape(8)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(rowSpacing))

                Column(Modifier.height(160.dp).width(130.dp), SpaceEvenly, Alignment.CenterHorizontally) {
                    TextCircleProgressIndicator(
                        0.66f,
                        Modifier.size(125.dp),
                        fontSize = 52.sp,
                        textColor = MaterialTheme.colors.background.copy(0.75f),
                        fontFamily = fontCambay,
                        brush = Brush.radialGradient(listOf(MaterialTheme.colors.primaryVariant, MaterialTheme.colors.primary)),
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        "Total: ${ByteSize(totalSpace)} | Free: ${ByteSize(freeSpace)}",
                        fontSize = 9.sp,
                        fontFamily = fontCambay,
                        color = MaterialTheme.extras().solidColor.copy(alpha = 0.33f),
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(Modifier.width(rowSpacing))
                HorizontalDivider(fillMaxHeight = 0.87f)
                Spacer(Modifier.width(rowSpacing))

                val itemModifier = Modifier
                    .clip(CircleShape)
                    .fillMaxWidth()
                    .height(25.dp)
                    .background(Brush.linearGradient(0f to MaterialTheme.colors.primaryVariant, 0.8f to MaterialTheme.colors.primary.copy(0.8f)), CircleShape)
                    .border(Dp.Hairline, MaterialTheme.colors.primaryVariant)
                Column(
                    Modifier.width(216.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(Modifier.height(11.dp))
                    ComposeItem(
                        painterResource(R.drawable.android),
                        "App",
                        "500Mb",
                        1f,
                        itemModifier,
                    )
                    ComposeItem(
                        painterResource(R.drawable.video_icon_96_xxxhdpi),
                        "Video",
                        "500Mb",
                        1f,
                        itemModifier,
                    )
                    ComposeItem(
                        painterResource(R.drawable.camera),
                        "Photo",
                        "500Mb",
                        1f,
                        itemModifier,
                    )
                    ComposeItem(
                        painterResource(R.drawable.file_96_hdpi),
                        "Other",
                        "500Mb",
                        1f,
                        itemModifier,
                    )

                    VerticalDivider()
                    Spacer(Modifier.height(5.dp))

                    ComposeItem(
                        painterResource(R.drawable.file_96_hdpi),
                        "Downloads",
                        "500Mb",
                        1f,
                        itemModifier,
                    )
                    Spacer(Modifier.height(5.dp))
                }
            }
        }
    }
}

@Composable
@SuppressLint("ModifierParameter")
private fun ComposeItem(icon: Painter, category: String, bytesSize: String, percentage: Float, modifier: Modifier = Modifier, fontSize: TextUnit = 15.sp, iconModifier: Modifier = Modifier.height(24.dp)) {
    BarItem(
        icon,
        category,
        bytesSize,
        percentage,
        modifier,
        iconModifier = iconModifier,
        color = MaterialTheme.extras().topBackground,
        textAlign = TextAlign.Left,
        fontSize = fontSize,
        maxLines = 1)

    Spacer(Modifier.height(7.dp))
}

/**
 * Biggest files composed. This is a protostype and will be changed later.
 *
 * @param volume
 * @param primaryOnly
 */
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun BiggestFilesComposed(volume: StorageVolume, primaryOnly: Boolean) {
    if (if (primaryOnly) volume.isPrimary else !volume.isPrimary ) {
        val card = volume.directory!!

        Text(
            "Large Files",
            Modifier.fillMaxWidth(),
            MaterialTheme.extras().solidColor.copy(alpha = 0.33f),
            fontFamily = fontCambay,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Left
        )
        Box(
            Modifier.coloredShadow(
                MaterialTheme.extras().solidColor,
                offsetY = 6.dp,
                alpha = 0.25f,
                borderRadius = 10.dp
            )
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(185.dp)
                    .background(
                        Brush.linearGradient(
                            listOf(
                                MaterialTheme.extras().primaryHardLighting,
                                MaterialTheme.extras().primaryMedianLighting,
                                MaterialTheme.extras().primarySoftLighting,
                                MaterialTheme.extras().primarySoftLighting,
                                MaterialTheme.extras().primarySoftLighting
                            ),
                            start = Offset(0f, Float.POSITIVE_INFINITY),
                            end = Offset(Float.POSITIVE_INFINITY, 0f)
                        ),
                        RoundedCornerShape(8)
                    )
                    .border(1.dp, MaterialTheme.colors.primaryVariant, RoundedCornerShape(8)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LazyColumn(Modifier.fillMaxSize().clip(RoundedCornerShape(8))) {
                    // Here is the header
                    item {
                        Row(Modifier) {
                            TableCell(text = "Filename", weight = 0.4f)
                            TableCell(text = "File Path", weight = 0.42f)
                            TableCell(text = "Size", weight = 0.18f)
                        }
                    }

                    val tripleData: List<Triple<String, String, String>> = (1..10).mapIndexed { index, _ -> // Temporary, make model
                        Triple("File$index", "/some/dir/File$index", "500Mb")
                    }

                    // Here are all the lines of your table.
                    items(tripleData) {
                        val (name, path, size) = it
                        Row(Modifier.fillMaxWidth()) {
                            TableCell(text = name, weight = 0.4f)
                            TableCell(text = path, weight = 0.42f)
                            TableCell(text = size, weight = 0.18f)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}
//endregion