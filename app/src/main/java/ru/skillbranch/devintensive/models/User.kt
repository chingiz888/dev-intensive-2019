package ru.skillbranch.devintensive.models

import java.util.*


class User (
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
){
    var introBit: String

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id,"John", "Doe")

    init {
        introBit = getInfo()


        println("New user is alive! \n" +
                "${if(lastName=="Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName!!!" }\n")
    }

    fun printMe() = println("""
                id: $id,
                firstName: $firstName,
                lastName: $lastName,
                avatar: $avatar,
                rating: $rating,
                respect: $respect,
                lastVisit: $lastVisit,
                isOnline: $isOnline
        """.trimIndent())

    private fun getInfo() = """
        tu tu tu
        la la la
        ${"\n\n"}
        $firstName $lastName
    """.trimIndent()


    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?) : User {
            lastId++

            val parts: List<String>? = fullName?.split(" ")
            val firstName = parts?.getOrNull(0)
            val lastName: String? = parts?.getOrNull(1)


            return User(
                id = "$lastId",
                firstName = firstName,
                lastName = lastName )
        }
    }

}