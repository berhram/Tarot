pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            //Core
            library("coreKtx", "androidx.core", "core-ktx").version("1.8.0")
            library("appcompat", "androidx.appcompat", "appcompat").version("1.5.0")
            library(    "androidMaterial", "com.google.android.material","material").version("1.6.1")
            //Coroutines
            version("coroutines", "1.6.4")
            library("kotlinxCoroutinesCore", "org.jetbrains.kotlinx" , "kotlinx-coroutines-core").versionRef("coroutines")
            library("kotlinxCoroutinesAndroid", "org.jetbrains.kotlinx" , "kotlinx-coroutines-android").versionRef("coroutines")
            bundle("coroutines", listOf("kotlinxCoroutinesCore", "kotlinxCoroutinesAndroid"))
            //Orbit
            version("orbit", "4.3.2")
            library("orbitCore", "org.orbit-mvi", "orbit-core").versionRef("orbit")
            library("orbitViewModel", "org.orbit-mvi", "orbit-viewmodel").versionRef("orbit")
            library("orbitCompose", "org.orbit-mvi", "orbit-compose").versionRef("orbit")
            bundle("orbit", listOf("orbitCore", "orbitViewModel", "orbitCompose"))
            //Room
            version("room", "2.4.3")
            library("roomKtx", "androidx.room", "room-ktx").versionRef("room")
            library("roomRuntime", "androidx.room", "room-runtime").versionRef("room")
            library("roomCompiler", "androidx.room", "room-compiler").versionRef("room")
            library("roomTesting", "androidx.room", "room-testing").versionRef("room")
            //Activity
            version("activity", "1.5.1")
            library("activityKtx", "androidx.activity", "activity-ktx").versionRef("activity")
            library("activityCompose", "androidx.activity", "activity-compose").versionRef("activity")
            bundle("activity", listOf("activityKtx", "activityCompose"))
            //Compose
            library("ui", "androidx.compose.ui", "ui").version("1.2.0")
            library("uiUtil", "androidx.compose.ui", "ui-util").version("1.2.0")
            library("uiTooling", "androidx.compose.ui", "ui-tooling").version("1.2.0")
            library("foundation", "androidx.compose.foundation", "foundation").version("1.2.0")
            library("material", "androidx.compose.material", "material").version("1.2.0")
            library("constraint", "androidx.constraintlayout", "constraintlayout-compose").version("1.0.1")
            library("materialIconsCore", "androidx.compose.material", "material-icons-core").version("1.2.0")
            library("materialIconsExtended", "androidx.compose.material", "material-icons-extended").version("1.2.0")
            bundle("compose", listOf("ui", "uiUtil", "uiTooling", "foundation", "material", "materialIconsCore", "materialIconsExtended", "constraint"))
            //Koin
            version("koin", "3.2.0")
            library("koinAndroid", "io.insert-koin", "koin-android").versionRef("koin")
            library("koinAndroidxNavigation", "io.insert-koin", "koin-androidx-navigation").versionRef("koin")
            library("koinAndroidxCompose", "io.insert-koin", "koin-androidx-compose").versionRef("koin")
            bundle("koin", listOf("koinAndroid", "koinAndroidxCompose"))
            //Gson
            version("gson","2.9.1")
            library("gson", "com.google.code.gson", "gson").versionRef("gson")
            //Retrofit
            version("retrofit", "2.9.0")
            library("retrofit", "com.squareup.retrofit2", "retrofit").versionRef("retrofit")
            library("retrofitConverter", "com.squareup.retrofit2", "converter-gson").versionRef("retrofit")
            //OkHttp
            version("okHttp", "4.10.0")
            library("loggingInterceptor", "com.squareup.okhttp3", "logging-interceptor").versionRef("okHttp")
            library("okHttp", "com.squareup.okhttp3", "okhttp").versionRef("okHttp")
            //Internet
            bundle("internet", listOf("retrofit", "retrofitConverter", "loggingInterceptor", "okHttp"))
            //Accompanist
            version("accompanist", "0.25.1")
            library("accompanistSystemUiController", "com.google.accompanist", "accompanist-systemuicontroller").versionRef("accompanist")
            library("accompanistPager", "com.google.accompanist", "accompanist-pager").versionRef("accompanist")
            library("accompanistSwipeRefresh", "com.google.accompanist", "accompanist-swiperefresh").versionRef("accompanist")
            library("accompanistPagerIndicators", "com.google.accompanist", "accompanist-pager-indicators").versionRef("accompanist")
            bundle("accompanist", listOf("accompanistSystemUiController", "accompanistPager", "accompanistSwipeRefresh", "accompanistPagerIndicators"))
            //Testing
            library("orbitTest", "org.orbit-mvi", "orbit-test").version("4.3.2")
            library("junit", "junit", "junit").version("4.13.2")
            library("junitAndroid", "androidx.test.ext", "junit").version("1.1.3")
            library("uiTestJunit4", "androidx.compose.ui", "ui-test-junit4").version("1.1.1")
            //Appyx
            version("appyx", "1.0-alpha08")
            library("appyxCore", "com.bumble.appyx",  "core").versionRef("appyx")
            library("appyxTesting", "com.bumble.appyx", "testing-ui").versionRef("appyx")
            library("appyxJunit", "com.bumble.appyx", "testing-junit4").versionRef("appyx")
        }
    }
}

rootProject.name = "Tarot"
include(":app")
include(":data")
include(":domain")
include(":core")