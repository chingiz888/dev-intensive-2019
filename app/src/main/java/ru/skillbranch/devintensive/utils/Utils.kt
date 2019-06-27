package ru.skillbranch.devintensive.utils

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

    //TODO FIX ME!!!
    fun transliteration(payload: String, divider: String = " ") : String {
        return ""
    }

    //TODO FIX ME!!!
    fun toInitials(firstName: String?, lastName: String?) : String? {
        return null
    }
}