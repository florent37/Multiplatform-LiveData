package com.github.florent37.log

actual class PlatformLogger {
    actual var enabled = true

    actual fun logDebug(tag: String, message: String){
        if(enabled) {
            print("${tag} : ${message}")
        }
    }
    actual fun logError(tag: String, message: String){
        if(enabled) {
            println("${tag} : ${message}")
        }
    }
    actual fun logError(tag: String, message: String, exception: Throwable){
        if(enabled) {
            println("${tag} : ${message}")
            println(exception)
        }
    }
}