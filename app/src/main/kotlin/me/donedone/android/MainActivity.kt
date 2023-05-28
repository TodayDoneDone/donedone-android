/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

package me.donedone.android

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import me.donedone.waffle.android.widget.WaffleButton

class MainActivity : AppCompatActivity() {
  companion object {
    const val TEN_SECOND = 10000L
  }

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(
      LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        addView(
          TextView(context)
            .apply {
              text = "Hello, World!"
            },
        )
        addView(
          WaffleButton(
            context,
            WaffleButton.PrimaryViewStyle,
          )
            .apply {
              layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
              )
              text = "WaffleButton Primary"
            },
        )
        addView(
          WaffleButton(
            context,
            WaffleButton.DefaultViewStyle,
          )
            .apply {
              layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
              )
              text = "WaffleButton Default"
              setOnClickListener {
                it.isEnabled = false
                Handler(Looper.getMainLooper()).postDelayed(
                  {
                    it.isEnabled = true
                  },
                  TEN_SECOND,
                )
              }
            },
        )
        addView(
          WaffleButton(
            context,
            WaffleButton.DoneViewStyle,
          )
            .apply {
              layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
              )
              text = "WaffleButton Done"
            },
        )
      },
    )
  }
}
