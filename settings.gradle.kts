pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.google.com")
        mavenCentral()
        jcenter()
        google()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://maven.google.com")
        mavenCentral()
        google()
        jcenter()
        maven("https://jitpack.io")
        maven {
            url = uri("https://androidx.dev/storage/compose-compiler/repository/")
        }
    }
}


rootProject.name = "compose_modular"
include(":app")
include(":core")
include(":core:domain")
include(":core:data")
include(":core:shared_ui")
include(":util")
include(":feature")
include(":feature:audio")
include(":feature:note")
include(":feature:setting")
include(":feature:message")
include(":feature:note_detail")
