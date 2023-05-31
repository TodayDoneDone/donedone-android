/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

package me.donedone.waffle.android.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.withStyledAttributes
import me.donedone.waffle.android.R

class WaffleButton @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
) : AppCompatButton(context.applyWaffleStyle(attrs), attrs, defStyleAttr) {

  constructor(context: Context, viewStyle: WaffleViewStyle) : this(ContextThemeWrapper(context, viewStyle.styleResId))

  companion object {

    private const val NONE = -1

    private fun Context.applyWaffleStyle(attrs: AttributeSet?): Context {
      var styleRes: Int? = null
      withStyledAttributes(
        set = attrs,
        attrs = R.styleable.WaffleButton,
        defStyleAttr = androidx.appcompat.R.attr.buttonStyle,
        defStyleRes = R.style.Waffle_Button,
      ) {
        val viewStyle = getInt(R.styleable.WaffleButton_waffleButtonStyle, if (attrs != null) 0 else NONE)
        if (viewStyle != NONE) {
          styleRes = WaffleViewStyle.getWaffleViewStyle(viewStyle).styleResId
        }
      }
      return styleRes?.let { ContextThemeWrapper(this, it) } ?: this
    }
  }

  sealed class WaffleViewStyle(val styleResId: Int) {

    companion object {
      private val styles = listOf(DefaultViewStyle, PrimaryViewStyle, DoneViewStyle)
      fun getWaffleViewStyle(viewStyle: Int): WaffleViewStyle = styles[viewStyle]
    }
  }

  object DefaultViewStyle : WaffleViewStyle(R.style.Waffle_Button_Default)
  object PrimaryViewStyle : WaffleViewStyle(R.style.Waffle_Button_Primary)
  object DoneViewStyle : WaffleViewStyle(R.style.Waffle_Button_Done)
}
