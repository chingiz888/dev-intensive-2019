package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
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
        val user3 = user2.copy(lastVisit = Date().add(8, TimeUnit.SECOND))
        val user4 = user2.copy(lastVisit = Date().add(-13, TimeUnit.DAY))

        println("""
            ${user1.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format()}
        """.trimIndent())
    }


    @Test
    fun test_of_data_mapping() {
        val user = User.makeUser("Чингиз Байшурин")
        val user_copy = user.copy(lastVisit = Date().add(-100))
        val userView = user_copy.toUserView()

        print(user)
        userView.printMe()
    }

    @Test
    fun test_of_Date_format() {
        println( Date().format()  )
        println( Date().format("HH:mm")  )
    }


    @Test
    fun test_of_Date_add() {
        println( Date().add(2, TimeUnit.SECOND)  )
    }


    @Test
    fun test_of_Message() {
        val msg = TextMessage(id = "", from = User(""), chat = Chat(""), text = "text",  date = Date())
        println(msg)
    }


    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Чингиз Байшурин")
        val msg1 = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val msg2 = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image")

        when(msg2) {
            is TextMessage -> println("this is text message")
            is ImageMessage -> println("this is image message")
            else -> print("undefined")
        }

        println("")
        println( msg1.formatMessage() )
        println("")
        println( msg2.formatMessage() )
    }


    @Test
    fun test_parse_full_name(){

        assertEquals("null null",  Utils.parseFullName(null) )
        assertEquals("null null",  Utils.parseFullName("") )
        assertEquals("null null",  Utils.parseFullName(" ") )
        assertEquals("John null",  Utils.parseFullName("John") )
    }


    @Test
    fun test_of_initials_utils_func() {

        assertEquals("JD",  Utils.toInitials("john" ,"doe") )
        assertEquals("J",  Utils.toInitials("John", null) )
        assertEquals("D",  Utils.toInitials(null, "doe") )
        assertEquals("null",  Utils.toInitials(null, null) )
        assertEquals("null",  Utils.toInitials(" ", "") )
    }

    @Test
    fun test_of_transliteration() {

        println(  Utils.transliteration("Чингиз Байшурин") )
        println(  Utils.transliteration("Натан Щарянский Самуилович", "_") )

        assertEquals( "Chingiz Baishurin", Utils.transliteration("Чингиз Байшурин") )
        assertEquals( "Ivan Stereotipov", Utils.transliteration("Иван Стереотипов") )
        assertEquals( "Amazing_Petr", Utils.transliteration("Amazing Петр", divider = "_") )


        assertEquals( "Zh Zh", Utils.transliteration("Ж Ж") )
        assertEquals( "ZhZh", Utils.transliteration("ЖЖ") )

        assertEquals( "AbrAKadabra", Utils.transliteration("AbrAKadabra") )

        assertEquals( "StraNNIi NikVash'e", Utils.transliteration("СтраННЫй НикВаще") )
    }


    @Test
    fun test_of_humanizeDiff() {
        // ----- Past -----
        assertEquals( "несколько секунд назад", Date().add(-2, TimeUnit.SECOND).humanizeDiff()  )
        assertEquals( "1 минуту назад", Date().add(-1, TimeUnit.MINUTE).humanizeDiff()  )
        assertEquals( "2 минуты назад", Date().add(-2, TimeUnit.MINUTE).humanizeDiff()  )
        assertEquals( "3 минуты назад", Date().add(-3, TimeUnit.MINUTE).humanizeDiff()  )
        assertEquals( "5 минут назад", Date().add(-5, TimeUnit.MINUTE).humanizeDiff()  )
        assertEquals( "1 час назад", Date().add(-1, TimeUnit.HOUR).humanizeDiff()  )
        assertEquals( "2 часа назад", Date().add(-2, TimeUnit.HOUR).humanizeDiff()  )
        assertEquals( "3 часа назад", Date().add(-3, TimeUnit.HOUR).humanizeDiff()  )
        assertEquals( "4 часа назад", Date().add(-4, TimeUnit.HOUR).humanizeDiff()  )
        assertEquals( "5 часов назад", Date().add(-5, TimeUnit.HOUR).humanizeDiff()  )
        assertEquals( "1 день назад", Date().add(-1, TimeUnit.DAY).humanizeDiff() )
        assertEquals( "4 дня назад", Date().add(-4, TimeUnit.DAY).humanizeDiff() )
        assertEquals( "5 дней назад", Date().add(-5, TimeUnit.DAY).humanizeDiff() )
        assertEquals( "100 дней назад", Date().add(-100, TimeUnit.DAY).humanizeDiff() )
        assertEquals( "более года назад", Date().add(-400, TimeUnit.DAY).humanizeDiff()  )

        // ----- Future ------
        assertEquals( "через несколько секунд", Date().add(2, TimeUnit.SECOND).humanizeDiff()  )
        assertEquals( "через 1 минуту", Date().add(1, TimeUnit.MINUTE).humanizeDiff()  )
        assertEquals( "через 2 минуты", Date().add(2, TimeUnit.MINUTE).humanizeDiff()  )
        assertEquals( "через 3 минуты", Date().add(3, TimeUnit.MINUTE).humanizeDiff()  )
        assertEquals( "через 5 минут", Date().add(5, TimeUnit.MINUTE).humanizeDiff()  )
        assertEquals( "через 1 час", Date().add(1, TimeUnit.HOUR).humanizeDiff()  )
        assertEquals( "через 2 часа", Date().add(2, TimeUnit.HOUR).humanizeDiff()  )
        assertEquals( "через 3 часа", Date().add(3, TimeUnit.HOUR).humanizeDiff()  )
        assertEquals( "через 4 часа", Date().add(4, TimeUnit.HOUR).humanizeDiff()  )
        assertEquals( "через 5 часов", Date().add(5, TimeUnit.HOUR).humanizeDiff()  )
        assertEquals( "через 1 день", Date().add(1, TimeUnit.DAY).humanizeDiff() )
        assertEquals( "через 4 дня", Date().add(4, TimeUnit.DAY).humanizeDiff() )
        assertEquals( "через 5 дней", Date().add(5, TimeUnit.DAY).humanizeDiff() )
        assertEquals( "через 148 дней", Date().add(148, TimeUnit.DAY).humanizeDiff() )
        assertEquals( "через год", Date().add(400, TimeUnit.DAY).humanizeDiff()  )
    }




}
