/*
 * TabController.java v1.00 16/06/08
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

import model.DataStructure;
import model.DataStructureType;
import view.AbstractViewFactory;
import view.IDataStructureView;
import view.IView;

/**
 * Definition of the tab controller.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see IController
 */
public class TabController implements IController {

    private DataStructure dataStructure;
    
    private IDataStructureView tabPageView;
    
    /**
     * Builds the tab controller.
     * 
     * @param type the type of the data structure
     */
    public TabController(DataStructureType type) {
        dataStructure = new DataStructure(type);
        
        AbstractViewFactory viewFactory = AbstractViewFactory.getFactory();
        tabPageView = viewFactory.createTabPage(dataStructure, type, this);
        addListener();
    }

    /**
     * Returns the data structure.
     * 
     * @return the data structure
     */
    public DataStructure getDataStructure() {
        return dataStructure;
    }
    
    @Override
    public IView getView() {
        return tabPageView;
    }

    private void addListener() {
        dataStructure.addDataStructureListener(tabPageView);
    }

    /**
     * Adds a node to the data structure. Only for
     * binary trees.
     * 
     * @param key the key of the node
     */
    public void addNode(int key) {
        dataStructure.addNode(key);
    }
    
    /**
     * Deletes a node from the data structure. Only for
     * binary trees.
     * 
     * @param key the key of the node
     */
    public void deleteNode(int key) {
        dataStructure.deleteNode(key);
    }
}
