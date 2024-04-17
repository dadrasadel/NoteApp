import convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
            }

            dependencies {
                "implementation"(libs.findBundle("hilt").get())
                "ksp"(libs.findLibrary("hilt.compiler").get())
            }

        }
    }

}
