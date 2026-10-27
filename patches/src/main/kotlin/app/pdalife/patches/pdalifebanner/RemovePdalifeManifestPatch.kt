package app.pdalife.patches.pdalifebanner

import app.morphe.patcher.patch.resourcePatch
import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility
import org.w3c.dom.Element
import kotlin.collections.firstOrNull

/**
 * Targeted patch: Removes android:name="com.pdalife.PdaApplication" from AndroidManifest.xml.
 * Default-enabled for the 4 confirmed games.
 */
@Suppress("unused")
val removePdalifeManifestPatch = resourcePatch(
    name = "Remove PDALIFE Application from manifest",
    description = "Removes android:name=\"com.pdalife.PdaApplication\" from AndroidManifest.xml so the PDALIFE injector never runs.",
    default = true
) {
    compatibleWith(
        Compatibility(
            name = "Slime Rancher",
            packageName = "com.playdigious.slimerancher",
            apkFileType = ApkFileType.APK,
            targets = listOf(AppTarget(version = "1.1.1103"))
        )
    )
    compatibleWith(
        Compatibility(
            name = "Subnautica",
            packageName = "com.unknownworlds.subnautica",
            apkFileType = ApkFileType.APK,
            targets = listOf(AppTarget(version = "1.22.83811"))
        )
    )
    compatibleWith(
        Compatibility(
            name = "World of Goo 2",
            packageName = "com.twodboy.wog2",
            apkFileType = ApkFileType.APK,
            targets = listOf(AppTarget(version = "1.0.29878322"))
        )
    )
    compatibleWith(
        Compatibility(
            name = "Don't Starve Pocket",
            packageName = "com.kleientertainment.doNotStarvePocket",
            apkFileType = ApkFileType.APK,
            targets = listOf(AppTarget(version = "1.19.22"))
        )
    )

    execute {
        document("AndroidManifest.xml").use { doc ->
            val appElem = doc.getElementsByTagName("application").item(0) as? Element
            if (appElem != null) {
                appElem.removeAttribute("android:name")
            }
        }
    }
}

/**
 * Universal patch: Removes android:name="com.pdalife.PdaApplication" from AndroidManifest.xml.
 * Works on any app — enable manually in Morphe Manager.
 */
@Suppress("unused")
val removePdalifeManifestPatchUniversal = resourcePatch(
    name = "Remove PDALIFE Application from manifest (universal)",
    description = "Universal patch: Removes android:name=\"com.pdalife.PdaApplication\" from AndroidManifest.xml so the PDALIFE injector never runs. Works on any APK.",
    default = false
) {
    execute {
        document("AndroidManifest.xml").use { doc ->
            val appElem = doc.getElementsByTagName("application").item(0) as? Element
            if (appElem != null) {
                appElem.removeAttribute("android:name")
            }
        }
    }
}
