package ru.skillbranch.devintensive.extensions

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

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {

    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        TimeUnits.YEAR -> value * YEAR
    }
    this.time = time
    return this
}


fun Date.humanizeDiff(): String {

    var delta = Date().time - this.time
    delta = delta/1000 * 1000
    var past = true
    if (delta < 0) {
        past = false
        delta = -delta
    }

    fun plurals(i: Long, timeunit: TimeUnits): String {
        return if(i in 0..1) {
            when(timeunit) {
                TimeUnits.SECOND ->  "$i секунду"
                TimeUnits.MINUTE ->  "$i минуту"
                TimeUnits.HOUR ->  "$i час"
                TimeUnits.DAY ->  "$i день"
                TimeUnits.YEAR ->  "$i год"
            }
        } else if(i in 2..4) {
            when(timeunit) {
                TimeUnits.SECOND ->  "$i секунды"
                TimeUnits.MINUTE ->  "$i минуты"
                TimeUnits.HOUR ->  "$i часа"
                TimeUnits.DAY ->  "$i дня"
                TimeUnits.YEAR ->  "$i года"
            }
        } else {
            when(timeunit) {
                TimeUnits.SECOND ->  "$i секунд"
                TimeUnits.MINUTE ->  "$i минут"
                TimeUnits.HOUR ->  "$i часов"
                TimeUnits.DAY ->  "$i дней"
                TimeUnits.YEAR ->  "$i лет"
            }
        }
    }
    
    return  if (delta <= SECOND * 1) {
                if(past) "только что"
                else "только что"
            }
            else if (delta <= SECOND * 45) {
                if(past) "несколько секунд назад"
                else "через несколько секунд"
            }
            else if (delta <= SECOND * 75) {
                if(past) "минуту назад"
                else "через минуту"
            }
            else if (delta <= MINUTE * 45) {
                val del = delta/1000/60
                if(past) plurals(del, TimeUnits.MINUTE) + " назад"
                else "через " + plurals(del, TimeUnits.MINUTE)
            }
            else if (delta <= MINUTE * 75)  {
                if(past) "час назад"
                else "через час"
            }
            else if (delta <= HOUR  * 22)  {
                val del = delta/1000/60/60
                if(past) plurals(del, TimeUnits.HOUR) + " назад"
                else "через " + plurals(del, TimeUnits.HOUR)
            }
            else if (delta <= HOUR  * 26)  {
                if(past) "день назад"
                else "через день"
            }
            else if (delta <= DAY * 360)  {
                val del = delta/1000/60/60/24
                if(past) plurals(del, TimeUnits.DAY) + " назад"
                else "через " + plurals(del, TimeUnits.DAY)
            }
            else {
                if(past) "более года назад"
                else "через год"
            }
}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    YEAR
}