package controller;

import model.Model;


public abstract class AbstractController implements IController {

    Model model;
    
    public AbstractController(Model m){
        model = m;
    }
}