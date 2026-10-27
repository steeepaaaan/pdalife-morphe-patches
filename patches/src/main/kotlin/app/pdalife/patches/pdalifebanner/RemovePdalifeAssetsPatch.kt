package app.pdalife.patches.pdalifebanner

import app.morphe.patcher.patch.rawResourcePatch
import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

/**
 * Targeted patch: Deletes the PDALIFE injector's encrypted config assets.
 * Default-enabled for the 4 confirmed games.
 */
@Suppress("unused")
val removePdalifeAssetsPatch = rawResourcePatch(
    name = "Remove PDALIFE config assets",
    description = "Deletes assets/PDALIFE.conf and assets/PDALIFE.bin from the APK.",
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
        delete("assets", "PDALIFE.conf")
        delete("assets", "PDALIFE.bin")
    }
}

/**
 * Universal patch: Deletes the PDALIFE injector's encrypted config assets.
 * Works on any app — enable manually in Morphe Manager.
 */
@Suppress("unused")
val removePdalifeAssetsPatchUniversal = rawResourcePatch(
    name = "Remove PDALIFE config assets (universal)",
    description = "Universal patch: Deletes assets/PDALIFE.conf and assets/PDALIFE.bin from the APK. Works on any APK.",
    default = false
) {
    execute {
        delete("assets", "PDALIFE.conf")
        delete("assets", "PDALIFE.bin")
    }
}
