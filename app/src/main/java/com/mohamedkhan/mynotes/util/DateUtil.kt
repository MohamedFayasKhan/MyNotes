package com.mohamedkhan.mynotes.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.mohamedkhan.mynotes.constant.Constants
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DateUtil {

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateToEpoch(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT)
        val localDateTime = LocalDateTime.parse(dateString, formatter)
        val istZoneId = ZoneId.of("Asia/Kolkata")
        val zonedDateTime = localDateTime.atZone(istZoneId)
        return zonedDateTime.toEpochSecond().toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun epochToDate(epochTime: String): String {
        val instant = Instant.ofEpochSecond(epochTime.toLong())
        val istZoneId = ZoneId.of("Asia/Kolkata")
        val zonedDateTime = ZonedDateTime.ofInstant(instant, istZoneId)
        val formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT)
        return zonedDateTime.format(formatter)
    }
}