plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.junit)
    alias(libs.plugins.hilt)
//    alias(libs.plugins.google.services)
//    alias(libs.plugins.firebase.crashlytics)
//    alias(libs.plugins.firebase.appdistribution)
}

android {
    namespace = "com.javichaques.randomuser"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.javichaques.randomuser"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    ksp(libs.destinations.ksp)

    implementation(libs.timber)

//    implementation(platform(libs.firebase.bom))
//    implementation(libs.firebase.analytics)
//    implementation(libs.firebase.crashlytics)

//    implementation(platform(libs.arrow.bom))
//    implementation(libs.arrow.core)
//    implementation(libs.arrow.retrofit)

//    implementation(libs.coil.core)

    debugImplementation(libs.leakcanary)
}
