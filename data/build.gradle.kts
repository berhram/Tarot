plugins {
    id(Dependencies.Plugins.library)
    id(Dependencies.Plugins.kotlinAndroid)
    kotlin(Dependencies.Plugins.kapt)
}

repositories {
    google()
    mavenCentral()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    kapt(Dependencies.Room.roomKapt)

    implementation(Dependencies.Retrofit.converterGson)
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Gson.gson)
    implementation(Dependencies.OkHttp.okHttp)
    implementation(Dependencies.OkHttp.loggingInterceptor)
    implementation(Dependencies.Core.coroutines)
    implementation(Dependencies.Core.appCompat)
    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.material)
    testImplementation(Dependencies.Testing.jUnit)
    testImplementation(Dependencies.Testing.mockk)
    androidTestImplementation(Dependencies.Testing.mockkAndroid)
    androidTestImplementation(Dependencies.Testing.composeUiTest)
    debugImplementation(Dependencies.Testing.composeTestRule)
    implementation(Dependencies.Room.room)
    testImplementation(Dependencies.Room.roomTest)
    implementation(Dependencies.Di.koin)
    implementation(Dependencies.Di.koinNavGraph)
    implementation(Dependencies.Di.koinCompose)
}