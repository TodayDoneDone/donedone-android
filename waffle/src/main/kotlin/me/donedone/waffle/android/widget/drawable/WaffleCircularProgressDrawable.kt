/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

package me.donedone.waffle.android.widget.drawable

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WaffleCircularProgressDrawable : Drawable() {
  companion object {
    const val DURATION_ANIMATION = 300L
    const val PROGRESS_WIDTH = 5f
    const val DEGREE_0 = 0f
    const val DEGREE_START = 270f
    const val DEGREE_360 = 360f
  }

  private val primaryPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    style = Paint.Style.STROKE
    strokeWidth = PROGRESS_WIDTH
    strokeJoin = Paint.Join.ROUND
    strokeCap = Paint.Cap.ROUND
    pathEffect = CornerPathEffect(PROGRESS_WIDTH)
    isDither = true
    isAntiAlias = true
  }

  private val secondPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    style = Paint.Style.STROKE
    strokeWidth = PROGRESS_WIDTH
    color = Color.GRAY
    isAntiAlias = true
  }

  private var ratio = 0f

  private var animator: ValueAnimator? = null
  private val drawBounds = RectF()
  override fun draw(canvas: Canvas) {
    canvas.drawArc(
      drawBounds,
      DEGREE_0,
      DEGREE_360,
      false,
      secondPaint,
    )

    canvas.drawArc(
      drawBounds,
      DEGREE_START,
      ratio * DEGREE_360,
      false,
      primaryPaint,
    )
  }

  override fun onBoundsChange(bounds: Rect) {
    super.onBoundsChange(bounds)
    val sw = bounds.width().coerceAtMost(bounds.height())
    val radius = sw / 2 - PROGRESS_WIDTH
    val cx = bounds.centerX().toFloat()
    val cy = bounds.centerY().toFloat()
    drawBounds.set(
      RectF(
        cx - radius,
        cy - radius,
        cx + radius,
        cy + radius,
      ),
    )
  }

  override fun setAlpha(alpha: Int) {
    primaryPaint.alpha = alpha
  }

  override fun setColorFilter(colorFilter: ColorFilter?) {
    primaryPaint.setColorFilter(colorFilter)
  }

  @Deprecated("Deprecated in Java")
  override fun getOpacity() = primaryPaint.alpha

  fun setRatio(to: Float) {
    CoroutineScope(Dispatchers.Main.immediate).launch {
      animator?.end()
      animator = ValueAnimator.ofFloat(ratio, to.coerceIn(0f, 1f))
        .setDuration(DURATION_ANIMATION)
        .apply {
          addUpdateListener {
            ratio = it.animatedValue as Float
            invalidateSelf()
          }
          start()
        }
    }
  }
}
