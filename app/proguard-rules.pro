# SQLCipher
-keep class net.sqlcipher.** { *; }
-keep class org.sqlite.** { *; }

# Ktor client
-keep class io.ktor.** { *; }
-dontwarn io.ktor.client.**

# Kotlinx serialization
-keepattributes *Annotation*
-keepattributes Signature
-keep class kotlinx.serialization.** { *; }
-dontwarn kotlinx.serialization.**

# Room database
-keep class androidx.room.** { *; }
-keep @androidx.room.* annotation class * { *; }

# OkHttp
-keep class okhttp3.** { *; }
-keep class okio.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

# PDFBox
-keep class com.tom_roush.** { *; }

# Compose Markdown
-keep class com.github.jeziellago.** { *; }

# Koin DI
-keep class org.koin.** { *; }

# General Android
-dontoptimize
-dontwarn android.**
-dontwarn androidx.**