/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

package me.donedone.waffle.preview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dalvik.system.DexFile
import me.donedone.waffle.preview.annotations.WaffleSample
import me.donedone.waffle.preview.databinding.ActivityMainBinding
import me.donedone.waffle.preview.databinding.ListItemSampleBinding

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    getSampleItemList().forEach {
      val item = ListItemSampleBinding.inflate(layoutInflater)
      item.viewModel = it.toViewModel(this)
      binding.container.addView(item.root)
    }
  }

  private fun getSampleItemList(): List<SampleItem> {
    val df = DexFile(packageCodePath)
    return df.entries().toList()
      .filter { it.contains(this.packageName) }
      .mapNotNull {
        try {
          Class.forName(it)
        } catch (_: ClassNotFoundException) {
          null
        } catch (_: ExceptionInInitializerError) {
          null
        } catch (_: LinkageError) {
          null
        }
      }.mapNotNull {
        it.getAnnotation(WaffleSample::class.java)?.run {
          this to it
        }
      }.map {
        SampleItem(it.first.title, it.second)
      }
  }

  private fun SampleItem.toViewModel(context: Context): SampleViewModel {
    return SampleViewModel(
      title = title,
      action = {
        startActivity(Intent(context, cls))
      },
    )
  }

  data class SampleItem(val title: String, val cls: Class<*>)

  data class SampleViewModel(val title: String, val action: Runnable)
}
