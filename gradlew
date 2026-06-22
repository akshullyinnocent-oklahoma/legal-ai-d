#!/bin/bash
export ANDROID_HOME=/opt/android-sdk
export ANDROID_SDK_ROOT=/opt/android-sdk
export ANDROID_NDK_HOME=/opt/android-sdk/android-ndk-r27d
export ANDROID_NDK_ROOT=/opt/android-sdk/android-ndk-r27d
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=/opt/gradle-9.5.1/bin:$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$ANDROID_SDK_ROOT/build-tools/34.0.0:$JAVA_HOME/bin:$PATH
/opt/gradle-9.5.1/bin/gradle "$@"
