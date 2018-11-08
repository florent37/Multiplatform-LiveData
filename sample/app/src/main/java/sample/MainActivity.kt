package sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.florent37.livedata.observe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewmodel by lazy { dependencies.mainViewmodel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel.viewState().observe(this){
            userStatus.text = it.userStatus
        }

        premiumButton.setOnClickListener {
            viewmodel.becomePremium()
        }
    }


}
