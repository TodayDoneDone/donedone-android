/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */
package me.donedone.waffle.preview.button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.donedone.waffle.preview.annotations.WaffleSample
import me.donedone.waffle.preview.databinding.ActivityWaffleButtonBinding

@WaffleSample(title = "Button Sample Activity")
class WaffleButtonActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityWaffleButtonBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}
