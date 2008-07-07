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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import model.ISubModel;
import model.tree.BinaryTreeSubModel;
import model.tree.UnknownTreeTypeException;
import model.tree.AbstractBinaryTree.BinaryTreeType;
import view.AbstractViewFactory;
import view.IBinaryTreeView;
import view.IView;

/**
 * Definition of the binary tree tab controller.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see IController
 */
public class BinaryTreeTabController implements ISubController {
    
    private static String subControllerType = "TREE";

    private BinaryTreeSubModel binaryTreeSubModel;
    
    private IBinaryTreeView tabPageView;

    /**
     * Builds the binary tree tab controller.
     * 
     * @param type the type of the binary tree
     */
    public BinaryTreeTabController(BinaryTreeType type) {
        binaryTreeSubModel = new BinaryTreeSubModel(type);
        
        AbstractViewFactory viewFactory = 
            AbstractViewFactory.getFactory();
        tabPageView = viewFactory.createBinaryTreeTabPage(
            type.toString(), this);
        addListener();
    }
    
    /**
     * Builds the binary tree tab controller.
     * 
     * @param file the file containing the binary tree
     * @throws UnknownTreeTypeException 
     * @throws IOException 
     * @throws ParseException 
     * @throws FileNotFoundException 
     */
    public BinaryTreeTabController(String file) throws FileNotFoundException,
            ParseException, IOException, UnknownTreeTypeException {
        binaryTreeSubModel = new BinaryTreeSubModel(file);
        
        AbstractViewFactory viewFactory = 
            AbstractViewFactory.getFactory();
        tabPageView = viewFactory.createBinaryTreeTabPage(
            binaryTreeSubModel.getDataStructure().getType(), this);
        addListener();
        binaryTreeSubModel.updateBinaryTreeView();
    }

    @Override
    public String getType() {
        return subControllerType;
    }
    
    @Override
    public ISubModel getSubModel() {
        return binaryTreeSubModel;
    }
    
    @Override
    public boolean isSaved() {
        return binaryTreeSubModel.isBinaryTreeSaved();
    }
    
    @Override
    public IView getView() {
        return tabPageView;
    }

    private void addListener() {
        binaryTreeSubModel.addBinaryTreeListener(tabPageView);
    }

    /**
     * Adds a node to the data structure.
     * 
     * @param key the key of the node
     */
    public void addNode(int key) {
        binaryTreeSubModel.addNode(key);
    }
    
    /**
     * Deletes a node from the data structure.
     * 
     * @param key the key of the node
     */
    public void deleteNode(int key) {
        binaryTreeSubModel.deleteNode(key);
    }
    
    /**
     * Saves the binary tree into the selected file.
     * 
     * @param file the file
     * @throws IOException 
     */
    public void saveBinaryTreeSubModel(String file) throws IOException {
        binaryTreeSubModel.saveBinaryTree(file);
    }
}
