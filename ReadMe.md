# Multiplatform Preferences

Use a single object : `Logger` in your kotlin shared projects to display logs

Note you can also use it in your real code on Android & iOs

Compatible with kotlin android and kotlin native for iphone

```kotlin
class MyPresenter {
    
    val TAG = "MyPresenter"

    fun start(){
        Logger.d(TAG, "my message")
        
        try{
            //myMethod
        } catch(e: Throwable){
            Logger.e(TAG, e.message, e)
        }
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
implementation "com.gitub.florent37:multiplatform-log:1.0.0"
```

## ios

Uses inside the NSUserDefaults

```groovy
implementation "com.gitub.florent37:multiplatform-log-ios:1.0.0"
```

## android

Uses inside the SharedPreferences

```groovy
implementation "com.gitub.florent37:multiplatform-log-android:1.0.0"
```

# Disable logs on release

Just disable the logger on debug :

```kotlin
Logger.enabled = BuildConfig.DEBUG
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