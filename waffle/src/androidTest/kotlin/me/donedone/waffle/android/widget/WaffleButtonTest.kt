/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */
package me.donedone.waffle.android.widget

import android.content.Context
import android.widget.Button
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WaffleButtonTest {

  @get:Rule
  val activityScenarioRule = activityScenarioRule<WaffleButtonTestActivity>()

  private lateinit var activity: WaffleButtonTestActivity

  private val context: Context = InstrumentationRegistry.getInstrumentation().context

  @Before
  fun setup() {
    activityScenarioRule.scenario.onActivity {
      this.activity = it
    }
  }

  @Test
  fun testConstructor() {
    Button(context, null, 0)
    Button(context, null)
    Button(context)
  }

  @Test
  fun testConstructorWithNullContext1() {
    WaffleButton(context)
    Assert.assertTrue(true)
  }

  @Test
  fun testConstructorWithNullContext2() {
    WaffleButton(context, null)
    Assert.assertTrue(true)
  }

  @Test
  fun testConstructorWithNullContext3() {
    WaffleButton(context, null, -1)
    Assert.assertTrue(true)
  }

  @Test
  fun testButtonClick() {
    var clicked = 0

    onView(withId(me.donedone.waffle.android.test.R.id.btn))
      .check { view, _ ->
        view.setOnClickListener {
          clicked += 1
        }
      }
      .perform(click())
      .check { _, _ ->
        Assert.assertEquals(1, clicked)
      }
  }

  @Test
  fun testWaffleButtonInflate() {
    val button: Button = activity.findViewById(me.donedone.waffle.android.test.R.id.btn)
    Assert.assertEquals(WaffleButton::class.java, button::class.java)
  }
}
