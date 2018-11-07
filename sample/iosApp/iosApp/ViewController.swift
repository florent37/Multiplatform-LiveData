import UIKit
import app

class ViewController: UIViewController, MainView {
    
    let presenter: MainPresenter = MainPresenter()
    
    @IBAction func debugClicked(_ sender: Any) {
        presenter.displayLogDebug()
    }
    
    @IBAction func errorClicked(_ sender: Any) {
         presenter.displayLogError()
    }
    
    override func viewDidLoad() {
        presenter.bind(view: self)
        super.viewDidLoad()
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        presenter.unbind()
        super.viewDidDisappear(animated)
    }
    
}
