plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.application")
    kotlin("kapt")
}

repositories {
    google()
    mavenCentral()
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.velvet.tarot"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
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
    implementation(project(":data"))
    implementation(project(":coremvi"))
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

kapt {
    correctErrorTypes = true
}