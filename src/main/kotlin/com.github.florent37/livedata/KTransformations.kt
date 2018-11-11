package com.github.florent37.livedata

object Transformations {

    fun <X, Y> map(
            source: KLiveData<X>,
            mapFunction: (X) -> Y): KLiveData<Y> {
        val result = KMediatorLiveData<Y>()
        result.addSource(source) { value ->
            result.value = mapFunction.invoke(value)
        }
        return result
    }

    fun <X, Y> switchMap(
            source: KLiveData<X>,
            switchMapFunction: (X) -> KLiveData<Y>): KLiveData<Y> {
        val result = KMediatorLiveData<Y>()

        var mSource: KLiveData<Y>? = null

        result.addSource(source) { x ->
            val newLiveData = switchMapFunction.invoke(x)
            if (mSource === newLiveData) {
                return@addSource
            }
            mSource?.let {
                result.removeSource(it)
            }
            mSource = newLiveData
            mSource?.let {
                result.addSource(it) { y ->
                    result.value = y
                }
            }
        }
        return result
    }
}
