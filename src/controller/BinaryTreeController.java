/*
 * BinaryTreeController.java v1.00 16/06/08
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

import io.tree.TreeFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import model.IDataStructureModel;
import model.UnknownDataStructureException;
import model.tree.BinaryTreeModel;
import model.tree.AbstractBinaryTree.BinaryTreeType;
import view.AbstractViewFactory;
import view.IBinaryTreeView;

/**
 * Definition of the binary tree tab controller.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see IDataStructureController
 */
public class BinaryTreeController implements IDataStructureController {

    private BinaryTreeModel binaryTreeTabModel;

    private IBinaryTreeView binaryTreeTabView;

    @Override
    public void initializeTabController(Object type,
            AbstractViewFactory viewFactory, int width, int height) {
        binaryTreeTabModel = new BinaryTreeModel((BinaryTreeType) type);

        binaryTreeTabView = viewFactory.createBinaryTreeView(
            "Type of the Tree : " + type.toString(), this, width, height);
        addListener();
    }

    @Override
    public void initializeTabControllerWithRandom(Object type,
            AbstractViewFactory viewFactory, int random, int width, int height) {
        binaryTreeTabModel = new BinaryTreeModel((BinaryTreeType) type);
        binaryTreeTabModel.insertRandomNodes(random);

        binaryTreeTabView = viewFactory.createBinaryTreeView(
            "Type Of The Tree : " + type.toString(), this, width, height);
        addListener();
        binaryTreeTabModel.updateListeners();
    }

    @Override
    public void initializeTabControllerWithFile(File file,
            AbstractViewFactory viewFactory, int width, int height)
            throws FileNotFoundException, ParseException, IOException,
            UnknownDataStructureException {
        binaryTreeTabModel = new BinaryTreeModel(file);

        binaryTreeTabView = viewFactory.createBinaryTreeView(
            "Type of the Tree : " + binaryTreeTabModel.getDataStructure().getType(),
            this, width, height);
        addListener();
        binaryTreeTabModel.updateListeners();
    }

    @Override
    public IDataStructureModel getTabModel() {
        return binaryTreeTabModel;
    }

    @Override
    public void saveTabModel(File file) throws IOException {
        binaryTreeTabModel.saveDataStructure(file);
    }

    @Override
    public boolean isTabModelSaved() {
        return binaryTreeTabModel.isDataStructureSaved();
    }

    @Override
    public String getFileExtension() {
        return TreeFile.fileExtension;
    }

    @Override
    public IBinaryTreeView getView() {
        return binaryTreeTabView;
    }

    private void addListener() {
        binaryTreeTabModel.addBinaryTreeModelListener(binaryTreeTabView);
    }

    /**
     * Adds a node to the binary tree.
     * 
     * @param key the key of the node
     */
    public void addNode(int key) {
        binaryTreeTabModel.insertNode(key);
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
     * Deletes a node from the binary tree. It is a delete from the pedagogical
     * creation mode.
     * 
     * @param key the key of the node
     */
    public void pedagogicalDeleteNode(int key) {
        binaryTreeTabModel.deleteNodeWithoutCorrection(key);
    }
}
