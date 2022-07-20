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
            library("coreKtx", "androidx.core", "core-ktx").version("1.7.0")
            library("appcompat", "androidx.appcompat", "appcompat").version("1.4.1")
            library("material", "com.google.android.material", "material").version("1.6.0")
            //Coroutines
            version("coroutines", "1.6.1")
            library("kotlinxCoroutinesCore", "org.jetbrains.kotlinx" , "kotlinx-coroutines-core").versionRef("coroutines")
            library("kotlinxCoroutinesAndroid", "org.jetbrains.kotlinx" , "kotlinx-coroutines-android").versionRef("coroutines")
            bundle("coroutines", listOf("kotlinxCoroutinesCore", "kotlinxCoroutinesAndroid"))
            //Orbit
            version("orbit", "4.3.2")
            library("orbitCore", "org.orbit-mvi", "orbit-core").versionRef("orbit")
            library("orbitViewmodel", "org.orbit-mvi", "orbit-viewmodel").versionRef("orbit")
            bundle("orbit", listOf("orbitCore", "orbitViewmodel"))
            //Room
            version("room", "2.4.2")
            library("roomKtx", "androidx.room", "room-ktx").versionRef("room")
            library("roomRuntime", "androidx.room", "room-runtime").versionRef("room")
            library("roomCompiler", "androidx.room", "room-compiler").versionRef("room")
            library("roomTesting", "androidx.room", "room-testing").versionRef("room")
            //Activity
            version("activity", "1.4.0")
            library("activityKtx", "androidx.activity", "activity-ktx").versionRef("activity")
            library("activityCompose", "androidx.activity", "activity-compose").versionRef("activity")
            bundle("activity", listOf("activityKtx", "activityCompose"))
            //Compose
            version("compose", "1.1.1")
            library("ui", "androidx.compose.ui", "ui").versionRef("compose")
            library("uiUtil", "androidx.compose.ui", "ui-util").versionRef("compose")
            library("uiTooling", "androidx.compose.ui", "ui-tooling").versionRef("compose")
            library("foundation", "androidx.compose.foundation", "foundation").versionRef("compose")
            library("material", "androidx.compose.material", "material").versionRef("compose")
            library("materialIconsCore", "androidx.compose.material", "material-icons-core").versionRef("compose")
            library("materialIconsExtended", "androidx.compose.material", "material-icons-extended").versionRef("compose")
            bundle("compose", listOf("ui", "uiUtil", "uiTooling", "foundation", "material", "materialIconsCore", "materialIconsExtended"))
            //Koin
            version("koin", "3.2.0")
            library("koinAndroid", "io.insert-koin", "koin-android").versionRef("koin")
            library("koinAndroidxNavigation", "io.insert-koin", "koin-androidx-navigation").versionRef("koin")
            library("koinAndroidxCompose", "io.insert-koin", "koin-androidx-compose").versionRef("koin")
            bundle("koin", listOf("koinAndroid", "koinAndroidxCompose"))
            //Navigation
            version("navigation", "2.4.1")
            library("navigationCompose", "androidx.navigation", "navigation-compose").versionRef("navigation")
            //Gson
            version("gson","2.8.6")
            library("gson", "com.google.code.gson", "gson").versionRef("gson")
            //Retrofit
            version("retrofit", "2.9.0")
            library("retrofit", "com.squareup.retrofit2", "retrofit").versionRef("retrofit")
            library("retrofitConverter", "com.squareup.retrofit2", "converter-gson").versionRef("retrofit")
            //OkHttp
            version("okHttp", "4.9.3")
            library("loggingInterceptor", "com.squareup.okhttp3", "logging-interceptor").versionRef("okHttp")
            library("okHttp", "com.squareup.okhttp3", "okhttp").versionRef("okHttp")
            //Internet
            bundle("internet", listOf("retrofit", "retrofitConverter", "loggingInterceptor", "okHttp"))
            //Accompanist
            version("accompanist", "0.23.1")
            library("accompanistSystemuicontroller", "com.google.accompanist", "accompanist-systemuicontroller").versionRef("accompanist")
            library("accompanistPager", "com.google.accompanist", "accompanist-pager").versionRef("accompanist")
            library("accompanistSwiperefresh", "com.google.accompanist", "accompanist-swiperefresh").versionRef("accompanist")
            library("accompanistPagerIndicators", "com.google.accompanist", "accompanist-pager-indicators").versionRef("accompanist")
            bundle("accompanist", listOf("accompanistSystemuicontroller", "accompanistPager", "accompanistSwiperefresh", "accompanistPagerIndicators"))
            //Testing
            library("orbitTest", "org.orbit-mvi", "orbit-test").version("4.3.2")
            library("junit", "junit", "junit").version("4.13.2")
            library("junitAndroid", "androidx.test.ext", "junit").version("1.1.3")
            library("uiTestJunit4", "androidx.compose.ui", "ui-test-junit4").version("1.1.1")
        }
    }
}

rootProject.name = "Tarot"
include(":app")
include(":data")
include(":domain")
include(":core")
