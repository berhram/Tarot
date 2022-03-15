plugins {
    id(Dependencies.Plugins.library)
    id(Dependencies.Plugins.kotlinAndroid)
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
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Gson.gson)
    implementation(Dependencies.Retrofit.converterGson)
    implementation(Dependencies.OkHttp.okHttp)
    implementation(Dependencies.OkHttp.loggingInterceptor)
    implementation(Dependencies.Core.coroutines)
    implementation(Dependencies.Core.appCompat)
    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.material)
    implementation(Dependencies.Testing.jUnit)
    implementation(Dependencies.Testing.jUnitExt)
    implementation(Dependencies.Testing.espresso)
}