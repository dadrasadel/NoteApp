@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.modular.android.library)
    alias(libs.plugins.modular.android.hilt)
}

android {
    namespace = "com.adel.note_detail"
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
    implementation(project(":core:shared_ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation (libs.work.runtime)
    implementation (libs.gson)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    debugImplementation(libs.androidx.ui.tooling)
}