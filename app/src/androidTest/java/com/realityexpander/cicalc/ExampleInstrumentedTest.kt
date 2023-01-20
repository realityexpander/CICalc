package com.realityexpander.cicalc

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith
import java.util.logging.Logger


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        println("BuildConfig.APPLICATION_ID: ${BuildConfig.APPLICATION_ID}")
        Log.i("HELLO", "useAppContext Log: BuildConfig.APPLICATION_ID: ${BuildConfig.APPLICATION_ID}")
        Logger.getGlobal().info("useAppContext Logger: BuildConfig.APPLICATION_ID: ${BuildConfig.APPLICATION_ID}")

        assertEquals(BuildConfig.APPLICATION_ID, appContext.packageName)

        //  run a bash command
        val process = Runtime.getRuntime().exec("export MYVAR2=\"goodbye\"")
        process.waitFor()
        val output = process.inputStream.bufferedReader().readText()
        println("output: $output")
    }
}