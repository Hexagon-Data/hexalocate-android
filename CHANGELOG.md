# CHANGELOG

`OpenLocate-Android` adheres to [Semantic Versioning](http://semver.org/).

## [1.0.1](https://github.com/OpenLocate/openlocate-android/tree/1.0.1)

#### Fixed

- `android:allowBackup` was set to false in the AndroidManifest.xml. Removed it so it doens't cause a manifest merge conflict.

---

## [1.0.0](https://github.com/OpenLocate/openlocate-android/tree/1.0.0)

#### Added

- A CHANGELOG to the project documenting each official release.
- Add support for passive location tracking accuracy (`LocationAccuracy.NO_POWER`) (#61)
- Non-Android O devices now no longer show a foreground notification when OpenLocate is running. (#60)
- The SDK now handles the location permission, and google play services alert dialog through a much simplified API. (#60)

#### Fixed

- The correct location provider gets logged 

---

## [0.1.0]

Initial release!
