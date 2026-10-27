package app.pdalife.patches.pdalifebanner

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

/** Shared compatibility declarations for all four confirmed pdalife-injected games. */
object PdalifeCompatibility {
    val SLIME_RANCHER = Compatibility(
        name = "Slime Rancher",
        packageName = "com.playdigious.slimerancher",
        apkFileType = ApkFileType.APK,
        appIconColor = 0xFF0045,
        targets = listOf(AppTarget(version = "1.1.1103"))
    )
    val SUBNAUTICA = Compatibility(
        name = "Subnautica",
        packageName = "com.unknownworlds.subnautica",
        apkFileType = ApkFileType.APK,
        appIconColor = 0x0045FF,
        targets = listOf(AppTarget(version = "1.22.83811"))
    )
    val WORLD_OF_GOO_2 = Compatibility(
        name = "World of Goo 2",
        packageName = "com.twodboy.wog2",
        apkFileType = ApkFileType.APK,
        appIconColor = 0xFF4500,
        targets = listOf(AppTarget(version = "1.0.29878322"))
    )
    val DONT_STARVE_POCKET = Compatibility(
        name = "Don't Starve Pocket",
        packageName = "com.kleientertainment.doNotStarvePocket",
        apkFileType = ApkFileType.APK,
        appIconColor = 0x45FF00,
        targets = listOf(AppTarget(version = "1.19.22"))
    )

    val ALL = listOf(SLIME_RANCHER, SUBNAUTICA, WORLD_OF_GOO_2, DONT_STARVE_POCKET)
}
