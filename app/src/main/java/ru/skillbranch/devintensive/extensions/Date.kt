package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormate = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormate.format(this)
}

fun Date.add(value: Int, units: TimeUnit = TimeUnit.SECOND): Date {

    var time = this.time

    time += when(units) {
        TimeUnit.SECOND -> value * SECOND
        TimeUnit.MINUTE -> value * MINUTE
        TimeUnit.HOUR -> value * HOUR
        TimeUnit.DAY -> value * DAY
    }
    this.time = time
    return this
}

//TODO FIX ME!!!
fun Date.humanizeDiff(): String {
    return ""
}


enum class TimeUnit {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}