plugins {
    id(Dependencies.Plugins.application)
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
        applicationId = Config.appId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstrumentationRunner
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
        kotlinCompilerExtensionVersion = Dependencies.Compose.version
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

    implementation(project(":domain"))
    implementation(project(":data"))
    kapt(Dependencies.Room.roomKapt)
    implementation(Dependencies.Compose.systemUiController)
    implementation(Dependencies.Room.room)
    implementation(Dependencies.Orbit.orbit)
    implementation(Dependencies.Orbit.orbitCore)
    implementation(Dependencies.Orbit.orbitTest)
    implementation(Dependencies.Compose.runtime)
    implementation(Dependencies.Compose.compiler)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.foundation_layout)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.runtimeLivedata)
    implementation(Dependencies.Compose.uiTooling)
    implementation(Dependencies.Compose.accompanist)
    implementation(Dependencies.Core.appCompat)
    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.material)
    implementation(Dependencies.Navigation.navigationCompose)
    implementation(Dependencies.Testing.jUnit)
    implementation(Dependencies.Testing.jUnitExt)
    implementation(Dependencies.Testing.espresso)
    implementation(Dependencies.Di.koin)
    implementation(Dependencies.Di.koinNavGraph)
    implementation(Dependencies.Di.koinCompose)
    //implementation(Dependencies.Di.koinWM)
    testImplementation(Dependencies.Room.roomTest)
}

kapt {
    correctErrorTypes = true
}
