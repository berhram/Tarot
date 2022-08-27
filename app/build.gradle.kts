import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput

plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.application")
    id("kotlin-parcelize")
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
        kotlinCompilerExtensionVersion = "1.2.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    applicationVariants.all(object : Action<ApplicationVariant> {
        override fun execute(variant: ApplicationVariant) {
            variant.outputs.all(object : Action<BaseVariantOutput> {
                override fun execute(output: BaseVariantOutput) {
                    val outputImpl = output as com.android.build.gradle.internal.api.BaseVariantOutputImpl
                    val fileName = output.outputFileName
                        .replace("app-release", "tarot-release-v${defaultConfig.versionName}")
                        .replace("app-debug", "tarot-debug-v${defaultConfig.versionName}")
                    outputImpl.outputFileName = fileName
                }
            })
        }
    })
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":core"))
    implementation(libs.coreKtx)
    implementation(libs.androidMaterial)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.orbit)
    implementation(libs.bundles.activity)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.accompanist)
    implementation(libs.appyxCore)
    testImplementation(libs.appyxJunit)
    testImplementation(libs.appyxTestingUnit)
    testImplementation(libs.orbitTest)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junitAndroid)
    androidTestImplementation(libs.uiTestJunit4)
    androidTestImplementation(libs.appyxTesting)
}

kapt {
    correctErrorTypes = true
}