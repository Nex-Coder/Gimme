package co.uk.nexhub.gimme.lib

import android.annotation.TargetApi
import android.content.Context
import android.net.Uri
import android.os.Build
import android.preference.PreferenceManager
import android.util.Log
import androidx.documentfile.provider.DocumentFile
import java.io.File
import java.io.IOException

object ExternalSdCardOperation {
    val LOG = "ExternalSdCardOperation"

    /**
     * Get a DocumentFile corresponding to the given file (for writing on ExtSdCard on Android 5). If
     * the file is not existing, it is created.
     *
     * @param file The file.
     * @param isDirectory flag indicating if the file should be a directory.
     * @return The DocumentFile
     */
    @JvmStatic
    fun getDocumentFile(
        file: File,
        isDirectory: Boolean,
        context: Context
    ): DocumentFile? {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) return DocumentFile.fromFile(file)
        val baseFolder = getExtSdCardFolder(file, context)
        var originalDirectory = false
        if (baseFolder == null) {
            return null
        }
        var relativePath: String? = null
        try {
            val fullPath = file.canonicalPath
            if (baseFolder != fullPath) {
                relativePath = fullPath.substring(baseFolder.length + 1)
            } else {
                originalDirectory = true
            }
        } catch (e: IOException) {
            return null
        }

        val preferenceUri = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(PreferencesConstants.PREFERENCE_URI, null)
        var treeUri: Uri? = null
        if (preferenceUri != null) {
            treeUri = Uri.parse(preferenceUri)
        }
        if (treeUri == null) {
            return null
        }

        // start with root of SD card and then parse through document tree.
        var document = DocumentFile.fromTreeUri(context, treeUri)
        if (originalDirectory || relativePath == null) {
            return document
        }

        val parts = relativePath.split("/").toTypedArray()
        for (i in parts.indices) {
            if (document == null) {
                return null
            }

            var nextDocument = document.findFile(parts[i])
            if (nextDocument == null) {
                nextDocument = if (i < parts.size - 1 || isDirectory) {
                    document.createDirectory(parts[i])
                } else {
                    document.createFile("image", parts[i])
                }
            }
            document = nextDocument
        }

        return document
    }

    /**
     * Get a list of external SD card paths. (Kitkat or higher.)
     *
     * @return A list of external SD card paths.
     */
    @JvmStatic
    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun getExtSdCardPaths(context: Context): Array<String> {
        val paths: MutableList<String> = ArrayList()
        for (file in context.getExternalFilesDirs("external")) {
            if (file != null && file != context.getExternalFilesDir("external")) {
                val index = file.absolutePath.lastIndexOf("/Android/data")
                if (index < 0) {
                    Log.w(LOG, "Unexpected external file dir: " + file.absolutePath)
                } else {
                    var path = file.absolutePath.substring(0, index)
                    try {
                        path = File(path).canonicalPath
                    } catch (e: IOException) {
                        // Keep non-canonical path.
                    }
                    paths.add(path)
                }
            }
        }
        if (paths.isEmpty()) paths.add("/storage/sdcard1")
        return paths.toTypedArray()
    }

    @JvmStatic
    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun getExtSdCardPathsForActivity(context: Context): Array<String> {
        val paths: MutableList<String> = ArrayList()
        for (file in context.getExternalFilesDirs("external")) {
            if (file != null) {
                val index = file.absolutePath.lastIndexOf("/Android/data")
                if (index < 0) {
                    Log.w(LOG, "Unexpected external file dir: " + file.absolutePath)
                } else {
                    var path = file.absolutePath.substring(0, index)
                    try {
                        path = File(path).canonicalPath
                    } catch (e: IOException) {
                        // Keep non-canonical path.
                    }
                    paths.add(path)
                }
            }
        }
        if (paths.isEmpty()) paths.add("/storage/sdcard1")
        return paths.toTypedArray()
    }

    /**
     * Determine the main folder of the external SD card containing the given file.
     *
     * @param file the file.
     * @return The main folder of the external SD card containing this file, if the file is on an SD
     * card. Otherwise, null is returned.
     */
    @JvmStatic
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public fun getExtSdCardFolder(file: File, context: Context): String? {
        val extSdPaths = getExtSdCardPaths(context)
        try {
            for (i in extSdPaths.indices) {
                if (file.canonicalPath.startsWith(extSdPaths[i])) {
                    return extSdPaths[i]
                }
            }
        } catch (e: IOException) {
            return null
        }
        return null
    }

    /**
     * Determine if a file is on external sd card. (Kitkat or higher.)
     *
     * @param file The file.
     * @return true if on external sd card.
     */
    @JvmStatic
    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun isOnExtSdCard(file: File, c: Context): Boolean {
        return getExtSdCardFolder(file, c) != null
    }
}

object PreferencesConstants {
    // START fragments
    const val FRAGMENT_THEME = "theme"
    const val FRAGMENT_COLORS = "colors"
    const val FRAGMENT_FOLDERS = "sidebar_folders"
    const val FRAGMENT_QUICKACCESSES = "sidebar_quickaccess"
    const val FRAGMENT_ADVANCED_SEARCH = "advancedsearch"
    const val FRAGMENT_ABOUT = "about"
    const val FRAGMENT_FEEDBACK = "feedback"

    // END fragments
    // START preferences.xml constants
    const val PREFERENCE_INTELLI_HIDE_TOOLBAR = "intelliHideToolbar"
    const val PREFERENCE_SHOW_FILE_SIZE = "showFileSize"
    const val PREFERENCE_SHOW_PERMISSIONS = "showPermissions"
    const val PREFERENCE_SHOW_DIVIDERS = "showDividers"
    const val PREFERENCE_SHOW_HEADERS = "showHeaders"
    const val PREFERENCE_SHOW_GOBACK_BUTTON = "goBack_checkbox"
    const val PREFERENCE_SHOW_SIDEBAR_FOLDERS = "sidebar_folders_enable"
    const val PREFERENCE_SHOW_SIDEBAR_QUICKACCESSES = "sidebar_quickaccess_enable"
    const val PREFERENCE_ENABLE_MARQUEE_FILENAME = "enableMarqueeFilename"
    const val PREFERENCE_ROOT_LEGACY_LISTING = "legacyListing"
    const val PREFERENCE_DRAG_AND_DROP_PREFERENCE = "dragAndDropPreference"
    const val PREFERENCE_DRAG_AND_DROP_REMEMBERED = "dragOperationRemembered"
    const val PREFERENCE_CLEAR_OPEN_FILE = "clear_open_file"
    const val PREFERENCE_BOOKMARKS_ADDED = "books_added"
    const val PREFERENCE_TEXTEDITOR_NEWSTACK = "texteditor_newstack"
    const val PREFERENCE_SHOW_HIDDENFILES = "showHidden"
    const val PREFERENCE_SHOW_LAST_MODIFIED = "showLastModified"
    const val PREFERENCE_USE_CIRCULAR_IMAGES = "circularimages"
    const val PREFERENCE_ROOTMODE = "rootmode"
    const val PREFERENCE_CHANGEPATHS = "typeablepaths"
    const val PREFERENCE_GRID_COLUMNS = "columns"
    const val PREFERENCE_SHOW_THUMB = "showThumbs"
    const val PREFERENCE_CRYPT_MASTER_PASSWORD = "crypt_password"
    const val PREFERENCE_CRYPT_FINGERPRINT = "crypt_fingerprint"
    const val PREFERENCE_CRYPT_WARNING_REMEMBER = "crypt_remember"
    const val ENCRYPT_PASSWORD_FINGERPRINT = "fingerprint"
    const val ENCRYPT_PASSWORD_MASTER = "master"
    const val PREFERENCE_CRYPT_MASTER_PASSWORD_DEFAULT = ""
    const val PREFERENCE_CRYPT_FINGERPRINT_DEFAULT = false
    const val PREFERENCE_CRYPT_WARNING_REMEMBER_DEFAULT = false
    const val PREFERENCE_ZIP_EXTRACT_PATH = "extractpath"

    // END preferences.xml constants
    // START color_prefs.xml constants
    const val PREFERENCE_SKIN = "skin"
    const val PREFERENCE_SKIN_TWO = "skin_two"
    const val PREFERENCE_ACCENT = "accent_skin"
    const val PREFERENCE_ICON_SKIN = "icon_skin"
    const val PREFERENCE_CURRENT_TAB = "current_tab"
    const val PREFERENCE_COLORIZE_ICONS = "coloriseIcons"
    const val PREFERENCE_COLORED_NAVIGATION = "colorednavigation"
    const val PREFERENCE_RANDOM_COLOR = "random_checkbox"

    // END color_prefs.xml constants
    // START folders_prefs.xml constants
    const val PREFERENCE_SHORTCUT = "add_shortcut"

    // END folders_prefs.xml constants
    // START random preferences
    const val PREFERENCE_DIRECTORY_SORT_MODE = "dirontop"
    const val PREFERENCE_DRAWER_HEADER_PATH = "drawer_header_path"
    const val PREFERENCE_URI = "URI"
    const val PREFERENCE_HIDEMODE = "hidemode"
    const val PREFERENCE_VIEW = "view"
    const val PREFERENCE_NEED_TO_SET_HOME = "needtosethome"

    /** The value is an int with values RANDOM_INDEX, CUSTOM_INDEX, NO_DATA or [0, ...]  */
    const val PREFERENCE_COLOR_CONFIG = "color config"

    // END random preferences
    // START sort preferences
    const val PREFERENCE_SORTBY_ONLY_THIS = "sortby_only_this"
    const val PREFERENCE_APPLIST_SORTBY = "AppsListFragment.sortBy"
    const val PREFERENCE_APPLIST_ISASCENDING = "AppsListFragment.isAscending"

    // END sort preferences
    // drag and drop preferences
    const val PREFERENCE_DRAG_DEFAULT = 0
    const val PREFERENCE_DRAG_TO_SELECT = 1
    const val PREFERENCE_DRAG_TO_MOVE_COPY = 2
    const val PREFERENCE_DRAG_REMEMBER_COPY = "copy"
    const val PREFERENCE_DRAG_REMEMBER_MOVE = "move"
}

