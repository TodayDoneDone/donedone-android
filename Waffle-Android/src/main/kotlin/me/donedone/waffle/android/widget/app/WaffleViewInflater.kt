/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

package me.donedone.waffle.android.widget.app

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatViewInflater
import androidx.appcompat.widget.AppCompatButton
import me.donedone.waffle.android.widget.WaffleButton

class WaffleViewInflater : AppCompatViewInflater() {

  override fun createButton(context: Context, attrs: AttributeSet?): AppCompatButton {
    return WaffleButton(context, attrs)
  }

  override fun createView(context: Context, name: String, attrs: AttributeSet): View? {
    return super.createView(context, name, attrs)
  }
}
