package com.huhx0015.deviceinfo

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    var textureSizeLimit: Int = 0
    var memorySize: Long = 0
    lateinit var deviceModel: String
    lateinit var deviceManufacturer: String
    var apiLevel: Int = 0
    lateinit var androidVersion: String

    /** ACTIVITY LIFECYCLE METHODS _____________________________________________________________ **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
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

    private fun initData() {
        deviceManufacturer = Build.MANUFACTURER
        deviceModel = Build.MODEL

        memorySize = MemoryUtils.getDeviceMemorySize(this)

        apiLevel = Build.VERSION.SDK_INT
        androidVersion = Build.VERSION.RELEASE

        textureSizeLimit = OpenGLUtils.maxSupportedTextureSize
    }

    /** VIEW METHODS ___________________________________________________________________________ **/

    private fun initText() {

    }

    private fun setText() {
        deviceManufacturerText.text = deviceManufacturer
        deviceModelText.text = deviceModel
        deviceMemorySizeText.text = memorySize.toString()
        deviceAndroidVersionText.text = androidVersion
        deviceApiLevelText.text = apiLevel.toString()
        deviceTextureSizeText.text = textureSizeLimit.toString()
    }
}