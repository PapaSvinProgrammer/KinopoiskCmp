import com.codingfeline.buildkonfig.compiler.FieldSpec
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.buildkonfig)
}

buildkonfig {
    packageName = "ru.mordva.network"

    defaultConfigs { }

    defaultConfigs {
        val props = rootProject.file("local.properties")
            .takeIf { it.exists() }
            ?.inputStream()?.use { Properties().apply { load(it) } }

        val apiKey = props?.getProperty("POISK_API_KEY") ?: ""

        buildConfigField(FieldSpec.Type.STRING, "POISK_API_KEY", "\"$apiKey\"")
    }
}

kotlin {
    androidLibrary {
        namespace = "com.mordva.network"
        compileSdk = 36
        minSdk = 24

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    val xcfName = "core:networkKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        commonMain.dependencies {
            implementation(projects.core.util)
            implementation(libs.koin.core)
            implementation(libs.bundles.ktor)
        }

        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}