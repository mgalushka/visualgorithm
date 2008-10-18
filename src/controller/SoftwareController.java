/*
 * SoftwareController.java v1.00 16/06/08
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
import view.ISoftwareView;
import view.AbstractViewFactory;
import model.SoftwareModel;
import model.UnknownDataStructureException;
import model.tree.AbstractBinaryTree.BinaryTreeType;

/**
 * Definition of the software controller.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see IController
 */
public class SoftwareController implements IController {

    private SoftwareModel softwareModel;

    private ISoftwareView softwareView;

    private List<ITabController> tabControllers;

    /**
     * Builds the software controller.
     * 
     * @param m the software model
     */
    public SoftwareController(SoftwareModel m) {
        tabControllers = new ArrayList<ITabController>();

        softwareModel = m;
        AbstractViewFactory viewFactory = AbstractViewFactory.getFactory();
        softwareView = viewFactory.createSoftwareView(this);
        getView().displayView();
        addListener();
    }

    /**
     * Returns the wanted tab controller thanks to index.
     * 
     * @param index the index of the tab
     * @return the tab controller
     */
    public ITabController getTabController(int index) {
        return tabControllers.get(index);
    }

    @Override
    public ISoftwareView getView() {
        return softwareView;
    }

    private void addListener() {
        softwareModel.addModelListener(softwareView);
    }

    /**
     * Adds a binary tree tab.
     * 
     * @param type the type of the binary tree
     * @param index the index of the tab
     * @param width the width of the tree visualization
     * @param height the height of the tree visualization
     */
    public void addBinaryTreeTab(BinaryTreeType type, int index, int width,
            int height) {
        tabControllers.add(new BinaryTreeTabController(type, width, height));
        softwareModel.addTabModel(getTabController(index).getTabModel());
    }

    /**
     * Adds a binary tree tab with a random binary tree.
     * 
     * @param type the type of the binary tree
     * @param nbNode the number of nodes
     * @param index the index of the tab
     * @param width the width of the tree visualization
     * @param height the height of the tree visualization
     */
    public void addRandomBinaryTreeTab(BinaryTreeType type, int nbNode,
            int index, int width, int height) {
        tabControllers.add(new BinaryTreeTabController(type, nbNode, width,
                height));
        softwareModel.addTabModel(getTabController(index).getTabModel());
    }

    /**
     * Removes the tab indicated with index.
     * 
     * @param index the index of the tab
     */
    public void closeTab(int index) {
        softwareModel.removeTabModel(index);
        tabControllers.remove(index);
    }

    /**
     * Opens a file.
     * 
     * @param file the file
     * @param width the width of the tree visualization
     * @param height the height of the tree visualization
     */
    public void openFile(File file, int width,
            int height) throws FileNotFoundException, ParseException,
            IOException, UnknownDataStructureException {
        int index = tabControllers.size();
        String fileName = file.getName();
        int i = fileName.lastIndexOf('.');
        String extension = fileName.substring(i + 1).toLowerCase();

        if (extension.equals("bt")) {
            tabControllers
                    .add(new BinaryTreeTabController(file, width, height));
            softwareModel.addTabModelFromDataStructureFile(getTabController(
                index).getTabModel(), fileName);
        }
    }

    /**
     * Saves the data structure from the tab into the selected file.
     * 
     * @param file the file
     * @param index the index of the tab
     * @throws IOException
     */
    public void saveTabModel(File file, int index) throws IOException {
        getTabController(index).saveTabModel(file);
    }

    /**
     * Removes all the tabs.
     */
    public void exitSoftware() {
        softwareView.closeView();
        softwareModel.removeAllTabModels();
        tabControllers.clear();
    }
}
