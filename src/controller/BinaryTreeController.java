/*
 * BinaryTreeController.java v0.10 16/06/08
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
import model.ISoftwareModel;
import model.UnknownDataStructureException;
import model.tree.BinaryTreeModel;
import model.tree.AbstractBinaryTree.BinaryTreeType;
import model.tree.BinaryTreeModelListener;
import model.tree.IBinaryTreeModel;
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
 * @version 0.10 16/06/08
 * @see IBinaryTreeController
 */
public final class BinaryTreeController implements IBinaryTreeController {

    private IBinaryTreeModel binaryTreeModel;

    private IBinaryTreeView binaryTreeView;

    /**
     * Builds the binary tree controller. The binary tree controller is composed
     * by a binary tree model and the corresponding binary tree view.
     */
    public BinaryTreeController() {
    }

    @Override
    public IBinaryTreeController clone() {
        return new BinaryTreeController();
    }

    @Override
    public void initializeDataStructureController(Object type,
            AbstractViewFactory viewFactory)
            throws IllegalArgumentException {
        if (!(type instanceof BinaryTreeType)) {
            throw new IllegalArgumentException("You have to pass a" +
                    " BinaryTreeType for the parameter type");
        }
        binaryTreeModel = new BinaryTreeModel((BinaryTreeType) type);

        binaryTreeView = viewFactory.createBinaryTreeView(
                "Type of the Tree : " + type.toString(), this);
        addListener();
    }

    @Override
    public void initializeDataStructureController(Object type,
            AbstractViewFactory viewFactory, int nb)
            throws IllegalArgumentException {
        if (!(type instanceof BinaryTreeType)) {
            throw new IllegalArgumentException("You have to pass a" +
                    " BinaryTreeType for the parameter type");
        }
        binaryTreeModel = new BinaryTreeModel((BinaryTreeType) type);
        binaryTreeModel.insertRandomNodes(nb);

        binaryTreeView = viewFactory.createBinaryTreeView(
                "Type Of The Tree : " + type.toString(), this);
        addListener();
    }

    @Override
    public void initializeDataStructureController(File file,
            AbstractViewFactory viewFactory)
            throws FileNotFoundException, ParseException, IOException,
            UnknownDataStructureException {
        binaryTreeModel = new BinaryTreeModel(file);

        binaryTreeView = viewFactory.createBinaryTreeView(
                "Type of the Tree : " + binaryTreeModel.getDataStructureType(), this);
        addListener();
    }

    @Override
    public void addDataStructureModelToSoftwareModel(ISoftwareModel softwareModel) {
        softwareModel.addDataStructureModel(binaryTreeModel);
    }

    @Override
    public void addDataStructureModelToSoftwareModelFromFile(
            ISoftwareModel softwareModel, String fileName) {
        softwareModel.addDataStructureModelFromFile(binaryTreeModel, fileName);
    }

    @Override
    public IBinaryTreeView getView() {
        return binaryTreeView;
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
        assert(binaryTreeView instanceof BinaryTreeModelListener);

        binaryTreeModel.addModelListener(binaryTreeView);
    }

    @Override
    public void addNodeToBinaryTreeModel(int key) throws IllegalArgumentException {
        binaryTreeModel.insertNode(key);
    }

    @Override
    public void deleteNodeFromBinaryTreeModel(int key) {
        binaryTreeModel.deleteNode(key);
    }
}
