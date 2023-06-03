/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

@file:Suppress("UnstableApiUsage")

plugins {
  donedone("android-application")
}

android {
  namespace = "me.donedone.waffle.preview"

  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  implementation(project(":waffle"))

  implementation(libs.appcompat)
}
