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
 * This class is the link between the binary tree view and the binary tree
 * model. Thus, the view does not know directly the model. It is the principle
 * of the model Model-View-Controller. So, this is a data structure controller
 * that is used for every binary tree in the software. This class is not
 * designed for inheritance.
 *
 * @author Julien Hannier
 * @version 1.00 16/06/08
 * @see IDataStructureController
 */
public final class BinaryTreeController implements IDataStructureController {

    /**
     * Definition of the extension of the binary tree files.
     */
    public final static String binaryTreeFileExtension = TreeFile.fileExtension;
    
    private BinaryTreeModel binaryTreeModel;

    private IBinaryTreeView binaryTreeView;

    @Override
    public void initializeDataStructureController(Object type,
            AbstractViewFactory viewFactory, int width, int height)
            throws IllegalArgumentException {
        if (!(type instanceof BinaryTreeType)) {
            throw new IllegalArgumentException("You have to pass a" +
                    " BinaryTreeType for the parameter type");
        }
        binaryTreeModel = new BinaryTreeModel((BinaryTreeType) type);

        binaryTreeView = viewFactory.createBinaryTreeView(
                "Type of the Tree : " + type.toString(), this, width, height);
        addListener();
    }

    @Override
    public void initializeDataStructureController(Object type,
            AbstractViewFactory viewFactory, int nb, int width, int height)
            throws IllegalArgumentException {
        if (!(type instanceof BinaryTreeType)) {
            throw new IllegalArgumentException("You have to pass a" +
                    " BinaryTreeType for the parameter type");
        }
        binaryTreeModel = new BinaryTreeModel((BinaryTreeType) type);
        binaryTreeModel.insertRandomNodes(nb);

        binaryTreeView = viewFactory.createBinaryTreeView(
                "Type Of The Tree : " + type.toString(), this, width, height);
        addListener();
        binaryTreeModel.updateListeners();
    }

    @Override
    public void initializeDataStructureController(File file,
            AbstractViewFactory viewFactory, int width, int height)
            throws FileNotFoundException, ParseException, IOException,
            UnknownDataStructureException {
        binaryTreeModel = new BinaryTreeModel(file);

        binaryTreeView = viewFactory.createBinaryTreeView(
                "Type of the Tree : " + binaryTreeModel.getDataStructure().getType(),
                this, width, height);
        addListener();
        binaryTreeModel.updateListeners();
    }

    @Override
    public IBinaryTreeView getView() {
        return binaryTreeView;
    }

    @Override
    public IDataStructureModel getDataStructureModel() {
        return binaryTreeModel;
    }

    @Override
    public void saveDataStructureModel(File file) throws IOException {
        binaryTreeModel.saveDataStructure(file);
    }

    @Override
    public boolean isDataStructureModelSaved() {
        return binaryTreeModel.isDataStructureSaved();
    }

    private void addListener() {
        binaryTreeModel.addBinaryTreeModelListener(binaryTreeView);
    }

    /**
     * Adds a node into the binary tree. This method uses the insert
     * algorithm corresponding to the type of the binary tree. If it is
     * necessary, the binary tree is corrected. For instance, the balance of an
     * AVL tree is corrected after the insertion. If {@code key} is greater than
     * 99 or less than 0 then an IllegalArgumentException is thrown.
     * 
     * @param key the key of the node to add
     * @throws IllegalArgumentException
     */
    public void addNodeToBinaryTreeModel(int key) throws IllegalArgumentException {
        binaryTreeModel.insertNode(key);
    }

    /**
     * Deletes a node from the binary tree. This method uses the delete
     * algorithm corresponding to the type of the binary tree. If it is
     * necessary, the binary tree is corrected. For instance, the balance of an
     * AVL tree is corrected after the deletion.
     *
     * @param key the key of the node to delete
     */
    public void deleteNodeFromBinaryTreeModel(int key) {
        binaryTreeModel.deleteNode(key);
    }

    /**
     * Deletes a node from the binary tree. This method uses the delete
     * algorithm from the binary search tree that is to say that the node is
     * deleted without any correction of the binary tree. For instance, the
     * balance of an AVL tree is not corrected after the deletion.
     * 
     * @param key the key of the node to delete
     */
    public void deleteNodeFromBinaryTreeModelWithoutCorrection(int key) {
        binaryTreeModel.deleteNodeWithoutCorrection(key);
    }
}
