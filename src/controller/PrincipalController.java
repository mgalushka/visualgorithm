/*
 * PrincipalController.java v1.00 16/06/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (visualgo@googlegroups.com)
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import view.IPrincipalModelView;
import view.AbstractViewFactory;
import model.PrincipalModel;
import model.tree.UnknownTreeTypeException;
import model.tree.AbstractBinaryTree.BinaryTreeType;

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

    private PrincipalModel model;

    private IPrincipalModelView gui;

    private List<ISubController> subControllers;

    /**
     * Builds the principal controller.
     * 
     * @param m the principal model
     */
    public PrincipalController(PrincipalModel m) {
        subControllers = new ArrayList<ISubController>();

        model = m;
        AbstractViewFactory viewFactory = AbstractViewFactory.getFactory();
        gui = viewFactory.createGraphicUserInterface(this);
        getView().displayView();
        addListener();
    }

    /**
     * Returns the wanted sub controller thanks to index.
     * 
     * @param index the index of the tab
     * @return the sub controller
     */
    public ISubController getSubController(int index) {
        return subControllers.get(index);
    }

    @Override
    public IPrincipalModelView getView() {
        return gui;
    }

    private void addListener() {
        model.addModelListener(gui);
    }

    /**
     * Adds a binary tree tab.
     * 
     * @param type the type of the binary tree
     * @param index the index of the tab
     */
    public void addBinaryTreeTab(BinaryTreeType type, int index) {
        subControllers.add(new BinaryTreeTabController(type));
        model.addSubModel(subControllers.get(index).getSubModel());
    }

    /**
     * Adds a binary tree tab with a random binary tree.
     * 
     * @param type the type of the binary tree
     * @param nbNode the number of nodes
     * @param index the index of the tab
     */
    public void addRandomBinaryTreeTab(BinaryTreeType type, int nbNode,
            int index) {
        subControllers.add(new BinaryTreeTabController(type, nbNode));
        model.addSubModel(subControllers.get(index).getSubModel());
    }
    
    /**
     * Removes the tab indicated with index.
     * 
     * @param index the index of the tab
     */
    public void closeTab(int index) {
        model.removeSubModel(index);
        subControllers.remove(index);
    }

    /**
     * Opens a file and loads it into a tab.
     * 
     * @param file the file
     * @param index the index of the tab
     */
    public void openFile(File file, int index) throws FileNotFoundException,
            ParseException, IOException, UnknownTreeTypeException {
        String fileName = file.getName();
        int i = fileName.lastIndexOf('.');
        String extension = fileName.substring(i + 1).toLowerCase();

        if (extension.equals("bt")) {
            subControllers.add(new BinaryTreeTabController(file));
            model.openSubModelFile(subControllers.get(index).getSubModel(),
                fileName);
        }
    }

    /**
     * Saves the data structure into the selected file.
     * 
     * @param file the file
     * @param index the index of the tab
     * @throws IOException
     */
    public void saveDataStructure(File file, int index) throws IOException {
        getSubController(index).saveSubModel(file);
    }

    /**
     * Removes all the tabs.
     */
    public void exitSoftware() {
        gui.closeView();
        model.removeAllSubModels();
        subControllers.clear();
    }
}
