@file:Suppress("InvalidPackageDeclaration", "ForbiddenComment")

import com.android.build.gradle.internal.dsl.BuildType
import java.io.FileInputStream
import java.util.*

/* PLUGINS */

plugins {
    id(PluginIds.Android.LIBRARY)
    id(PluginIds.Kotlin.Android.ANDROID)
    id(PluginIds.Quality.DETEKT)
    // id(PluginIds.Test.Android.J_UNIT_5) // FIXME: Failed to notify project evaluation listener.
    id(PluginIds.Dependency.VERSIONS)
}

/* ANDROID */

android {
    buildTypes { buildTypes() }
}

/* DEPENDENCIES */

dependencies {
    projectImplementation()
    implementation()
    testProjectImplementation()
    testImplementation()
    plugins()
}

/* *********************************************************************************************************************** */

/* CONFIGURATION EXTENSION FUNCTIONS - ANDROID */

fun NamedDomainObjectContainer<BuildType>.buildTypes() {
    val themoviedbApiProperties = Properties()
    themoviedbApiProperties.load(FileInputStream(file(Config.Keys.TheMoviesDb.API_FILE_PATH)))
    val themoviedbApiKey = themoviedbApiProperties[Keys.TheMoviesDb.Property.API_KEY].asString()
    named(Android.BuildTypes.DEBUG) {
        buildConfigField(BuildConfig.Field.STRING, Config.Keys.TheMoviesDb.API_KEY_CONST, themoviedbApiKey)
    }
    named(Android.BuildTypes.RELEASE) {
        buildConfigField(BuildConfig.Field.STRING, Config.Keys.TheMoviesDb.API_KEY_CONST, themoviedbApiKey)
    }
}

/* DEPENDENCIES - PROJECT IMPLEMENTATION */

fun DependencyHandlerScope.projectImplementation() {
    kotlinProjectImplementation()
}

fun DependencyHandlerScope.kotlinProjectImplementation() {
    implementation(project(Project.Implementation.Kotlin.DOMAIN))
    implementation(project(Project.Implementation.Kotlin.NETWORK))
}

/* DEPENDENCIES - IMPLEMENTATION */

fun DependencyHandlerScope.implementation() {
    implementationKotlin()
    implementationDi()
}

fun DependencyHandlerScope.implementationKotlin() {
    implementation(Deps.Kotlin.Core.KOTLIN)
    implementation(Deps.Kotlin.Coroutines.CORE)
}

fun DependencyHandlerScope.implementationDi() {
    implementation(Deps.Di.Koin.Kotlin.CORE)
}

/* DEPENDENCIES - TEST PROJECT IMPLEMENTATION */

fun DependencyHandlerScope.testProjectImplementation() {
    kotlinTestProjectImplementation()
}

fun DependencyHandlerScope.kotlinTestProjectImplementation() {
    testImplementation(project(Project.TestImplementation.Kotlin.TEST))
}

/* DEPENDENCIES - TEST IMPLEMENTATION */

fun DependencyHandlerScope.testImplementation() {
    testImplementationKotlin()
    testImplementationSpek()
    testImplementationJUnit()
    testImplementationTest()
    testImplementationMock()
}

fun DependencyHandlerScope.testImplementationKotlin() {
    testImplementation(Deps.Kotlin.Coroutines.Test.TEST)
}

fun DependencyHandlerScope.testImplementationSpek() {
    testImplementation(Deps.Test.Spek.DSL)
    testImplementation(Deps.Test.Spek.J_UNIT_5)
    testRuntimeOnly(Deps.Kotlin.Core.KOTLIN_REFLECT)
}

fun DependencyHandlerScope.testImplementationJUnit() {
    testImplementation(Deps.Test.JUnit.J_UNIT_4)
    testRuntimeOnly(Deps.Test.JUnit.J_UNIT_5)
}

fun DependencyHandlerScope.testImplementationTest() {
    testImplementation(Deps.Test.Assert.STRIKT)
}

fun DependencyHandlerScope.testImplementationMock() {
    testImplementation(Deps.Test.Mock.MOCK_K)
}

/* DEPENDENCIES - PLUGINS */

fun DependencyHandlerScope.plugins() {
    detektPlugins(Deps.Plugin.DETEKT_FORMATTING)
}
