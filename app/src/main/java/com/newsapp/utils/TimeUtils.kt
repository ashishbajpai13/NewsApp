package com.newsapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class TimeUtils {
    fun timeAgo(dateTime: String?): String {
        val timeInMillis = convertDateToMillis(convertUtcToLocal(dateTime))
        val cur_time = Calendar.getInstance().timeInMillis / 1000
        val time_elapsed = cur_time - timeInMillis / 1000
        val minutes = Math.round((time_elapsed / 60).toFloat())
        val hours = Math.round((time_elapsed / 3600).toFloat())
        val days = Math.round((time_elapsed / 86400).toFloat())
        val weeks = Math.round((time_elapsed / 604800).toFloat())
        val months = Math.round((time_elapsed / 2600640).toFloat())
        val years = Math.round((time_elapsed / 31207680).toFloat())

        // Seconds
        return if (time_elapsed <= 60) {
            "just now"
        } else if (minutes <= 60) {
            if (minutes == 1) {
                "1 minute ago"
            } else {
                "$minutes minutes ago"
            }
        } else if (hours <= 24) {
            if (hours == 1) {
                "an hour ago"
            } else {
                "$hours hrs ago"
            }
        } else if (days <= 7) {
            if (days == 1) {
                "yesterday"
            } else {
                "$days days ago"
            }
        } else if (weeks <= 4.3) {
            if (weeks == 1) {
                "a week ago"
            } else {
                "$weeks weeks ago"
            }
        } else if (months <= 12) {
            if (months == 1) {
                "a month ago"
            } else {
                "$months months ago"
            }
        } else {
            if (years == 1) {
                "1 year ago"
            } else {
                "$years years ago"
            }
        }
    }

    private fun convertDateToMillis(dateTime: String): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        var date: Date? = null
        try {
            date = sdf.parse(dateTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date!!.time
    }

    private fun convertUtcToLocal(dateTime: String?): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        var date: Date? = null
        try {
            date = sdf.parse(dateTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    fun convertSDF(dateTime: String?): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outFormat = SimpleDateFormat("MMMM dd, yyyy hh:mm a", Locale.getDefault())
        return outFormat.format(sdf.parse(dateTime))
    }
}

