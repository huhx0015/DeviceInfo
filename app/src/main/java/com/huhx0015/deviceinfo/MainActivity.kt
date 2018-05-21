package com.huhx0015.deviceinfo

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    // DATA VARIABLES:
    var apiLevel: Int = 0
    var memorySize: Long = 0
    var textureSizeLimit: Int = 0
    lateinit var deviceModel: String
    lateinit var deviceManufacturer: String
    lateinit var androidVersion: String

    // STATIC VARIABLES:
    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private val INSTANCE_DEVICE_MANUFACTURER = TAG + "deviceManufacturer"
        private val INSTANCE_DEVICE_MODEL = TAG + "deviceModel"
        private val INSTANCE_MEMORY_SIZE = TAG + "memorySize"
        private val INSTANCE_API_LEVEL = TAG + "apiLevel"
        private val INSTANCE_ANDROID_VERSION = TAG + "androidVersion"
        private val INSTANCE_TEXTURE_SIZE = TAG + "textureSize"
    }

    /** ACTIVITY LIFECYCLE METHODS _____________________________________________________________ **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData(savedInstanceState)
        initText()
        setText()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /** ACTIVITY EXTENSION METHODS _____________________________________________________________ **/

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putString(INSTANCE_DEVICE_MANUFACTURER, deviceManufacturer)
        outState?.putString(INSTANCE_DEVICE_MODEL, deviceModel)
        outState?.putLong(INSTANCE_MEMORY_SIZE, memorySize)
        outState?.putInt(INSTANCE_API_LEVEL, apiLevel)
        outState?.putString(INSTANCE_ANDROID_VERSION, androidVersion)
        outState?.putInt(INSTANCE_TEXTURE_SIZE, textureSizeLimit)
    }

    /** DATA METHODS ___________________________________________________________________________ **/

    // initData(): Initializes the data variables. If the Activity was destroyed and re-created, the
    // previous data instance is restored. Otherwise the data variables are initialized.
    private fun initData(savedInstanceState: Bundle?) {
        deviceManufacturer = savedInstanceState?.getString(INSTANCE_DEVICE_MANUFACTURER) ?:
                Build.MANUFACTURER
        deviceModel = savedInstanceState?.getString(INSTANCE_DEVICE_MODEL) ?: Build.MODEL
        memorySize = savedInstanceState?.getLong(INSTANCE_MEMORY_SIZE) ?:
                MemoryUtils.getDeviceMemorySize(this)
        apiLevel = savedInstanceState?.getInt(INSTANCE_API_LEVEL) ?: Build.VERSION.SDK_INT
        androidVersion = savedInstanceState?.getString(INSTANCE_ANDROID_VERSION) ?:
                Build.VERSION.RELEASE
        textureSizeLimit = savedInstanceState?.getInt(INSTANCE_TEXTURE_SIZE) ?:
                OpenGLUtils.maxSupportedTextureSize
    }

    /** VIEW METHODS ___________________________________________________________________________ **/

    private fun initText() {
        // TODO: Initialize text properties here.
    }

    // setText(): Sets the data field TextView texts.
    private fun setText() {
        deviceManufacturerValue.text = deviceManufacturer
        deviceModelValue.text = deviceModel
        deviceMemorySizeValue.text = memorySize.toString()
        deviceAndroidVersionValue.text = androidVersion
        deviceApiLevelValue.text = apiLevel.toString()
        deviceTextureSizeValue.text = textureSizeLimit.toString()
    }
}