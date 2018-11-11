# Multiplatform LiveData

Réimplémentation of android LiveDatas on kotlin-multiplatform

It wraps reals livedatas on Android, and uses an Observable-Pattern on iOS

It works exactly the same as Android LiveDatas : https://developer.android.com/topic/libraries/architecture/livedata

`KLiveData<T>` Read only observable
`KMutableLiveData<T>` Read / Write observable
`KMediatorLiveData<T>` Read / Write observable, capable of listen `KLiveData`

Transformations like `map` and `switchmap` are available too

```kotlin
class MainViewModel(val premiumManager: PremiumManager) {
    private val _viewState = KMediatorLiveData<ViewState>()
    
    val viewState = KLiveData<ViewState>()
        get() = _viewState

    init {
        _viewState.value = ViewState("not premium")

        _viewState.addSource(premiumManager.premium()) {
            if(it) {
                _viewState.value = ViewState("premium")
            } else {
                _viewState.value = ViewState("not premium")
            }
        }
    }
}
```

```kotlin
class ViewState(
    val userStatus: String
)
```

```kotlin
class PremiumManager {
    private val premium = KMutableLiveData<Boolean>()
    fun premium() : KLiveData<Boolean> {
        return premium
    }

    fun becomePremium() {
        premium.value = true
    }

    init {
        //default value
        premium.value = false
    }
}
```


# Download

Add the repository
```groovy
repositories {
    maven { url  "https://dl.bintray.com/florent37/maven" }
}
```

## common
```groovy
implementation "com.gitub.florent37:multiplatform-livedata:0.0.4"
```

## ios

Uses a kotlin reimplementation of livedatas

```groovy
implementation "com.gitub.florent37:multiplatform-livedata-ios:0.0.4"
```

## android

Uses inside the jetpack's LiveDatas

```groovy
implementation "com.gitub.florent37:multiplatform-livedata-android:0.0.4"
```

You can retrieve the real livedatas stored inside :
```
KLiveData<T>.toLivedata : LiveData<T>

KMutableLiveData<T>.toMutableLiveData : MutableLiveData<T>

KMediatorLiveData<T>.toMediatorLivedata : MediatorLivedata<T>
```

And create KLiveDatas from jetpacks LiveDatas

```
LiveData<T>.toKLivedata: KLiveData<T>

MutableLiveData<T>.toKMutableLiveData: KMutableLiveData<T>

MediatorLiveData<T>.toKMediatorLivedata: KMediatorLiveData<T>
```
 
## License
        
    Copyright 2018 Florent37
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.