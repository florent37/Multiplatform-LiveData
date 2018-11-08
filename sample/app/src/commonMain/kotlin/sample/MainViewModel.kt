package sample

import com.github.florent37.livedata.KLiveData
import com.github.florent37.livedata.KMediatorLiveData
import com.github.florent37.log.Logger

class ViewState(
    val userStatus: String
)

class MainViewModel(val premiumManager: PremiumManager) {
    private val tag = "ErrorTag"
    private val viewState = KMediatorLiveData<ViewState>()

    init {
        viewState.value = ViewState("not premium")

        viewState.addSource(premiumManager.premium()) {
            Logger.d(tag, "add source called")
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