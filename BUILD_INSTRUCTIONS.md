# Legal AI-d - Build Instructions

## Prerequisites
- Java 17 JDK
- Android SDK (API 34)
- Android NDK r27d
- Gradle 8.7

## Quick Build

```bash
# Set environment variables
export ANDROID_HOME=/opt/android-sdk
export ANDROID_NDK_ROOT=/opt/android-sdk/ndk/27.1.11992032
export PATH=$ANDROID_HOME/cmdline-tools/latest/bin:$PATH

# Generate signed APK
./gradlew assembleRelease
```

## APK Location
After successful build:
- `app/build/outputs/apk/release/app-release.apk`

## Manual SDK Setup
```bash
# Download command line tools
curl -O https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip
unzip commandlinetools-linux-11076708_latest.zip -d $ANDROID_HOME/cmdline-tools/

# Accept licenses
yes | sdkmanager --sdk_root=$ANDROID_HOME "platforms;android-34"
yes | sdkmanager --sdk_root=$ANDROID_HOME "build-tools;34.0.0"
yes | sdkmanager --sdk_root=$ANDROID_HOME "ndk;27.1.11992032"
```

## Project Structure Summary
- **44 Kotlin files** covering all core functionality
- **Encrypted Room database** with SQLCipher
- **Multi-provider API clients** (OpenAI, Anthropic, OpenRouter, Groq, Cerebras, NVIDIA, DeepSeek)
- **Hybrid context strategy** for document processing
- **Agentic memory system** with persistent TaskLog
- **Terminal emulator** for Python scripting
- **AMOLED Dark Theme** with Cyan accents

## Key Files
- `app/src/main/java/com/legalai/app/data/model/ContextBuilder.kt` - Hybrid context strategy
- `app/src/main/java/com/legalai/app/data/remote/api/` - API clients
- `app/src/main/java/com/legalai/app/ui/theme/` - Theme definitions
- `database_schema.sql` - Complete SQL reference
