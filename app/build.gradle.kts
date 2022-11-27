plugins {
    id(Config.PluginIds.android)
    id(Config.PluginIds.kotlinAndroid)
    id(Config.PluginIds.kapt)
    id(Config.PluginIds.daggerHilt)
    id(Config.PluginIds.kotlinParcelize)
    id(Config.PluginIds.googleServices)
}

android {
    compileSdk = Version.compileSdk.toInt()

    defaultConfig {
        applicationId = Application.id
        minSdk = Version.minSdk.toInt()
        targetSdk = Version.targetSdk.toInt()
        versionName = Releases.versionName
        versionCode = Releases.versionCode
        testInstrumentationRunner = Config.testRunner
        vectorDrawables.useSupportLibrary = true

//        buildConfigField("String", "BASE_URL", property.getProperty("linkwillbeplacedhere"))
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.compose
        kotlinCompilerVersion = Version.kotlin
    }
}

dependencies {

    implementation(Libraries.Supports.coreKtx)
    implementation(Libraries.Supports.appcompat)
    implementation(Libraries.Supports.material)

    implementation(Libraries.Compose.compose)
    implementation(Libraries.Compose.composeMaterial)
    implementation(Libraries.lifecycle)
    implementation(Libraries.Compose.activityCompose)
    implementation(Libraries.Compose.activityCompose)

    implementation(Libraries.Firebase.firebaseStorageKtx)

    implementation(Libraries.Compose.compuseUIPreview)

    testImplementation(Libraries.Test.junit)
    androidTestImplementation(Libraries.Test.androidJunit)
    androidTestImplementation(Libraries.Test.espressoCore)
    androidTestImplementation(Libraries.Test.composeUITest)
    debugImplementation(Libraries.Test.debugComposeUITooling)

    implementation(Libraries.Compose.compuseUIPreview)

//    implementation(Libraries.Firebase.firebaseAnalyticsKtx)

    implementation(Libraries.Hilt.daggerHilt)
    implementation(Libraries.Hilt.hiltNavigationCompose)
    kapt(Libraries.Hilt.kaptDaggerHilt)
    kapt(Libraries.Hilt.kaptHiltCompiler)

    implementation(Libraries.Retrofit.retrofit)
    implementation(Libraries.Retrofit.converterMoshi)
    implementation(Libraries.Retrofit.converterGson)
    implementation(Libraries.Retrofit.gson)

    implementation(Libraries.Lotties.lottie)

    implementation(Libraries.Navigation.navigationCompose)

    implementation(Libraries.Glide.glide)

    implementation(Libraries.Room.room)
    implementation(Libraries.Room.coroutineRoom)
    kapt(Libraries.Room.kaptRoom)

    implementation(Libraries.Accompanist.swipeRefresh)

    implementation(Libraries.Material.materialIcon)

    implementation(Libraries.Glide.coil)

    implementation(Libraries.Firebase.firebaseStorage)

    implementation(Libraries.Accompanist.accompanist)
    implementation(Libraries.Accompanist.flowRow)
    implementation(Libraries.Accompanist.pager)
    implementation(Libraries.Accompanist.pagerIndicator)

    implementation(Libraries.Socket.signalR)

    implementation(Libraries.Logger.logger)
    implementation(Libraries.Logger.slf4j)
}