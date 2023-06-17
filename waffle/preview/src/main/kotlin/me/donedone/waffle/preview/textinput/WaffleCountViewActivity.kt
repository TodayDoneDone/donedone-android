/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

package me.donedone.waffle.preview.textinput

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import me.donedone.waffle.preview.annotations.WaffleSample
import me.donedone.waffle.preview.databinding.ActivityWaffleCountViewBinding

@WaffleSample(displayName = "TextLengthInfo Sample Activity")
class WaffleCountViewActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityWaffleCountViewBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.editText.doOnTextChanged { text, _, _, _ ->
      binding.countView.setValue(text?.length ?: 0)
    }
  }
}
