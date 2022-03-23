object Dependencies {
    object OkHttp {
        const val version = "4.9.3"

        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val okHttp = "com.squareup.okhttp3:okhttp:$version"
    }

    object Room {
        const val version = "2.4.2"

        const val room = "androidx.room:room-runtime:$version"
        const val roomKapt = "androidx.room:room-compiler:$version"
        const val roomTest = "androidx.room:room-testing:$version"
    }

    object Gson {
        const val version = "2.8.6"

        const val gson = "com.google.code.gson:gson:$version"
    }

    object Retrofit {
        const val version = "2.9.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Compose {
        const val version = "1.1.1"
        const val versionAccompanist = "0.23.1"

        const val runtime = "androidx.compose.runtime:runtime:$version"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$versionAccompanist"
        const val compiler = "androidx.compose.compiler:compiler:$version"
        const val ui = "androidx.compose.ui:ui:$version"
        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val foundation_layout = "androidx.compose.foundation:foundation-layout:$version"
        const val material = "androidx.compose.material:material:$version"
        const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
        const val accompanist = "com.google.accompanist:accompanist-swiperefresh:$versionAccompanist"
    }

    object Navigation {
        const val version = "2.4.1"

        const val navigationCompose = "androidx.navigation:navigation-compose:$version"
    }

    object Orbit {
        const val version = "4.3.2"
        const val orbitCore = "org.orbit-mvi:orbit-core:$version"
        const val orbit = "org.orbit-mvi:orbit-viewmodel:$version"
        const val orbitTest = "org.orbit-mvi:orbit-test:$version"
    }

    object Core {
        const val versionCoreKtx = "1.7.0"
        const val versionAppCompat = "1.4.1"
        const val versionMaterial = "1.5.0"
        const val versionCoroutines = "1.6.0"

        const val coreKtx = "androidx.core:core-ktx:$versionCoreKtx"
        const val appCompat = "androidx.appcompat:appcompat:$versionAppCompat"
        const val material  = "com.google.android.material:material:$versionMaterial"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$versionCoroutines"
    }

    object Di {
        const val version = "3.1.5"

        const val koin = "io.insert-koin:koin-android:$version"
        //const val koinWM = "io.insert-koin:koin-androidx-workmanager:$versionKoin"
        const val koinNavGraph = "io.insert-koin:koin-androidx-navigation:$version"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:$version"
    }


    object Testing {
        const val versionJUnit = "4.13.2"
        const val versionJUnitExt = "1.1.3"
        const val versionEspresso = "3.4.0"

        const val jUnit = "junit:junit:$versionJUnit"
        const val jUnitExt = "androidx.test.ext:junit:$versionJUnitExt"
        const val espresso = "androidx.test.espresso:espresso-core:$versionEspresso"

    }

    object Plugins {
        const val application = "com.android.application"
        const val library = "com.android.library"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
        const val kapt = "kapt"
        const val hilt = "dagger.hilt.android.plugin"
    }
}