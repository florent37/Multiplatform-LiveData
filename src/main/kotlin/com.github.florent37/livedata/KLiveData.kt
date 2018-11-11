package com.github.florent37.livedata

expect open class KLiveData<T>() {
    fun observeForever(block: (T) -> Unit)
    open val value : T?

    fun hasObservers() : Boolean

    fun observe(lifecycle: KLifecycle, block: (T) -> Unit)
}

expect open class KMutableLiveData<T>() : KLiveData<T> {
    override var value : T?
}

expect class KMediatorLiveData<T>() : KMutableLiveData<T> {
    fun <S> addSource(other: KLiveData<S>, block: ((S) -> Unit))
    fun removeSource(other: KLiveData<*>)
}