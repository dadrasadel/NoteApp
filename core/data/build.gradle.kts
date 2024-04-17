@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.modular.android.library)
    alias(libs.plugins.modular.android.hilt)
    alias(libs.plugins.modular.android.room)
    alias(libs.plugins.modular.android.network)
}

android {
    namespace = "com.adel.data"
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
    //add util
    implementation(project(":util"))
    //test
    testImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // JUNIT 5
    testImplementation (libs.jupiter)
    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.inline)

    // MOCKITO
    testImplementation (libs.mockito.kotlin)

    // CORE UNIT TESTING
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.junit.ext)

    // COROUTINES TEST
    testImplementation (libs.core.testing)
}