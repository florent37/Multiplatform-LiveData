package sample

class DependencyManager(val application: MainApplication) {

    val premiumManager by lazy { PremiumManager() }

    val mainViewmodel by lazy { MainPresenter(dependencies.premiumManager) }

}
