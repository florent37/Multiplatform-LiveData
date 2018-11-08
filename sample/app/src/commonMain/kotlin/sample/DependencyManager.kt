package sample

class DependencyManager {

    val premiumManager by lazy { PremiumManager() }

    val mainViewmodel by lazy { MainViewModel(premiumManager) }

}
