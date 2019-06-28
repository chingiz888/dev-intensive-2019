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

    val delta = Date().time - this.time


    return  if (delta < SECOND * 60) { "несколько секунд назад" }
            else if (delta < MINUTE * 60) {

                val del = delta/1000/60
                when (del) {
                    in 0..1 -> "${del} минуту назад"
                    in 1..4 -> "${del} минуты назад"
                    else -> "${del} минут назад"
                }
            }

            else if (delta < HOUR  * 24)  {
                val del = delta/1000/60/60
                when (del) {
                    in 0..1 -> "${del} час назад"
                    in 1..4 -> "${del} часа назад"
                    else -> "${del} часов назад"
                }

            }
            else if (delta < DAY * 365)  {
                val del = delta/1000/60/60/24
                when (del) {
                    in 0..1 -> "${del} день назад"
                    in 1..4 -> "${del} дня назад"
                    else -> "${del} дней назад"
                }

            }
            else "давно"
}


enum class TimeUnit {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}