/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

package me.donedone.waffle.android.widget

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import me.donedone.waffle.android.R
import me.donedone.waffle.android.widget.drawable.WaffleCircularProgressDrawable

class WaffleCountView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = android.R.attr.textViewStyle,
) : AppCompatTextView(
  ContextThemeWrapper(context, R.style.Waffle_CountView),
  attrs,
  defStyleAttr,
) {
  companion object {
    val DEFAULT_COLOR_RES_ALERT = R.color.color_state_alert
    val DEFAULT_COLOR_RES_NORMAL = R.color.color_base_black_01
    val DEFAULT_COLOR_RES_EMPTY = R.color.color_base_gray_01

    const val DEFAULT_MAX_COUNT = 20
    const val DP_SIZE_PROGRESS = 16f
  }

  private val maxCount: Int

  private val progressDrawable = WaffleCircularProgressDrawable().apply {
    setBounds(0, 0, DP_SIZE_PROGRESS.toPx(), DP_SIZE_PROGRESS.toPx())
  }

  private val getStatus: (Int) -> Status

  private val textWatcher: TextWatcher
  private var anchorTextView: TextView? = null

  init {
    val colorAlert: Int
    val colorEmpty: Int
    val colorNormal: Int
    val count: Int
    context.obtainStyledAttributes(attrs, R.styleable.WaffleCountView).use { ta ->
      colorAlert =
        ta.getColor(
          R.styleable.WaffleCountView_colorAlert,
          ContextCompat.getColor(context, DEFAULT_COLOR_RES_ALERT),
        )
      colorEmpty =
        ta.getColor(
          R.styleable.WaffleCountView_colorEmpty,
          ContextCompat.getColor(context, DEFAULT_COLOR_RES_EMPTY),
        )
      colorNormal =
        ta.getColor(
          R.styleable.WaffleCountView_colorNormal,
          ContextCompat.getColor(context, DEFAULT_COLOR_RES_NORMAL),
        )
      maxCount =
        ta.getInt(
          R.styleable.WaffleCountView_maxCount,
          DEFAULT_MAX_COUNT,
        )
      count =
        ta.getInt(
          R.styleable.WaffleCountView_count,
          DEFAULT_MAX_COUNT,
        )
      setCompoundDrawablesRelative(null, null, progressDrawable, null)
    }

    val statusNormal = Status(colorNormal, colorNormal, colorNormal)
    val statusAlert = Status(colorAlert, colorNormal, colorAlert)
    val statusEmpty = Status(colorEmpty, colorNormal, colorEmpty)

    textWatcher = object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

      override fun afterTextChanged(s: Editable?) {
        val length = s?.length ?: 0
        setValue(length)
      }
    }
    getStatus = { count ->
      when (count.coerceAtMost(maxCount + 1)) {
        0 -> statusEmpty
        maxCount + 1 -> statusAlert
        else -> statusNormal
      }
    }
    setValue(count)
    minHeight = DP_SIZE_PROGRESS.toPx()
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    anchorTextView?.removeTextChangedListener(textWatcher)
    anchorTextView?.addTextChangedListener(textWatcher)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    anchorTextView?.removeTextChangedListener(textWatcher)
  }

  fun setValue(count: Int) {
    val status = getStatus(count)
    setTextColor(status.textColor)

    text = status.getSpannable(count, maxCount)
    val ratio = count.toFloat() / maxCount
    progressDrawable.setRatio(ratio)
    progressDrawable.colorFilter =
      PorterDuffColorFilter(status.progressTint, PorterDuff.Mode.SRC_IN)
  }

  private fun Float.toPx(): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, resources.displayMetrics).toInt()

  class Status(
    @ColorInt val pointTextColor: Int,
    @ColorInt val textColor: Int,
    @ColorInt val progressTint: Int,
  ) {
    fun getSpannable(count: Int, maxCount: Int): CharSequence {
      val result = "$count / $maxCount"
      if (pointTextColor == textColor) {
        return result
      }
      val spannable = SpannableStringBuilder(result)
        .apply {
          setSpan(
            ForegroundColorSpan(pointTextColor),
            0,
            count.toString().length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE,
          )
        }
      return spannable
    }
  }
}
