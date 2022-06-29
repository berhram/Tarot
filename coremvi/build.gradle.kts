plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.orbit)
    implementation(libs.roomKtx)
    implementation(libs.roomRuntime)
    kapt(libs.roomCompiler)
    testImplementation(libs.roomTesting)
    implementation(libs.bundles.activity)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.accompanist)
    implementation(libs.navigationCompose)
    implementation(libs.gson)
    implementation(libs.bundles.internet)
    testImplementation(libs.orbitTest)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junitAndroid)
    androidTestImplementation(libs.uiTestJunit4)
}