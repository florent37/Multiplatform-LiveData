package sample

import com.github.florent37.log.Logger

interface MainView {
}

class MainPresenter {
    private val tag = "ErrorTag"
    private var view: MainView? = null

    fun bind(view: MainView){
        this.view = view
    }

    fun unbind(){
        this.view = null
    }

    fun displayLogError(){
        Logger.e(tag, "my error message")
    }

    fun displayLogDebug(){
        Logger.e(tag, "my debug message")
    }

}