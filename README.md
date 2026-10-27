# PDALIFE Banner Remover

Morphe patches that remove the **PDALIFE banner** and **PDALIFE toast** injected by pdalife.com into its modded APKs.

## What this does

pdalife.com injects branding into its modded APKs through a native injector library (`lib/arm64-v8a/libPDALIFE.so`) triggered by a custom `com.pdalife.PdaApplication` class declared in `AndroidManifest.xml`, plus encrypted config assets (`assets/PDALIFE.conf`, `assets/PDALIFE.bin`).

Because the DEXes in these mods are encrypted at rest (pairip protection), there is no fingerprinterable Java call site to target. Instead this patch bundle uses two patches:

1. **Remove PDALIFE Application from manifest** — Removes `android:name="com.pdalife.PdaApplication"` from `AndroidManifest.xml`. Without the custom Application class, `onCreate()` never runs, `System.loadLibrary("PDALIFE")` is never called, and no hooks are installed.
2. **Remove PDALIFE config assets** — Deletes `assets/PDALIFE.conf` and `assets/PDALIFE.bin`. Even if something loads `libPDALIFE.so`, without the config the native injector cannot show the banner/toast.

The mod's own single-player modifications are **not** touched.

## Supported apps

<!-- PATCHES_START EXPANDED -->
> **[v1.1.1](https://github.com/steeepaaaan/pdalife-morphe-patches/releases/tag/v1.1.1)**&nbsp;&nbsp;•&nbsp;&nbsp;`main`&nbsp;&nbsp;•&nbsp;&nbsp;10 patches total
<details open>
<summary>📦 Slime Rancher&nbsp;&nbsp;•&nbsp;&nbsp;2 patches</summary>
<br>

**🎯 Supported versions:**

| 1.1.1103 |
| :---: |

| 💊&nbsp;Patch | 📜&nbsp;Description | ⚙️&nbsp;Options |
|----------|----------------|-----------|
| [Remove PDALIFE Application from manifest](#remove-pdalife-application-from-manifest) | Removes android:name="com.pdalife.PdaApplication" from AndroidManifest.xml so the PDALIFE injector never runs. |  |
| [Remove PDALIFE config assets](#remove-pdalife-config-assets) | Deletes assets/PDALIFE.conf and assets/PDALIFE.bin from the APK. Keeps libPDALIFE.so so native onCreate methods resolve. |  |

</details>

<details open>
<summary>📦 Subnautica&nbsp;&nbsp;•&nbsp;&nbsp;2 patches</summary>
<br>

**🎯 Supported versions:**

| 1.22.83811 |
| :---: |

| 💊&nbsp;Patch | 📜&nbsp;Description | ⚙️&nbsp;Options |
|----------|----------------|-----------|
| [Remove PDALIFE Application from manifest](#remove-pdalife-application-from-manifest) | Removes android:name="com.pdalife.PdaApplication" from AndroidManifest.xml so the PDALIFE injector never runs. |  |
| [Remove PDALIFE config assets](#remove-pdalife-config-assets) | Deletes assets/PDALIFE.conf and assets/PDALIFE.bin from the APK. Keeps libPDALIFE.so so native onCreate methods resolve. |  |

</details>

<details open>
<summary>📦 World of Goo 2&nbsp;&nbsp;•&nbsp;&nbsp;2 patches</summary>
<br>

**🎯 Supported versions:**

| 1.0.29878322 |
| :---: |

| 💊&nbsp;Patch | 📜&nbsp;Description | ⚙️&nbsp;Options |
|----------|----------------|-----------|
| [Remove PDALIFE Application from manifest](#remove-pdalife-application-from-manifest) | Removes android:name="com.pdalife.PdaApplication" from AndroidManifest.xml so the PDALIFE injector never runs. |  |
| [Remove PDALIFE config assets](#remove-pdalife-config-assets) | Deletes assets/PDALIFE.conf and assets/PDALIFE.bin from the APK. Keeps libPDALIFE.so so native onCreate methods resolve. |  |

</details>

<details open>
<summary>📦 Don't Starve Pocket&nbsp;&nbsp;•&nbsp;&nbsp;2 patches</summary>
<br>

**🎯 Supported versions:**

| 1.19.22 |
| :---: |

| 💊&nbsp;Patch | 📜&nbsp;Description | ⚙️&nbsp;Options |
|----------|----------------|-----------|
| [Remove PDALIFE Application from manifest](#remove-pdalife-application-from-manifest) | Removes android:name="com.pdalife.PdaApplication" from AndroidManifest.xml so the PDALIFE injector never runs. |  |
| [Remove PDALIFE config assets](#remove-pdalife-config-assets) | Deletes assets/PDALIFE.conf and assets/PDALIFE.bin from the APK. Keeps libPDALIFE.so so native onCreate methods resolve. |  |

</details>

<details open>
<summary>🌐 Universal&nbsp;&nbsp;•&nbsp;&nbsp;2 patches</summary>
<br>

| 💊&nbsp;Patch | 📜&nbsp;Description | ⚙️&nbsp;Options |
|----------|----------------|-----------|
| [Remove PDALIFE Application from manifest (universal)](#remove-pdalife-application-from-manifest-universal) | Universal patch: Removes android:name="com.pdalife.PdaApplication" from AndroidManifest.xml so the PDALIFE injector never runs. Works on any APK. |  |
| [Remove PDALIFE config assets (universal)](#remove-pdalife-config-assets-universal) | Universal patch: Deletes assets/PDALIFE.conf and assets/PDALIFE.bin from the APK. Keeps libPDALIFE.so so native onCreate methods resolve. Works on any APK. |  |

</details>

<!-- PATCHES_END -->

## Installation

### Using Morphe Manager (recommended)

1. Download the latest `patches.mpp` from the [Releases](https://github.com/steeepaaaan/pdalife-morphe-patches/releases) page.
2. Open **Morphe Manager** → Patch sources → add the `.mpp` as a **local patch source**.
3. Select your pdalife mod APK (for Subnautica, extract and patch `base.apk` from the split set).
4. Enable both patches:
   - Remove PDALIFE Application from manifest
   - Remove PDALIFE config assets
5. Patch, install, and launch. No banner on cold start, no toast.

### Signing / install warning

Morphe Manager re-signs the patched APK with its own keystore. If the pdalife mod is already installed, Android will refuse to install over it (signature mismatch). **Uninstall the existing copy first**, and **back up your save data before uninstalling** — single-player saves may live in app-private storage and uninstalling can delete them.

## Building from source

Requirements:
- [Android Studio](https://developer.android.com/studio) with Android SDK
- GitHub account with a Personal Access Token (`read:packages` scope) for downloading `morphe-patcher` from GitHub Packages

```powershell
# Clone the repo
git clone https://github.com/steeepaaaan/pdalife-morphe-patches.git
cd pdalife-banner-remover

# Create gradle.properties (from the template) with your credentials:
# gpr.user=<your-github-username>
# gpr.key=<your-github-personal-access-token>

# Create local.properties pointing at your Android SDK:
# sdk.dir=C:\Users\admin\AI\tools\android_sdk

# Build
.\gradlew.bat buildAndroid
```

The built `.mpp` is output to `patches/build/libs/patches.mpp`.

## How the patches work

All DEXes in pdalife mods are encrypted at rest, so standard bytecode fingerprints cannot match. This patch bundle instead targets non-DEX entry points:

| Patch | Type | Target | Mechanism |
|-------|------|--------|-----------|
| Remove PDALIFE Application from manifest | `resourcePatch` | `AndroidManifest.xml` | Removes `android:name="com.pdalife.PdaApplication"` so the injector trigger never runs |
| Remove PDALIFE config assets | `rawResourcePatch` | `assets/PDALIFE.conf`, `assets/PDALIFE.bin` | Deletes encrypted config so the native injector has nothing to read |

## License

GPLv3 with the Morphe extra condition: do not name a derivative work "Morphe". See [LICENSE](LICENSE) and [NOTICE](NOTICE) for details.

This project is not affiliated with the Morphe open source project.
