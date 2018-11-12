package com.github.florent37.livedata

actual open class KLiveData<T> {

    private val foreverObservers = mutableListOf<(T) -> Unit>()
    private val foreverLiveDataObservers = mutableMapOf<KLiveData<*>, MutableList<(T) -> Unit>>()
    private val lifecycleObservers = mutableMapOf<KLifecycle, KLifecycleAndObserver<T>>()

    internal var _value : T? = null
        set(value) {
            field = value
            notifyObservers()
        }

    actual open val value: T?
        get() = _value

    fun removeObserveForever(block: (T) -> Unit){
        foreverObservers.remove(block)
    }

    actual fun observeForever(block: (T) -> Unit) {
        foreverObservers.add(block)

        value?.let {
            block(it)
        }
    }

    internal fun addLiveDataObserver(liveDataObserver: KLiveData<*>, block: (T) -> Unit) {
        var listObservers = foreverLiveDataObservers.get(liveDataObserver)
        if(listObservers == null){
            foreverLiveDataObservers[liveDataObserver] = mutableListOf(block)
        } else {
            listObservers.add(block)
        }

        foreverObservers.add(block)

        value?.let {
            block(it)
        }
    }
    internal fun removeLiveDataObserver(liveDataObserver: KLiveData<*>) {
        var listObservers = foreverLiveDataObservers.get(liveDataObserver)
        if(listObservers != null){
            listObservers.forEach{
                foreverObservers.remove(it)
            }
            listObservers.clear()
            foreverLiveDataObservers.remove(liveDataObserver)
        }
    }

    actual fun hasObservers(): Boolean {
        return !foreverObservers.isEmpty() && !lifecycleObservers.isEmpty()
    }

    actual fun observe(lifecycle: KLifecycle, block: (T) -> Unit) {
        this.addObserver(lifecycle, block)
    }

    internal fun notifyObservers(){
        value?.let { value ->
            foreverObservers.forEach {
                it(value)
            }
            lifecycleObservers.values.forEach {
                it.observers.forEach {
                    it(value)
                }
            }
        }
    }

    internal fun addObserver(lifecycle: KLifecycle, block: (T) -> Unit){
        var lifecycleAndObserver = this.lifecycleObservers.get(lifecycle)
        if(lifecycleAndObserver == null){
            lifecycleAndObserver = KLifecycleAndObserver(lifecycle)

            lifecycle.addStopObserver {
                lifecycleObservers.remove(lifecycle)
            }
        }
        lifecycleAndObserver.observers.add(block)

        value?.let {
            block(it)
        }
    }
}

actual open class KMutableLiveData<T> : KLiveData<T>() {
    actual override var value: T?
        get() = _value
        set(value) {
            _value = value
        }
}

actual open class KMediatorLiveData<T> : KMutableLiveData<T>() {
    actual fun <S> addSource(other: KLiveData<S>, block: ((S) -> Unit)) {
        other.addLiveDataObserver(this, block)
    }

    actual fun removeSource(other: KLiveData<*>) {
        other.removeLiveDataObserver(this)
    }
}