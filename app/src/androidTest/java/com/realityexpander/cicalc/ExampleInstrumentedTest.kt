package com.realityexpander.cicalc

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith
import java.io.File


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
        Log.d("ExampleInstrumentedTest", "BuildConfig.APPLICATION_ID: ${BuildConfig.APPLICATION_ID}")
        assertEquals(BuildConfig.APPLICATION_ID, appContext.packageName)

//        // run bash to check value of CI env variable
//        val process = Runtime.getRuntime().exec("envman bash -c echo \$CI")
//        val isRunningCI = process.inputStream.bufferedReader().readText()
//        println("CI: $isRunningCI")
//
//        if(isRunningCI == "true") {
//            println("CI is true")
//
//            val process = Runtime.getRuntime()
//                .exec("envman add --key APPLICATION_ID --value \"${BuildConfig.APPLICATION_ID}\"")
//            val inputStream = process.inputStream
//            val result = inputStream.bufferedReader().use { it.readText() }
//            println(result)
//        }

        // force return success
        // force update
        assertEquals(4, 2 + 2)


        // Create a temp directory
        val tempDir = File(appContext.cacheDir, "/tempTestDir")
        tempDir.mkdirs()

        // Write the output to a app directory
        val file = File(appContext.cacheDir, "/tempTestDir/test.txt")
        file.writeText("Hello World!")

        println("XXX Hello World!")
        println("XXX file: ${file.absolutePath}")
    }
}

