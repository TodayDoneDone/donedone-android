/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

package me.donedone.android

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.LinearLayout
import android.widget.TextView
import me.donedone.waffle.android.widget.WaffleButton

class MainActivity : Activity() {
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
            ContextThemeWrapper(
              context,
              android.R.style.Widget_Button,
            ),
          )
            .apply {
              layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
              )
              text = "WaffleButton"
            },
        )
      },
    )
  }
}
