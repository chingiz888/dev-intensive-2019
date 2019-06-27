package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.models.User
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instanciation_of_User() {
        val user1 = User("1")
        val user2 = User("2", "Tom", "Silverado")
        val user3 = User("3", "Alex", "Smith", null, lastVisit = Date(), isOnline = false)
        user1.printMe()
        user2.printMe()
        user3.printMe()

        println("\n$user1 $user2 $user3")
    }

    @Test
    fun test_of_factory() {

       val user1 = User.makeUser("John Doe")
       val user2 = User.makeUser("John")
       val user3 = User.makeUser(null)


       val userCopy = user1.copy(id = "1", lastName = "Silverado", lastVisit = Date())
       print("$user1 \n$userCopy")
    }

    @Test
    fun test_of_decomposition() {
        val user = User.makeUser("John Wick")

        fun getUserInfo() = user

        val (id, firstName, lastName) = getUserInfo()

        println("$id, $firstName, $lastName")
        println("${user.component1()}, ${user.component2()}, ${user.component3()}")
    }

    @Test
    fun test_of_copy_1() {
        val user1 = User.makeUser("John Wick")
        var user2 = user1.copy()

        if( user1 == user2 ) {
            println("equals data and hash \n${user1.hashCode()} $user1 \n${user2.hashCode()} $user2")
        } else {
            println("not equals data and hash \n${user1.hashCode()} $user1 \n${user2.hashCode()} $user2")
        }

        user2 = user1

        println("")
        if( user1 === user2 ) {
            println("equals address \n${System.identityHashCode(user1)} ${System.identityHashCode(user2)}")
        } else {
            println("not equals address \n${System.identityHashCode(user1)} ${System.identityHashCode(user2)}")
        }
    }


    @Test
    fun test_of_copy_2() {
        val user1 = User.makeUser("John Wick")
        val user2 = user1.copy(lastVisit = Date())
        val user3 = user2.copy(lastVisit = Date().add(8, "seconds"))
        val user4 = user2.copy(lastVisit = Date().add(-13, "days"))

        println("""
            ${user1.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format()}
        """.trimIndent())
    }
}
