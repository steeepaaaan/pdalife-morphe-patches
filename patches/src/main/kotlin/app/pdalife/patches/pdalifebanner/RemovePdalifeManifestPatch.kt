package app.pdalife.patches.pdalifebanner

import app.morphe.patcher.patch.resourcePatch
import app.pdalife.patches.pdalifebanner.PdalifeCompatibility.ALL
import org.w3c.dom.Element

/**
 * Removes android:name="com.pdalife.PdaApplication" from AndroidManifest.xml.
 *
 * com.pdalife.PdaApplication is the injector trigger — its onCreate() calls
 * System.loadLibrary("PDALIFE"), which installs A64HookFunction hooks and shows
 * the banner/toast. Without the custom Application class, Android uses the default
 * Application class, the library is never loaded, and no banner/toast appears.
 */
@Suppress("unused")
val removePdalifeManifestPatch = resourcePatch(
    name = "Remove PDALIFE Application from manifest",
    description = "Removes android:name=\"com.pdalife.PdaApplication\" from AndroidManifest.xml so the PDALIFE injector never runs.",
    default = true
) {
    ALL.forEach { compatibleWith(it) }

    execute {
        // Parse AndroidManifest.xml as a DOM document.
        val doc = document("AndroidManifest.xml")

        // Find the <application> element and remove android:name.
        val appElem = doc.getElementsByTagName("application").item(0) as? Element
        if (appElem != null) {
            appElem.removeAttribute("android:name")
        }
    }
}
