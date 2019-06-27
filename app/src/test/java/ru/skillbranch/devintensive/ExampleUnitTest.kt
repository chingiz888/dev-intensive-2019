package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
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
    }
}
