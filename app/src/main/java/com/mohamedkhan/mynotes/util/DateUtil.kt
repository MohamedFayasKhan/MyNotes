package com.mohamedkhan.mynotes.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.mohamedkhan.mynotes.constant.Constants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

public class DateUtil {

    companion object {

        fun dateToEpoch(dateString: String): String {
            val formatter = SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.getDefault())
            formatter.timeZone = TimeZone.getTimeZone("Asia/Kolkata") // Set time zone to IST
            val date = formatter.parse(dateString)
            return date.time.toString()
        }


        fun epochToDate(epochTime: String): String {
            val date = Date(epochTime.toLong())
            val formatter = SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.getDefault())
            formatter.timeZone = TimeZone.getTimeZone("Asia/Kolkata")
            return formatter.format(date)
        }

        fun getCurrentFormattedTime(): String {
            val currentTimeMillis = System.currentTimeMillis()
            val date = Date(currentTimeMillis)
            val formatter = SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.getDefault())
            formatter.timeZone = TimeZone.getTimeZone("Asia/Kolkata")
            return formatter.format(date)
        }
    }
}