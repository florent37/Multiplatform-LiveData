package com.github.florent37.livedata

import androidx.lifecycle.*

private val lifeCycleOwnerTag = "LifecycleOwner"

actual open class KLiveData<T> {

    internal val liveData = MediatorLiveData<T>()
    actual open val value: T?
        get() = liveData.value

    actual fun observeForever(block: (T) -> Unit) {
        liveData.observeForever {
            block(it)
        }
    }

    actual fun hasObservers(): Boolean {
        return liveData.hasObservers()
    }

    actual fun observe(lifecycle: KLifecycle, block: (T) -> Unit) {
        val realLifecycleOwner = lifecycle.tags[lifeCycleOwnerTag]
        if (realLifecycleOwner == null || realLifecycleOwner !is LifecycleOwner) {
            throw Exception("Please use LifecycleOwner.kLifecycle() to create your lifecycle, or KLiveData.observe(LifecycleOwner, (T) -> Unit) on Android Platform")
        } else {
            lifecycle.tags.remove(lifeCycleOwnerTag)
            observe(realLifecycleOwner, block)
        }
    }
}

actual open class KMutableLiveData<T> : KLiveData<T>() {
    actual override var value: T?
        get() = liveData.value
        set(value) {
            liveData.postValue(value)
        }
}

actual open class KMediatorLiveData<T> : KMutableLiveData<T>() {
    actual fun <S> addSource(other: KLiveData<S>, block: ((S) -> Unit)) {
        liveData.addSource(other.liveData) {
            block(it)
        }
    }

    actual fun removeSource(other: KLiveData<*>) {
        liveData.removeSource(other.liveData)
    }
}

fun <T> KLiveData<T>.observe(lifecycle: LifecycleOwner, block: (T) -> Unit) {
    this.liveData.observe(lifecycle, Observer<T> {
        block(it)
    })
}

fun LifecycleOwner.kLifecycle(): KLifecycle {
    val kLifecycle = KLifecycle()
    kLifecycle.tags[lifeCycleOwnerTag] = this
    return kLifecycle
}

val <T> KLiveData<T>.toLivedata: LiveData<T>
    get() = this.liveData

val <T> KMutableLiveData<T>.toMutableLiveData: MutableLiveData<T>
    get() = this.liveData

val <T> KMediatorLiveData<T>.toMediatorLivedata: MediatorLiveData<T>
    get() = this.liveData

val <T> LiveData<T>.toKLivedata: KLiveData<T>
    get() = this.transformToKLiveData()

val <T> MutableLiveData<T>.toKMutableLiveData: KMutableLiveData<T>
    get() = this.transformToKLiveData()

val <T> MediatorLiveData<T>.toKMediatorLivedata: KMediatorLiveData<T>
    get() = this.transformToKLiveData()

private fun <X> LiveData<X>.transformToKLiveData(): KMediatorLiveData<X> {
    val result = KMediatorLiveData<X>()

    observeForever{
        result.value = it
    }

    return result
}