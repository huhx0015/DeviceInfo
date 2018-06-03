package com.huhx0015.deviceinfo

import android.annotation.TargetApi
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

object MemoryUtils {

    // getDeviceMemorySize(): Retrieves the device's total memory capacity. Adapted from code provided by
    // Leon Lucardie at StackOverFlow:
    // http://stackoverflow.com/questions/12551547/is-there-a-way-to-get-total-device-rami-need-it-for-an-optimization
    fun getDeviceMemorySize(context: Context): Long {

        val totalDeviceMemory: Long // Used to reference the device's total memory amount.

        // Determines the device's total memory, based on the device's Android version.
        // ANDROID 4.3:
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR2) {

            val processString = "/proc/meminfo" // Location where the device's memory property file is located.
            val memoryString: String // Used to store the data read from the memory property file.
            val arrayOfString: Array<String> // Stores the lines of data read from the memory property file.

            // Reads the device's memory properties file.
            try {

                // Attempts to read from the device's memory properties file.
                val localFileReader = FileReader(processString)
                val localBufferedReader = BufferedReader(localFileReader, 8192) // Opens the file.
                memoryString = localBufferedReader.readLine() // Retrieves the memory properties from the file.
                arrayOfString = memoryString.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() // Splits the read string.

                // Retrieves the device's total memory from the read string.
                totalDeviceMemory = (Integer.valueOf(arrayOfString[1]) / 1000).toLong()
                localBufferedReader.close() // Closes the I/O file reader.
            } catch (e: IOException) {
                return -1 // Indicates that the device's memory capacity couldn't be determined.
            }
        }

        // ANDROID 4.4+:
        else {

            // Initializes the ActivityManager object for retrieving the device's memory properties.
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val memProperties = ActivityManager.MemoryInfo()

            activityManager.getMemoryInfo(memProperties) // Retrieves the device's memory properties.
            totalDeviceMemory = memProperties.totalMem / 1000 / 1000 // Retrieves the device's total memory value.
        }

        return totalDeviceMemory // Returns the device's total memory capacity.
    }

    // isLowRAMDevice(): Determines if the device is a low RAM device (512 MB or less).
    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun isLowRAMDevice(context: Context): Boolean {
        val activity = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activity.isLowRamDevice
    }
}