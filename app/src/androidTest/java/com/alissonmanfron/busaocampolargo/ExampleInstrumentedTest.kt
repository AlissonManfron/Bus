package com.alissonmanfron.busaocampolargo

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.alissonmanfron.busaocampolargo.extensions.stringToTimeStampList
import com.alissonmanfron.busaocampolargo.model.Linha
import com.alissonmanfron.busaocampolargo.persistence.AppDatabase
import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    var database: AppDatabase? = null
    var appContext: Context? = null

    @Before
    fun setUp() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getTargetContext()
        database = AppDatabase.getInstance()
        database?.linhaDao()?.deleteAll()
    }

    //@Test
    fun testHour() {

        val hours = "04:40 05:00 05:25 05:45 06:05 06:30 06:50 07:15 07:40 08:00 08:30 08:55\n" +
                "09:20 09:45 10:10 10:35 11:00 11:25 11:42 11:59 12:16 12:33 12:50 13:07\n" +
                "13:24 13:41 14:06 14:31 14:56 15:21 15:46 16:12 16:38 17:05 17:27 17:44\n" +
                "18:01 18:18 18:36 18:55 19:12 19:29 19:48 20:18 20:48 21:18 21:48 22:18\n" +
                "22:48 23:25"

        val list = hours.stringToTimeStampList()

        println(list)
        println(list.size)
        timeStampToString(list)
    }

    fun timeStampToString(list: List<String>) {

        for (l in list) {
            val dt = DateTime(l.toLong())
            println(String.format("%s:%s", dt.hourOfDay().get(), if (dt.minuteOfHour().get().toString().length == 2) dt.minuteOfHour().get() else "0" + dt.minuteOfHour().get()))
        }
    }

    @Test
    fun insertLinhasTest() {

        val linhas = arrayListOf<Linha>()

        val partenope = Linha(null, 100, "Partênope", false,
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400")
        )

        val itaqui = Linha(null, 101, "Itaqui", false,
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400")
        )

        val populares = Linha(null, 102, "Populares", false,
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400")
        )

        linhas.add(partenope)
        linhas.add(itaqui)
        linhas.add(populares)

        for (l in linhas) {
            database?.linhaDao()?.insert(l)
        }

        database?.linhaDao()?.gelAll()?.subscribe({
            println("SIZE : ${it.size}")
            assertEquals(linhas.size, it.size)
        })
    }

//    @Test
//    fun convertersTestToString() {
//        val conv = Converters()
//
//        val array = arrayListOf(17,20)
//
//        val test = conv.intArrayToString(array)
//
//        println(test)
//    }
//
//    @Test
//    fun convertersTestToInt() {
//        val conv = Converters()
//
//        val array = "16,30"
//
//        val test = conv.stringToIntArray(array)
//
//        println(test)
//    }
}
