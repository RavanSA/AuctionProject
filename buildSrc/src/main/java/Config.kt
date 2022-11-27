object Config {
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"

    object Dependencies {
        const val kotlinPlugin = "gradle-plugin"
        const val androidPlugin = "com.android.tools.build:gradle:${Version.gradle}"
        const val hiltAndroidPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Version.daggerHilt}"
        const val googleServices = "com.google.gms:google-services:${Version.googleServices}"
    }

    object PluginIds {
        const val android = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val kapt = "kotlin-kapt"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinAndroidExtensions = "kotlin-android-extensions"
        const val daggerHilt = "dagger.hilt.android.plugin"
        const val kotlinParcelize = "kotlin-parcelize"
        const val googleServices = "com.google.gms.google-services"

    }

    object Repositories {
        const val gradleMaven = "https://plugins.gradle.org/m2/"
    }
}