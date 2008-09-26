/*
 * BinaryTreeTabController.java v1.00 16/06/08
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
import model.ITabModel;
import model.UnknownDataStructureException;
import model.tree.BinaryTreeTabModel;
import model.tree.AbstractBinaryTree.BinaryTreeType;
import view.AbstractViewFactory;
import view.IBinaryTreeTabView;
import view.IView;

/**
 * Definition of the binary tree tab controller.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see ITabController
 */
public class BinaryTreeTabController implements ITabController {

    private BinaryTreeTabModel binaryTreeTabModel;

    private IBinaryTreeTabView binaryTreeTabView;

    /**
     * Builds the binary tree tab controller.
     * 
     * @param type the type of the binary tree
     * @param width the width of the tree visualization
     * @param height the height of the tree visualization
     */
    public BinaryTreeTabController(BinaryTreeType type, int width, int height) {
        binaryTreeTabModel = new BinaryTreeTabModel(type);

        AbstractViewFactory viewFactory = AbstractViewFactory.getFactory();
        binaryTreeTabView = viewFactory.createBinaryTreeTabView("Type Of The Tree : "
                + type.toString(), this, width, height);
        addListener();
    }

    /**
     * Builds the binary tree tab controller.
     * 
     * @param file the file containing the binary tree
     * @param width the width of the tree visualization
     * @param height the height of the tree visualization
     * @throws UnknownDataStructureException
     * @throws IOException
     * @throws ParseException
     * @throws FileNotFoundException
     */
    public BinaryTreeTabController(File file, int width, int height)
            throws FileNotFoundException, ParseException, IOException,
            UnknownDataStructureException {
        binaryTreeTabModel = new BinaryTreeTabModel(file);

        AbstractViewFactory viewFactory = AbstractViewFactory.getFactory();
        binaryTreeTabView = viewFactory.createBinaryTreeTabView("Type Of The Tree : "
                + binaryTreeTabModel.getTabModel().getType(), this, width,
            height);
        addListener();
        binaryTreeTabModel.updateBinaryTreeView();
    }

    /**
     * Builds the binary tree tab controller.
     * 
     * @param type the type of the binary tree
     * @param nbNode the number of nodes
     * @param width the width of the tree visualization
     * @param height the height of the tree visualization
     */
    public BinaryTreeTabController(BinaryTreeType type, int nbNode, int width,
            int height) {
        binaryTreeTabModel = new BinaryTreeTabModel(type, nbNode);

        AbstractViewFactory viewFactory = AbstractViewFactory.getFactory();
        binaryTreeTabView = viewFactory.createBinaryTreeTabView("Type Of The Tree : "
                + type.toString(), this, width, height);
        addListener();
        binaryTreeTabModel.updateBinaryTreeView();
    }

    @Override
    public ITabModel getTabModel() {
        return binaryTreeTabModel;
    }

    @Override
    public void saveTabModel(File file) throws IOException {
        binaryTreeTabModel.saveTabModel(file);
    }

    @Override
    public boolean isTabModelSaved() {
        return binaryTreeTabModel.isTabModelSaved();
    }

    @Override
    public IView getView() {
        return binaryTreeTabView;
    }

    private void addListener() {
        binaryTreeTabModel.addBinaryTreeListener(binaryTreeTabView);
    }

    /**
     * Adds a node to the binary tree.
     * 
     * @param key the key of the node
     */
    public void addNode(int key) {
        binaryTreeTabModel.addNode(key);
    }

    /**
     * Deletes a node from the binary tree.
     * 
     * @param key the key of the node
     */
    public void deleteNode(int key) {
        binaryTreeTabModel.deleteNode(key);
    }

    /**
     * Deletes a node from the binary tree. It is a delete from
     * the pedagogical creation mode.
     * 
     * @param key the key of the node
     */
    public void pedagogicalDeleteNode(int key) {
        binaryTreeTabModel.pedagogicalDeleteNode(key);
    }
}
