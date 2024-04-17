import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
   alias(libs.plugins.ktlint.gradle)
}
android {
    namespace = "com.adel.compose_modular"
    defaultConfig {

        vectorDrawables {
            useSupportLibrary = true
        }
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
}

dependencies {
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v270)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation (libs.accompanist.systemuicontroller)

    implementation(project(":core:shared_ui"))
    implementation(project(":feature:audio"))
    implementation(project(":feature:note"))
    implementation(project(":feature:message"))
    implementation(project(":feature:setting"))
    implementation(project(":feature:note_detail"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}