package io.hecpay.util

import android.icu.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale


/**
 * check date in millis is today,tomorrow,or yesterday
 * if none of above return date-name
 */

fun compareAndFindDay(timeInMillis: Long): String {
    val dayInMillis = 24 * 60 * 60 * 1000 // Milliseconds in a day
    val dateFormat=SimpleDateFormat("EEEE", Locale.getDefault())
    val todayStartInMillis = System.currentTimeMillis() / dayInMillis * dayInMillis
    val yesterdayStartInMillis = todayStartInMillis - dayInMillis
    val tomorrowStartInMillis = todayStartInMillis + dayInMillis-1000
    val twoDaysAfter = tomorrowStartInMillis + dayInMillis

    return when {
        timeInMillis in todayStartInMillis..tomorrowStartInMillis -> "Today"
        timeInMillis in yesterdayStartInMillis..todayStartInMillis -> "Yesterday"
        timeInMillis in tomorrowStartInMillis..twoDaysAfter -> "tomorrow"
        else -> dateFormat.format(timeInMillis)
    }
}