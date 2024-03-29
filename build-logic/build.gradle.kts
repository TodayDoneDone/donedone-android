/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
  `kotlin-dsl`
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.gradle.dependency.handler.extensions)
}

gradlePlugin {
  val pluginClasses = listOf(
    "AndroidApplicationPlugin" to "android-application",
    "AndroidLibraryPlugin" to "android-library",
    "AndroidGmdPlugin" to "android-gmd",
    "JvmKotlinPlugin" to "jvm-kotlin",
    "TestJUnitPlugin" to "test-junit",
    "TestKotestPlugin" to "test-kotest",
    "AndroidTestJUnitPlugin" to "android-test-junit",
  )

  plugins {
    pluginClasses.forEach { pluginClass ->
      pluginRegister(pluginClass)
    }
  }
}

repositories {
  google()
  mavenCentral()
  gradlePluginPortal()
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
  jvmToolchain(17)
}

sourceSets {
  getByName("main").java.srcDir("src/main/kotlin")
}

dependencies {
  implementations(
    libs.gradle.android,
    libs.kotlin.gradle,
  )
}

// Pair<ClassName, PluginName>
fun NamedDomainObjectContainer<PluginDeclaration>.pluginRegister(data: Pair<String, String>) {
  val (className, pluginName) = data
  register(pluginName) {
    implementationClass = className
    id = "donedone.plugin.$pluginName"
  }
}
