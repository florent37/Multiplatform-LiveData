package com.github.florent37.livedata

class KLifecycle {

    private val lifecycleStopObservers = mutableListOf<() -> Unit>()

    var isStarted : Boolean = false
        get
        private set

    fun start(){
        isStarted = true
    }
    fun stop() {
        isStarted = false

        notifyObserversStop()
    }

    fun addStopObserver(block: () -> Unit){
        lifecycleStopObservers.add(block)
    }

    fun notifyObserversStop(){
        lifecycleStopObservers.forEach {
            it()
        }
        lifecycleStopObservers.clear()
    }
}

class KLifecycleAndObserver<T>(val lifecycle: KLifecycle){

    val observers = mutableListOf<(T) -> Unit>()

    init {
        lifecycle.addStopObserver {
            observers.clear()
        }
    }

}