package controller;

import view.IPrincipalView;
import view.AbstractViewFactory;
import model.Model;


public class PrincipalController extends AbstractController {
 
    private IPrincipalView gui;
    
    private FastTreeCreationController fastTreeCreationController;
    
    public FastTreeCreationController getFastTreeCreationController() {
        return fastTreeCreationController;
    }
    
    public PrincipalController(Model m) {
        super(m);
        AbstractViewFactory viewFactory = AbstractViewFactory.getFactory();
        gui = viewFactory.createGraphicUserInterface(this);
        getView().displayView();
    }
    
    public void fastCreationTree() {
        fastTreeCreationController = new FastTreeCreationController(model);
    }
    
    @Override
    public IPrincipalView getView() {
        return gui;
    }
}