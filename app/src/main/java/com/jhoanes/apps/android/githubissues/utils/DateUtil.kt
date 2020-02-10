package com.jhoanes.apps.android.githubissues.utils

import android.annotation.SuppressLint
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.DATE_FORMAT
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.EMPTY_STRING
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.NULL_STRING
import com.jhoanes.apps.android.githubissues.constants.Constants.Companion.PORTUGUESE
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.AM
import java.util.Calendar.PM

class DateUtil {
    companion object {

        @SuppressLint("SimpleDateFormat")
        fun convertStringToDate(date: String): Date? {
            return if (EMPTY_STRING == date ||
                NULL_STRING == date
            ) null else SimpleDateFormat(DATE_FORMAT).parse(date)
        }

        fun getDate(date: Date?): String {
            return if (date == null
            ) "" else {
                val cal = Calendar.getInstance()
                cal.time = date

                var day = cal.get(Calendar.DAY_OF_MONTH).toString()
                var month = (cal.get(Calendar.MONTH) + 1).toString()
                val year = cal.get(Calendar.YEAR)

                if (day.toInt() < 10) day = "0$day"
                if (month.toInt() < 10) month = "0$month"

                when {
                    Locale.getDefault().displayLanguage != PORTUGUESE -> "$month/$day/$year"
                    else -> "$day/$month/$year"
                }
            }
        }

        fun getHour(date: Date?): String {
            return if (date == null
            ) "" else {
                val cal = Calendar.getInstance()

                val isNotPortuguese = Locale.getDefault().displayLanguage != PORTUGUESE

                cal.time = date

                var hour =
                    if (isNotPortuguese) cal.get(Calendar.HOUR).toString() else cal.get(
                        Calendar.HOUR_OF_DAY
                    ).toString()

                var min = cal.get(Calendar.MINUTE).toString()
                var sec = cal.get(Calendar.SECOND).toString()

                if (!isNotPortuguese)
                    if (hour.toInt() < 10) hour = "0$hour"
                if (min.toInt() < 10) min = "0$min"
                if (sec.toInt() < 10) sec = "0$sec"

                val amPm = when (cal.get(Calendar.AM_PM)) {
                    0 -> AM
                    else -> PM
                }

                when {
                    isNotPortuguese -> {
                        "$hour:$min:$sec $amPm"
                    }
                    else ->
                        "$hour:$min:$sec"
                }

            }
        }
    }
}