plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("app.cash.sqldelight") version "2.0.1"
}

android {
    namespace = "com.slateblua.taptap"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.slateblua.taptap"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)

    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.ui)
    implementation(libs.material3)
    implementation(libs.ui.tooling.preview)
    implementation(libs.ui.graphics)

    testImplementation(libs.junit)

    androidTestImplementation(libs.junit.android)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    androidTestImplementation(libs.junit4.ui)

    debugImplementation(libs.ui.test.manifest)
    debugImplementation(libs.ui.debug.tooling)

    implementation(libs.koin)

    implementation(libs.sqldelight)
    implementation(libs.sqldelight.coroutines)

    implementation(libs.voyager.koin)
    implementation(libs.voyager.navigator)
}

sqldelight {
    databases {
        create("TapDatabase") {
            packageName.set("com.slateblua.db")
        }
    }
}
