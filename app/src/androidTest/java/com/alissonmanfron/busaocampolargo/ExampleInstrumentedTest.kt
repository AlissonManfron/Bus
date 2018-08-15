package com.alissonmanfron.busaocampolargo

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.alissonmanfron.busaocampolargo.persistence.AppDatabase
import com.alissonmanfron.busaocampolargo.persistence.LinhaObj
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
        database = AppDatabase.getInstance(appContext!!)
    }

    @Test
    fun useAppContext() {
        assertEquals("com.alissonmanfron.busaocampolargo", appContext?.packageName)
    }

    @Test
    fun databaseTest() {
        val l = LinhaObj(null, 101, "Itaqui", false, arrayListOf("1534375279", "1534375279"),
                arrayListOf("1534375279", "1534375279"))

        database?.linhaDao()?.insert(l)

        assertNotNull(database?.linhaDao()?.gelAll())
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
