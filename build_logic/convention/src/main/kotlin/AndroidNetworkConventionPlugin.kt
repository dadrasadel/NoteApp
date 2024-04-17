import convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidNetworkConventionPlugin:Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            dependencies {
                add("implementation", libs.findBundle("hilt").get())
                add("implementation", libs.findBundle("network").get())
                add("ksp", libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}