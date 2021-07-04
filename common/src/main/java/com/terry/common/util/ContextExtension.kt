package com.terry.common.util

import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf

/*
 * Created by Taehyung Kim on 2021-07-04
 */
fun Context.createIntent(
    className: String,
    vararg extras: Pair<String, Any?>,
) = runCatching {
    Intent(this, Class.forName(className)).apply {
        putExtras(bundleOf(*extras))
    }
}.onFailure {
    it.printStackTrace()
}.getOrNull()

fun Context.startActivity(
    className: String,
    vararg extras: Pair<String, Any?>,
) {
    runCatching {
        startActivity(createIntent(className, *extras))
    }.onFailure {
        it.printStackTrace()
    }
}
