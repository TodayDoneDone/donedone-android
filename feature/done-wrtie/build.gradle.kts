/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

@file:Suppress("INLINE_FROM_HIGHER_PLATFORM", "UnstableApiUsage")

plugins {
  donedone("android-library")
}

android {
  namespace = "me.donedone.android.done.write"

  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  implementations(
    libs.android.material,
    libs.androidx.constraintlayout,
    libs.androidx.appcompat,
  )
}
