package sample

import android.app.Application
import com.github.florent37.log.Logger

class MainApplication : Application() {

    val dependencyManager by lazy { DependencyManager(this) }

    override fun onCreate() {
        super.onCreate()

        Logger.enabled = BuildConfig.DEBUG

        _app = this
    }
}

private lateinit var _app: MainApplication

val app: MainApplication
    get() = _app

val dependencies: DependencyManager
    get() = app.dependencyManager