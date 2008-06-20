/*
 * PrincipalController.java v1.00 16/06/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (bx1gl@googlegroups.com)
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import view.IModelView;
import view.AbstractViewFactory;
import model.Model;
import model.datastructure.DataStructureType;

/**
 * Definition of the principal controller.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see IController
 */
public class PrincipalController implements IController {
    
    private Model model;
 
    private IModelView gui;
    
    private List<TabController> tabControllers;
    
    /**
     * Builds the principal controller.
     * 
     * @param m the model
     */
    public PrincipalController(Model m) {
        tabControllers = new ArrayList<TabController>();
        
        model = m;
        AbstractViewFactory viewFactory = 
            AbstractViewFactory.getFactory();
        gui = viewFactory.createGraphicUserInterface(model, this);
        getView().displayView();
        addListener();
    }
    
    /**
     * Returns the wanted tab controller thanks to index.
     * 
     * @param index the index of the tab
     * @return the tab controller
     */
    public IController getTabController(int index) {
        return tabControllers.get(index);
    }
    
    @Override
    public IModelView getView() {
        return gui;
    }
    
    private void addListener() {
        model.addModelListener(gui);
    }

    /**
     * Adds a tab.
     * 
     * @param type the type of the data structure
     * @param index the index of the tab
     */
    public void addTabDataStructure(DataStructureType type,
            int index) {
        tabControllers.add(new TabController(type));
        model.addDataStructure(
            tabControllers.get(index).getDataStructure(), type);
    }
    
    /**
     * Removes the tab indicated with index.
     * 
     * @param index the index of the tab
     */
    public void closeTab(int index) {
        model.removeDataStructure(index);
        tabControllers.remove(index);
    }
    
    /**
     * Removes all the tabs.
     */
    public void exitSoftware() {
        gui.closeView();
        model.removeAllDataStructure();
        tabControllers.clear();
    }
}