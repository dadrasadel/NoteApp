@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.modular.android.library)
    alias(libs.plugins.modular.android.hilt)
}

android {
    namespace = "com.adel.shared_ui"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
    implementation(project(":core:data"))
    implementation("com.github.bumptech.glide:glide:4.12.0")
    ksp("com.github.bumptech.glide:compiler:4.12.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}