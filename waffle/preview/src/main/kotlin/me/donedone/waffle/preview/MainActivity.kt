/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

package me.donedone.waffle.preview

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.ResolveInfoFlags
import android.content.pm.ResolveInfo
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.donedone.waffle.preview.annotations.WaffleSample
import me.donedone.waffle.preview.databinding.ActivityMainBinding
import me.donedone.waffle.preview.databinding.ListItemSampleBinding

class MainActivity : AppCompatActivity() {

  private companion object {
    private const val INTENT_ACTION_SAMPLE = "me.donedone.waffle.preview.SAMPLE"
  }

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
    val queryIntentActivitiesCompat: (Intent) -> List<ResolveInfo> =
      { intent ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
          packageManager.queryIntentActivities(
            intent,
            ResolveInfoFlags.of(
              PackageManager.GET_ACTIVITIES.toLong(),
            ),
          )
        } else {
          packageManager.queryIntentActivities(intent, 0)
        }
      }

    fun String.getClassOrNull() = try {
      Class.forName(this)
    } catch (_: ClassNotFoundException) {
      null
    } catch (_: ExceptionInInitializerError) {
      null
    } catch (_: LinkageError) {
      null
    }

    return queryIntentActivitiesCompat(Intent(INTENT_ACTION_SAMPLE)).asSequence()
      .filter { resolveInfo ->
        resolveInfo.activityInfo.packageName?.startsWith(packageName) ?: false
      }.mapNotNull { resolveInfo ->
        val activityName = resolveInfo.activityInfo.name
        val cls = activityName.getClassOrNull() ?: return@mapNotNull null
        val waffleSample = cls.getAnnotation(WaffleSample::class.java) ?: return@mapNotNull null
        SampleItem(waffleSample.displayName, cls)
      }.toList()
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
