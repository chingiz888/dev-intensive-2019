package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.extensions.TimeUnits

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?>{

        val parts: List<String>? = fullName?.trim()?.split(" ")
        val firstName = if (parts?.getOrNull(0) != "") parts?.getOrNull(0) else null
        val lastName = if (parts?.getOrNull(1) != "") parts?.getOrNull(1) else null

        // infix style, instead of Pair(firtsName, lastName)
        return firstName?.trim() to lastName?.trim()
    }

//    fun parseFullName(fullName: String?): String {
//
//
//        var (firstName, lastName) = parseFullNameToPair(fullName)
//
//        return "${firstName} ${lastName}"
//    }


    fun transliteration(payload: String, divider: String = " ") : String {

        // first clear provided text fragment from " and whitespaces
        val clearCharArr = strArray.toCharArray()
            .filter { i -> !i.isWhitespace() }
            .filter { i -> !i.equals('"') }

        // then make array of transliteration pairs
        val pairs = clearCharArr.joinToString("").split(",")

        // then make HashMap
        var lettersMap: MutableMap<String, String> = mutableMapOf()
        pairs.forEach{ pair ->
            var let = (pair.split(":"))
            lettersMap.put( let[0].toString(), let[1].toString()  )
        }

        // then expand our HashMap for capital letters
        for(i in 0..pairs.size-1) {
            val pair = pairs[i].split(":")
            lettersMap.put(  pair[0].toString().capitalize(),
                             pair[1].toString().capitalize() )
        }

        return payload.split("").map { str ->
            if( lettersMap.containsKey(str) ) lettersMap.get(str) else str
         }
            .joinToString("")
            .split(" ")
            .joinToString(divider)
    }

    fun toInitials(firstName: String?, lastName: String?) : String? {

        val a = firstName?.trim()?.firstOrNull()?.toUpperCase()?.toString() ?: ""
        val b = lastName?.trim()?.firstOrNull()?.toUpperCase()?.toString() ?: ""
        return if ( "$a$b".equals("") ) null else "$a$b"
    }



    fun plurals(i: Long, timeunit: TimeUnits): String {

        val j = i % 10

        return if(j in 1..1) {
            when(timeunit) {
                TimeUnits.SECOND ->  "$i секунду"
                TimeUnits.MINUTE ->  "$i минуту"
                TimeUnits.HOUR ->  "$i час"
                TimeUnits.DAY ->  "$i день"
                TimeUnits.YEAR ->  "$i год"
            }
        } else if(j in 2..4) {
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



    val strArray = """
        "а": "a",
        "б": "b",
        "в": "v",
        "г": "g",
        "д": "d",
        "е": "e",
        "ё": "e",
        "ж": "zh",
        "з": "z",
        "и": "i",
        "й": "i",
        "к": "k",
        "л": "l",
        "м": "m",
        "н": "n",
        "о": "o",
        "п": "p",
        "р": "r",
        "с": "s",
        "т": "t",
        "у": "u",
        "ф": "f",
        "х": "h",
        "ц": "c",
        "ч": "ch",
        "ш": "sh",
        "щ": "sh'",
        "ъ": "",
        "ы": "i",
        "ь": "",
        "э": "e",
        "ю": "yu",
        "я": "ya"
    """.trimIndent()
}
