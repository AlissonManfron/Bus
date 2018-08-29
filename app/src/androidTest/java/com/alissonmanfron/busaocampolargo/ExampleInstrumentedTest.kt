package com.alissonmanfron.busaocampolargo

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.alissonmanfron.busaocampolargo.persistence.AppDatabase
import com.alissonmanfron.busaocampolargo.persistence.LinhaObj
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

    @Test
    fun insertLinhasTest() {

        val linhas = arrayListOf<LinhaObj>()

        val partenope = LinhaObj(null, 100, "Partênope", false,
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400")
        )

        val itaqui = LinhaObj(null, 101, "Itaqui", false,
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400"),
                arrayListOf("1514793900", "1514795400")
        )

        val populares = LinhaObj(null, 102, "Populares", false,
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
