/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

package me.donedone.waffle.android.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.Button

@SuppressLint("AppCompatCustomView")
class WaffleButton @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : Button(context, attrs, defStyleAttr)
