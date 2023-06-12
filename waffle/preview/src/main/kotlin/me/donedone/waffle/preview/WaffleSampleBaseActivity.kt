/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

package me.donedone.waffle.preview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.donedone.waffle.preview.annotations.WaffleSample

open class WaffleSampleBaseActivity : AppCompatActivity() {
  companion object {
    const val MENU_INFO = "menu_info"
  }

  private var infoMenuItem: MenuItem? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    this.javaClass.getAnnotation(WaffleSample::class.java)?.let { waffleSample ->
      title = waffleSample.displayName
    }

    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    infoMenuItem = menu.add(MENU_INFO)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == android.R.id.home) {
      finish()
      return true
    }

    if (item == infoMenuItem) {
      Toast.makeText(this, this::class.java.simpleName, Toast.LENGTH_SHORT).show()
      return true
    }
    return super.onOptionsItemSelected(item)
  }
}
