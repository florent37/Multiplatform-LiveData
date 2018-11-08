package sample

import com.github.florent37.livedata.KLiveData
import com.github.florent37.livedata.KMutableLiveData

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