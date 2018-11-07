package com.github.florent37.log

expect class PlatformLogger() {
    var enabled: Boolean

    fun logDebug(tag: String, message: String)
    fun logError(tag: String, message: String)
    fun logError(tag: String, message: String, exception: Throwable)
}

object Logger {
    private val platformLogger = PlatformLogger()

    var enabled
        get() = platformLogger.enabled
        set(value) {
            platformLogger.enabled = value
        }

    fun d(tag: String, message: String){
        platformLogger.logDebug(tag, message)
    }

    fun e(tag: String, message: String, exception: Throwable? = null){
        exception?.let {
            platformLogger.logError(tag, message, exception)
        } ?: run {
            platformLogger.logError(tag, message)
        }
    }
}

