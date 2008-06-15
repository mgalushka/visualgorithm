package swing;

import model.Model;
import controller.PrincipalController;
import view.IPrincipalView;
import view.IFastTreeCreationView;
import view.AbstractViewFactory;


public class SwingViewFactory extends AbstractViewFactory{
    
    public IFastTreeCreationView createFastTreeCreation(Model m) {
        return new FastTreeCreation(m);
    }

    public IPrincipalView createGraphicUserInterface(PrincipalController c) {
        return new GraphicUserInterface(c);
    }
}
