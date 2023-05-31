/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */
package me.donedone.waffle.android.widget

import android.content.Context
import me.donedone.waffle.android.test.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Adb command for run
 *  adb shell am start -n me.donedone.waffle.android.test/me.donedone.waffle.android.widget.WaffleButtonTestActivity
 */
class WaffleButtonTestActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_waffle_button_test)
  }

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(newBase)
  }
}