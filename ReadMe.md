# Multiplatform LiveData

Réimplémentation of android LiveDatas on kotlin-multiplatform

It wraps reals livedatas on Android, and uses an Observable-Pattern on iOS

It works exactly the same as Android LiveDatas : https://developer.android.com/topic/libraries/architecture/livedata

`KLiveData<T>` Read only observable
`KMutableLiveData<T>` Read / Write observable
`KMediatorLiveData<T>` Read / Write observable, capable of listen `KLiveData`

```kotlin
class ViewState(
    val userStatus: String
)
```

```kotlin
class MainViewModel(val premiumManager: PremiumManager) {
    private val viewState = KMediatorLiveData<ViewState>()

    init {
        viewState.value = ViewState("not premium")

        viewState.addSource(premiumManager.premium()) {
            if(it) {
                viewState.value = ViewState("premium")
            } else {
                viewState.value = ViewState("not premium")
            }
        }
    }

    fun viewState() : KLiveData<ViewState> {
        return viewState
    }

    fun becomePremium() {
   
        premiumManager.becomePremium()
    }

}
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
implementation "com.gitub.florent37:multiplatform-livedata:1.0.0"
```

## ios

Uses inside the NSUserDefaults

```groovy
implementation "com.gitub.florent37:multiplatform-livedata-ios:1.0.0"
```

## android

Uses inside the SharedPreferences

```groovy
implementation "com.gitub.florent37:multiplatform-livedata-android:1.0.0"
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