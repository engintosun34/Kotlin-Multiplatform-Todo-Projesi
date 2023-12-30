import android.os.Build

//class AndroidPlatform : Platform {
  //  override val name: String = "Android ${Build.VERSION.SDK_INT}"
//}

//actual fun getPlatform(): Platform = AndroidPlatform()

// AndroidMain/platform.android.kt

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun initPlatform() {
    // Android platform için başlangıç ayarları
}
