pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android")) {
                useModule("com.android.tools.build:gradle:7.1.3")
            }
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.6.10")
            }
        }
    }
}
rootProject.name = "Tarot"
include(":app")
include(":data")
