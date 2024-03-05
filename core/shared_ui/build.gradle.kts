@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.adel.shared_ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
}

dependencies {
    /* compose */
    api(libs.androidx.compose.ui)
    api(libs.androidx.activity.compose)
    api(libs.androidx.compose.compose.material3)
    api(libs.androidx.compose.compose.material)
    api(libs.androidx.compose.foundation)
    api(libs.constraintlayout.compose)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.ui.tooling.preview.android)
    api(platform(libs.androidx.compose.bom))
    api (libs.accompanist.navigation.animation)
    api(libs.androidx.core.ktx)
    api(libs.appcompat)
    api(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}