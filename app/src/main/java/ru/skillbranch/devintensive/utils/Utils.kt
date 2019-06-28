package ru.skillbranch.devintensive.utils

import com.google.gson.Gson
import ru.skillbranch.devintensive.models.Letters

object Utils {

    fun parseFullNameToPair(fullName: String?): Pair<String?, String?>{

        val parts: List<String>? = fullName?.split(" ")
        val firstName = if (parts?.getOrNull(0) != "") parts?.getOrNull(0) else null
        val lastName = if (parts?.getOrNull(1) != "") parts?.getOrNull(1) else null

        // infix style, instead of Pair(firtsName, lastName)
        return firstName?.trim() to lastName?.trim()
    }

    fun parseFullName(fullName: String?): String {


        var (firstName, lastName) = parseFullNameToPair(fullName)

        return "${firstName} ${lastName}"
    }


    fun transliteration(payload: String, divider: String = " ") : String {

        val clearCharArr = Letters.strArray.toCharArray()
            .filter { i -> !i.isWhitespace() }
            .filter { i -> !i.equals('"') }
        val pairs = clearCharArr.joinToString("").split(",")


        var lettersMap: MutableMap<String, String> = mutableMapOf()

        pairs.forEach{ pair ->
            var let = (pair.split(":"))
            lettersMap.put( let[0].toString(), let[1].toString()  )
        }


        var trans = payload.split("").map { str ->
            if( lettersMap.containsKey(str.toLowerCase()) ) lettersMap.get(str.toLowerCase()) else str
         }


        return trans.joinToString("").split(" ").map { word -> word.capitalize() }
            .joinToString(divider)
    }

    fun toInitials(firstName: String?, lastName: String?) : String {

        val a = firstName?.trim()?.firstOrNull()?.toUpperCase()?.toString() ?: ""
        val b = lastName?.trim()?.firstOrNull()?.toUpperCase()?.toString() ?: ""
        return if ( "$a$b".equals("") ) "null" else "$a$b"
    }
}
