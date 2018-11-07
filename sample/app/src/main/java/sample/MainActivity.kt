package sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.florent37.log.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logger.enabled = BuildConfig.DEBUG

        presenter.bind(this)

        debugButton.setOnClickListener {
            presenter.displayLogDebug()
        }
        errorButton.setOnClickListener {
            presenter.displayLogError()
        }
    }


}