package view;

import swing.SwingViewFactory;
import model.Model;
import controller.PrincipalController;


public abstract class AbstractViewFactory {

    public static AbstractViewFactory getFactory() {
        return new SwingViewFactory();
    }
    
    public abstract IPrincipalView createGraphicUserInterface(PrincipalController c);
    
    public abstract IFastTreeCreationView createFastTreeCreation(Model m);
}
