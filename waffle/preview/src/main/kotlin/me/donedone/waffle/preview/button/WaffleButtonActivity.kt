/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */
package me.donedone.waffle.preview.button

import android.os.Bundle
import me.donedone.waffle.preview.WaffleSampleBaseActivity
import me.donedone.waffle.preview.annotations.WaffleSample
import me.donedone.waffle.preview.databinding.ActivityWaffleButtonBinding

@WaffleSample(title = "Waffle Button Sample")
class WaffleButtonActivity : WaffleSampleBaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityWaffleButtonBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}
