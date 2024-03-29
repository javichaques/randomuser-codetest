import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.google.firebase.appdistribution.gradle.firebaseAppDistribution

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.junit)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.appdistribution)
}

android {
    namespace = BuildConstants.APPLICATION_ID
    compileSdk = BuildConstants.Sdk.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildConstants.APPLICATION_ID

        minSdk = BuildConstants.Sdk.MIN_SDK_VERSION
        targetSdk = BuildConstants.Sdk.TARGET_SDK_VERSION

        versionCode = BuildConstants.VERSION_CODE
        versionName = BuildConstants.VERSION_NAME
    }

    signingConfigs {
        val localProperties = gradleLocalProperties(rootDir, providers)
        val keystoreFile = file("$rootDir/credentials/keystore.jks")
        val keystorePassword = localProperties.getProperty("keystore.password") ?: Credentials.Keystore.password

        named("debug") {
            storeFile = keystoreFile
            storePassword = keystorePassword
            keyAlias = localProperties.getProperty("keystore.debug.alias") ?: Credentials.Keystore.Debug.alias
            keyPassword = localProperties.getProperty("keystore.debug.password") ?: Credentials.Keystore.Debug.password
        }

        create("release") {
            storeFile = keystoreFile
            storePassword = keystorePassword
            keyAlias = localProperties.getProperty("keystore.release.alias") ?: Credentials.Keystore.Release.alias
            keyPassword = localProperties.getProperty("keystore.release.password") ?: Credentials.Keystore.Release.password
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true

            signingConfig = signingConfigs.getByName("debug")

            manifestPlaceholders["loggingEnabled"] = "false"
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false

            signingConfig = signingConfigs.getByName("release")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )

            manifestPlaceholders["loggingEnabled"] = "true"
        }
    }

    flavorDimensions.add("environment")

    productFlavors {
        create("development") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"

            firebaseAppDistribution {
                appId = BuildConstants.Development.Firebase.APP_ID
                serviceCredentialsFile = "$rootDir/credentials/sa_app_distribution.json"
                artifactType = "APK"
            }
        }

        create("production") {
            dimension = "environment"

            firebaseAppDistribution {
                appId = BuildConstants.Production.Firebase.APP_ID
                serviceCredentialsFile = "$rootDir/credentials/sa_app_distribution.json"
                artifactType = "APK"
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    testImplementation(project(":core:testing"))

    implementation(project(":feature:users"))

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.startup)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.destinations.core)
    implementation(libs.destinations.animations)
    ksp(libs.destinations.ksp)

    implementation(libs.timber)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)

    implementation(libs.coil.core)

    debugImplementation(libs.leakcanary)
}
