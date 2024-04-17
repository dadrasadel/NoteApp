@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.modular.android.library)
    alias(libs.plugins.modular.android.hilt)
}

android {
    namespace = "com.adel.note"

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
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.gson)
    implementation(project(":core:shared_ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":util"))
    implementation (libs.constraintlayout.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}