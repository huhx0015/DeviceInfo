package com.huhx0015.deviceinfo

import javax.microedition.khronos.egl.EGL10
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.egl.EGLContext

object OpenGLUtils {

    // getMaxSupportedTextureSize(): Retrieves the maximum texture size supported by the device's
    // GPU. This function is based on the getMaximumTextureSize method provided by Zenya:
    // http://stackoverflow.com/questions/11022109/get-maximum-opengl-es-2-0-texture-size-limit-in-android?lq=1
    val maxSupportedTextureSize: Int
        get() {

            val egl = EGLContext.getEGL() as EGL10
            val display = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY)
            val version = IntArray(2)

            // Initializes the OpenGL sub-system.
            egl.eglInitialize(display, version)

            // Queries the total number of configurations.
            val totalConfigurations = IntArray(1)
            egl.eglGetConfigs(display, null, 0, totalConfigurations)
            val configurationsList = arrayOfNulls<EGLConfig>(totalConfigurations[0])
            egl.eglGetConfigs(display, configurationsList, totalConfigurations[0], totalConfigurations)

            // Iterates through all the configurations to locate the maximum texture size for the device.
            // Checks only for the width since OpenGL textures are always square.
            // Keeps track of the maximum texture size.
            val textureSize = IntArray(1)
            var maximumTextureSize = 0
            var i = 0
            for (x in IntArray(totalConfigurations[0])) {
                egl.eglGetConfigAttrib(display, configurationsList[i], EGL10.EGL_MAX_PBUFFER_WIDTH,
                        textureSize)
                if (maximumTextureSize < textureSize[0]) {
                    maximumTextureSize = textureSize[0]
                }
                i++
            }

            // Releases the OpenGL sub-system.
            egl.eglTerminate(display)

            return maximumTextureSize
        }
}