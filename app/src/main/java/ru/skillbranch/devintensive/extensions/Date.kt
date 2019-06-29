package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val YEAR = 365 * DAY


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
        TimeUnit.YEAR -> value * YEAR
    }
    this.time = time
    return this
}


fun Date.humanizeDiff(): String {

    var delta = Date().time - this.time
    var past = true
    if (delta < 0) {
        past = false
        delta = -delta
    }

    fun plurals(i: Long, timeunit: TimeUnit): String {
        return if(i in 0..1) {
            when(timeunit) {
                TimeUnit.SECOND ->  "$i секунду"
                TimeUnit.MINUTE ->  "$i минуту"
                TimeUnit.HOUR ->  "$i час"
                TimeUnit.DAY ->  "$i день"
                TimeUnit.YEAR ->  "$i год"
            }
        } else if(i in 2..4) {
            when(timeunit) {
                TimeUnit.SECOND ->  "$i секунды"
                TimeUnit.MINUTE ->  "$i минуты"
                TimeUnit.HOUR ->  "$i часа"
                TimeUnit.DAY ->  "$i дня"
                TimeUnit.YEAR ->  "$i года"
            }
        } else {
            when(timeunit) {
                TimeUnit.SECOND ->  "$i секунд"
                TimeUnit.MINUTE ->  "$i минут"
                TimeUnit.HOUR ->  "$i часов"
                TimeUnit.DAY ->  "$i дней"
                TimeUnit.YEAR ->  "$i лет"
            }
        }
    }

    return  if (delta < SECOND * 60) {
                if(past) "несколько секунд назад"
                else "через несколько секунд"
    }
            else if (delta < MINUTE * 60) {
                val del = delta/1000/60
                if(past) plurals(del, TimeUnit.MINUTE) + " назад"
                else "через " + plurals(del, TimeUnit.MINUTE)
            }

            else if (delta < HOUR  * 24)  {
                val del = delta/1000/60/60
                if(past) plurals(del, TimeUnit.HOUR) + " назад"
                else "через " + plurals(del, TimeUnit.HOUR)
            }
            else if (delta < DAY * 365)  {
                val del = delta/1000/60/60/24
                if(past) plurals(del, TimeUnit.DAY) + " назад"
                else "через " + plurals(del, TimeUnit.DAY)
            }
            else {
                val del = delta/1000/60/60/24/365
                //                if(past) plurals(del, TimeUnit.YEAR) + " назад"
                //                else "через " + plurals(del, TimeUnit.YEAR)
                if(past) "более года назад"
                else "через год"
            }
}


enum class TimeUnit {
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    YEAR
}