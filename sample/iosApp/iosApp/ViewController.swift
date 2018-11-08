import UIKit
import app

class ViewController: UIViewController {
    
    var lifecycle = Multiplatform_livedata_iosKLifecycle()
    
    lazy var viewModel: MainViewModel = {
        dependencies().mainViewmodel
    }()
    
    @IBOutlet weak var userStatus: UILabel!
    @IBAction func becomePremiumClicked(_ sender: Any) {
        viewModel.becomePremium()
        if let viewState = viewModel.viewState().value as? ViewState {
            self.userStatus.text = viewState.userStatus
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        lifecycle.start()
        
        viewModel.viewState().observe(lifecycle: lifecycle) { (value) -> KotlinUnit in
            if let viewState = value as? ViewState {
                self.userStatus.text = viewState.userStatus
            }
            
            return KotlinUnit()
        }
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        lifecycle.stop()
    }
    
}
