package app.pdalife.patches.pdalifebanner

import app.morphe.patcher.patch.rawResourcePatch
import app.pdalife.patches.pdalifebanner.PdalifeCompatibility.ALL

/**
 * Deletes the PDALIFE injector's encrypted config assets.
 *
 * Even if something in the app still loads libPDALIFE.so, without PDALIFE.conf the
 * native injector cannot read its configuration and cannot show the banner/toast.
 */
// Set to true to make this patch universal (works on any app, no version/package restrictions).
val universalAssetsPatch = true

@Suppress("unused")
val removePdalifeAssetsPatch = rawResourcePatch(
    name = "Remove PDALIFE config assets",
    description = "Deletes assets/PDALIFE.conf and assets/PDALIFE.bin from the APK.",
    default = true
) {
    if (!universalAssetsPatch) {
        ALL.forEach { compatibleWith(it) }
    }

    execute {
        // Delete the encrypted config assets. The delete(type, name) signature matches
        // APK entry paths such as assets/PDALIFE.conf.
        delete("assets", "PDALIFE.conf")
        delete("assets", "PDALIFE.bin")
    }
}
