package com.github.florent37.log

import android.util.Log

actual class PlatformLogger {
    actual var enabled : Boolean = true

    actual fun logDebug(tag: String, message: String){
        if(enabled) {
            Log.d(tag, message)
        }
    }
    actual fun logError(tag: String, message: String){
        if(enabled) {
            Log.e(tag, message)
        }
    }
    actual fun logError(tag: String, message: String, exception: Throwable){
        if(enabled) {
            Log.e(tag, message, exception)
        }
    }
}