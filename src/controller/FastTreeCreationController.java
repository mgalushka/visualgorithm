package controller;

import model.Model;
import view.IFastTreeCreationView;
import view.IView;
import view.AbstractViewFactory;


public class FastTreeCreationController extends AbstractController {
 
    private IFastTreeCreationView fastTreeCreationView;
    
    public FastTreeCreationController(Model m) {
        super(m);
        AbstractViewFactory viewFactory = AbstractViewFactory.getFactory();
        fastTreeCreationView = viewFactory.createFastTreeCreation(m);
    }
    
    @Override
    public IView getView() {
        return fastTreeCreationView;
    }
}