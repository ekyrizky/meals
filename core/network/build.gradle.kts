plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.ekyrizky.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":core:test"))

    // coroutines
    implementation(libs.kotlinx.coroutines)
    testImplementation(libs.kotlinx.coroutines)
    testImplementation(libs.kotlinx.coroutines.test)

    // network
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp.interceptor)
    implementation(libs.sandwich)
    testImplementation(libs.okhttp.mockserver)
    testImplementation(libs.androidx.arch.core)

    // json
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // di
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}