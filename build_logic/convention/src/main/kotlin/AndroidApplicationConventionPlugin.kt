import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import convention.configureAndroidCompose
import convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationConventionPlugin:Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
            }
            extensions.configure (ApplicationExtension::class.java){
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                compileSdk = 34
                defaultConfig{
                    applicationId="com.adel.compose_modular"
                    targetSdk=34
                    minSdk=21
                    versionCode=1
                    versionName="1.0"
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
//                configureAndroidCompose(this)
            }
        }
    }
}