/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */
package me.donedone.waffle.android.widget

import android.content.Context
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import me.donedone.waffle.android.widget.WaffleCountView.Companion.DEFAULT_COLOR_RES_ALERT
import me.donedone.waffle.android.widget.WaffleCountView.Companion.DEFAULT_COLOR_RES_EMPTY
import me.donedone.waffle.android.widget.WaffleCountView.Companion.DEFAULT_COLOR_RES_NORMAL
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WaffleCountViewTest {

  @get:Rule
  val activityScenarioRule = activityScenarioRule<EmptyTestActivity>()

  private lateinit var activity: EmptyTestActivity
  private lateinit var waffleCountView: WaffleCountView

  private val context: Context = InstrumentationRegistry.getInstrumentation().context

  @Before
  fun setup() {
    activityScenarioRule.scenario.onActivity {
      it.setContentView(me.donedone.waffle.android.test.R.layout.activity_waffle_count_view)
      this.activity = it
      waffleCountView = it.findViewById(me.donedone.waffle.android.test.R.id.count_view)
    }
  }

  @Test
  fun testConstructor() {
    WaffleCountView(context, null, 0)
    WaffleCountView(context, null)
    WaffleCountView(context)
  }

  @Test
  fun testConstructorWithContext1() {
    WaffleCountView(context)
    Assert.assertTrue(true)
  }

  @Test
  fun testConstructorWithContext2() {
    WaffleCountView(context, null)
    Assert.assertTrue(true)
  }

  @Test
  fun testConstructorWithContext3() {
    WaffleCountView(context, null, -1)
    Assert.assertTrue(true)
  }

  @Test
  fun testWaffleButtonInflate() {
    val countView: WaffleCountView =
      activity.findViewById(me.donedone.waffle.android.test.R.id.count_view)
    Assert.assertEquals(WaffleCountView::class.java, countView::class.java)
    Assert.assertEquals(
      ContextCompat.getColor(context, DEFAULT_COLOR_RES_NORMAL),
      countView.textColors.defaultColor,
    )
  }

  @Test
  fun testCountView_setValue_normal() {
    val testValue = 10
    val expectedText = "10 / 20"
    waffleCountView.setValue(testValue)
    onView(withId(me.donedone.waffle.android.test.R.id.count_view))
      .check { view, _ ->
        Assert.assertTrue(view is TextView)
        val textView = view as TextView
        Assert.assertEquals(expectedText, textView.text.toString())
        Assert.assertEquals(String::class.java, textView.text::class.java)
      }
  }

  @Test
  fun testCountView_setValue_empty() {
    val testValue = 0
    val expectedText = "0 / 20"
    waffleCountView.setValue(testValue)
    onView(withId(me.donedone.waffle.android.test.R.id.count_view))
      .check { view, _ ->
        Assert.assertTrue(view is TextView)
        val textView = view as TextView
        Assert.assertEquals(expectedText, textView.text.toString())
        Assert.assertEquals(SpannedString::class.java, textView.text::class.java)
        val spannedString = textView.text as SpannedString
        val spans = spannedString.getSpans(0, expectedText.length, ForegroundColorSpan::class.java)
        Assert.assertEquals(
          1,
          spans.size,
        )
        Assert.assertEquals(
          ContextCompat.getColor(context, DEFAULT_COLOR_RES_EMPTY),
          spans[0].foregroundColor,
        )
      }
  }

  @Test
  fun testCountView_setValue_alert() {
    val testValue = 21
    val expectedText = "21 / 20"
    waffleCountView.setValue(testValue)
    onView(withId(me.donedone.waffle.android.test.R.id.count_view))
      .check { view, _ ->
        Assert.assertTrue(view is TextView)
        val textView = view as TextView
        Assert.assertEquals(expectedText, textView.text.toString())
        Assert.assertEquals(SpannedString::class.java, textView.text::class.java)
        val spannedString = textView.text as SpannedString
        val spans = spannedString.getSpans(1, expectedText.length, ForegroundColorSpan::class.java)
        Assert.assertEquals(
          1,
          spans.size,
        )
        Assert.assertEquals(
          ContextCompat.getColor(context, DEFAULT_COLOR_RES_ALERT),
          spans[0].foregroundColor,
        )
      }
  }
}
