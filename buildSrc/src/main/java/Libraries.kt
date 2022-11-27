object Libraries {
    const val lifecycle =  "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleRuntime}"

    object Supports {
        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
        const val material = "com.google.android.material:material:${Version.material}"
    }

    object Compose {
        const val compose = "androidx.compose.ui:ui:${Version.compose}"
        const val composeMaterial = "androidx.compose.material:material:${Version.materialCompose}"
        const val activityCompose = "androidx.activity:activity-compose:${Version.composeActivity}"
        const val compuseUIPreview = "androidx.compose.ui:ui-tooling-preview:${Version.composeUIPreview}"
    }

    object Firebase {
        const val firebaseStorageKtx = "com.google.firebase:firebase-storage-ktx:${Version.firebaseStorageKtx}"
        const val firebaseAnalyticsKtx = "com.google.firebase:firebase-analytics-ktx"
        const val firebaseStorage = "com.google.firebase:firebase-storage:${Version.firebaseStorage}"
    }

    object Hilt {
        const val daggerHilt = "com.google.dagger:hilt-android:${Version.daggerHilt}"
        const val kaptDaggerHilt = "com.google.dagger:hilt-android-compiler:${Version.daggerHilt}"
        const val kaptHiltCompiler = "androidx.hilt:hilt-compiler:${Version.hiltCompiler}"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Version.hiltCompiler}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Version.converterMoshi}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Version.converterGson}"
        const val gson = "com.google.code.gson:gson:${Version.gson}"
    }

    object Lotties {
        const val lottie = "com.airbnb.android:lottie-compose:${Version.lottie}"
    }

    object Navigation {
        const val navigationCompose = "androidx.navigation:navigation-compose:${Version.navigationCompose}"
    }

    object Glide {
        const val glide = "com.github.skydoves:landscapist-glide:${Version.glide}"
        const val coil = "io.coil-kt:coil-compose:${Version.coilCompose}"
    }

    object Room {
        const val room = "androidx.room:room-runtime:${Version.room}"
        const val kaptRoom = "androidx.room:room-compiler:${Version.kaptRoom}"
        const val coroutineRoom = "androidx.room:room-ktx:${Version.roomCoroutine}"
    }

    object Material {
        const val materialIcon = "androidx.compose.material:material-icons-extended:${Version.materialIcon}"
    }

    object Accompanist {
        const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Version.swipeRefresh}"
        const val accompanist = "com.google.accompanist:accompanist-pager:${Version.accompanist}"
        const val pager = "com.google.accompanist:accompanist-pager:${Version.pager}"
        const val pagerIndicator = "com.google.accompanist:accompanist-pager:${Version.pagerIndicator}"
        const val flowRow = "com.google.accompanist:accompanist-flowlayout:${Version.flowRow}"
    }

    object Socket {
        const val signalR = "com.microsoft.signalr:signalr:${Version.signalR}"
    }

    object Logger {
        const val logger = "org.slf4j:slf4j-api:${Version.logger}"
        const val slf4j = "uk.uuid.slf4j:slf4j-android:${Version.slf4jAndroid}"
    }

    object Test {
        const val junit = "junit:junit:${Version.junit}"
        const val androidJunit = "androidx.test.ext:junit:${Version.androidxJunit}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val composeUITest = "androidx.compose.ui:ui-test-junit4:${Version.composeUITest}"
        const val debugComposeUITooling = "androidx.compose.ui:ui-tooling:${Version.composeUITool}"
    }
}