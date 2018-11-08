package com.github.florent37.livedata

expect open class KLiveData<T>() {
    fun observeForever(block: (T) -> Unit)
    open val value : T?

    fun hasObservers() : Boolean

    fun observe(lifecycle: KLifecycle, block: (T) -> Unit)
}

expect open class KMutableLiveData<T>() : com.github.florent37.livedata.KLiveData<T> {
    override var value : T?
}

expect class KMediatorLiveData<T>() : com.github.florent37.livedata.KMutableLiveData<T> {
    actual fun <S> addSource(other: com.github.florent37.livedata.KLiveData<S>, block: ((S) -> Unit))
}