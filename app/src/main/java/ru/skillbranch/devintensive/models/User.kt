package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*


data class User (
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

            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(
                id = "$lastId",
                firstName = firstName,
                lastName = lastName )
        }
    }



    data class Builder(
        var id: String = "0",
        var firstName: String? = null,
        var lastName: String? = null,
        var avatar: String? = null,
        var rating: Int = 0,
        var respect: Int = 0,
        var lastVisit: Date? = null,
        var isOnline: Boolean = false) {

        fun id(value: String) = apply { this.id = value }
        fun firstName(value: String) = apply { this.firstName = value }
        fun lastName(value: String) = apply { this.lastName = value }
        fun avatar(value: String) = apply { this.avatar = value }
        fun rating(value: Int) = apply { this.rating = value }
        fun respect(value: Int) = apply { this.respect = value }
        fun lastVisit(value: Date) = apply { this.lastVisit = value }
        fun isOnline(value: Boolean) = apply { this.isOnline = value }
        fun build() = User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
    }

}


//
//Реализуй паттерн Builder для класса User.
//User.Builder().id(s)
//.firstName(s)
//.lastName(s)
//.avatar(s)
//.rating(n)
//.respect(n)
//.lastVisit(d)
//.isOnline(b)
//.build() должен вернуть объект User