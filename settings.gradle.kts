rootProject.name = "KinopoiskCmp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":core:data")
include(":core:network")
include(":core:util")
include(":core:security")
include(":core:ui")
include(":core:navigation")
include(":core:domain")
include(":core:sqlite")
include(":feature:awards-list")
include(":feature:collection-list")
include(":feature:movie")
include(":feature:otp")
include(":feature:person")
include(":feature:settings")
include(":feature:profile")
include(":feature:search")
include(":feature:favorite")
include(":feature:images-list")
include(":core:bottomsheet")
