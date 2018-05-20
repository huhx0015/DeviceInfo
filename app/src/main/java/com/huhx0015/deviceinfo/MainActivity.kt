package com.huhx0015.deviceinfo

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    var apiLevel: Int = 0
    var memorySize: Long = 0
    var textureSizeLimit: Int = 0

    lateinit var deviceModel: String
    lateinit var deviceManufacturer: String
    lateinit var androidVersion: String

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
    }

    /** DATA METHODS ___________________________________________________________________________ **/

    private fun initData(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            // TODO: Restore saved data here.
        } else {
            deviceManufacturer = Build.MANUFACTURER
            deviceModel = Build.MODEL

            memorySize = MemoryUtils.getDeviceMemorySize(this)

            apiLevel = Build.VERSION.SDK_INT
            androidVersion = Build.VERSION.RELEASE

            textureSizeLimit = OpenGLUtils.maxSupportedTextureSize
        }
    }

    /** VIEW METHODS ___________________________________________________________________________ **/

    private fun initText() {

    }

    private fun setText() {
        deviceManufacturerValue.text = deviceManufacturer
        deviceModelValue.text = deviceModel
        deviceMemorySizeValue.text = memorySize.toString()
        deviceAndroidVersionValue.text = androidVersion
        deviceApiLevelValue.text = apiLevel.toString()
        deviceTextureSizeValue.text = textureSizeLimit.toString()
    }
}