# Grocery app Kotlin Multiplatform


### Note

If got error somethink like `error: Could not find "co.touchlab:stately-common"` running on IOS, try add `-lsqlite3` in  `Build settings` >> `Other Linker Flags` in Xcode. Also add
in commonMain module `build.gradle.kts` file:
```
commonMain.dependencies {
   ...
   implementation("co.touchlab:stately-common:2.0.7")
}
```