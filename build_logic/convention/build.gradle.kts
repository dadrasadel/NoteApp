import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    `kotlin-dsl`
}
group = "com.adel.compose_modular.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    compileOnly(libs.androidx.room.gradle.plugin)
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}
gradlePlugin{
    plugins{
        register("androidApplication"){
            id="modular.android.application"
            implementationClass="AndroidApplicationConventionPlugin"
        }
        register("androidHilt"){
            id="modular.android.hilt"
            implementationClass="AndroidHiltConventionPlugin"
        }
        register("androidLibrary"){
            id="modular.android.library"
            implementationClass="AndroidLibraryConventionPlugin"
        }
        register("androidRoom"){
            id="modular.android.room"
            implementationClass="AndroidRoomConventionPlugin"
        }
        register("androidNetwork"){
            id="modular.android.network"
            implementationClass="AndroidNetworkConventionPlugin"
        }
    }
}