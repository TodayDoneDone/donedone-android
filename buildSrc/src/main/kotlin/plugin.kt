/*
 * Designed and developed by DoneDone Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/TodayDoneDone/donedone-android/blob/main/LICENSE
 */

import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

@Suppress("NOTHING_TO_INLINE")
inline fun PluginDependenciesSpec.donedone(pluginId: String): PluginDependencySpec =
  id("donedone.plugin.$pluginId")
