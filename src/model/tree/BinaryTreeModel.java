/*
 * BinaryTreeModel.java v1.00 16/06/08
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

package model.tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.event.EventListenerList;
import io.tree.TreeFile;
import model.IDataStructureModel;
import model.UnknownDataStructureException;
import model.tree.AbstractBinaryTree.BinaryTreeType;

/**
 * This class contains all the methods in order to modify the binary tree of the
 * model like {@code void insertNode(int key)}. This is a data structure model
 * that is used to represent each binary tree in the software. It is possible to
 * add a binary tree model listener to the model in order to listen the
 * modifications of the binary tree. This class is not designed for inheritance.
 * 
 * @author Julien Hannier
 * @version 1.00 16/06/08
 * @see IDataStructureModel
 */
public final class BinaryTreeModel implements IDataStructureModel {

    /**
     * Defines the name of the data structure of this model. It is used to find
     * the corresponding components of the software like the corresponding
     * controller.
     */
    public final static String DATA_STRUCTURE_NAME = "BINARYTREE";
    
    private IBinaryTree binaryTree;

    private EventListenerList listeners;

    private boolean isBinaryTreeSaved;

    /**
     * Builds the binary tree model from a type of tree. This binary tree model
     * is not considered as saved.
     * 
     * @param type the type of the binary tree
     */
    public BinaryTreeModel(BinaryTreeType type) {
        binaryTree = type.createBinaryTree();
        listeners = new EventListenerList();
        isBinaryTreeSaved = false;
    }

    /**
     * Builds the binary tree model from a file containing a binary tree. This
     * binary tree model is considered as saved. The different exceptions that
     * could be thrown must be translated until the graphic user interface.
     * 
     * @param file the file containing the binary tree
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws UnknownDataStructureException
     */
    public BinaryTreeModel(File file) throws FileNotFoundException,
            ParseException, IOException, UnknownDataStructureException {
        binaryTree = TreeFile.load(file.getAbsolutePath());
        listeners = new EventListenerList();
        isBinaryTreeSaved = true;
    }

    @Override
    public IBinaryTree getDataStructure() {
        return binaryTree;
    }

    @Override
    public boolean isDataStructureSaved() {
        return isBinaryTreeSaved;
    }

    @Override
    public void saveDataStructure(File file) throws IOException {
        String path = file.getAbsolutePath();

        if (path.endsWith(".bt")) {
            TreeFile.save(binaryTree, path);
        } else {
            TreeFile.save(binaryTree, path.concat(".bt"));
        }
        isBinaryTreeSaved = true;
    }

    /**
     * Adds a binary tree model listener to the binary tree model.
     * 
     * @param listener the listener to add
     */
    public void addBinaryTreeModelListener(BinaryTreeModelListener listener) {
        listeners.add(BinaryTreeModelListener.class, listener);
    }

    /**
     * Removes a binary tree model listener from the binary tree model.
     * 
     * @param listener the listener to remove
     */
    public void removeBinaryTreeModelListener(BinaryTreeModelListener listener) {
        listeners.remove(BinaryTreeModelListener.class, listener);
    }

    /**
     * Inserts {@code nbNode} random nodes into the binary tree. This method
     * uses the insert algorithm corresponding to the type of the binary tree.
     * If it is necessary, the binary tree is corrected. For instance, the
     * balance of an AVL tree is corrected after multiple insertions.
     * 
     * @param nbNode the number of random nodes to insert
     */
    public void insertRandomNodes(int nbNode) {
        for (int i = 0; i < nbNode; i++) {
            int key = (int) Math.round(Math.random() * 100);
            // TODO insertion
            System.out.println(key);
        }
        updateListeners();
        isBinaryTreeSaved = false;
    }

    /**
     * Inserts a node into the binary tree. This method uses the insert
     * algorithm corresponding to the type of the binary tree. If it is
     * necessary, the binary tree is corrected. For instance, the balance of an
     * AVL tree is corrected after the insertion. If {@code key} is greater than
     * 99 or less than 0 then an IllegalArgumentException is thrown.
     * 
     * @param key the key of the node to insert
     * @throws IllegalArgumentException
     */
    public void insertNode(int key) throws IllegalArgumentException {
        // TODO insertion
        System.out.println(key);
        updateListeners();
        isBinaryTreeSaved = false;
    }

    /**
     * Deletes a node from the binary tree. This method uses the delete
     * algorithm corresponding to the type of the binary tree. If it is
     * necessary, the binary tree is corrected. For instance, the balance of an
     * AVL tree is corrected after the deletion.
     * 
     * @param key the key of the node to delete
     */
    public void deleteNode(int key) {
        // TODO deletion
        System.out.println(key);
        updateListeners();
        isBinaryTreeSaved = false;
    }

    /**
     * Deletes a node from the binary tree. This method uses the delete
     * algorithm from the binary search tree that is to say that the node is
     * deleted without any correction of the binary tree. For instance, the
     * balance of an AVL tree is not corrected after the deletion.
     * 
     * @param key the key of the node to delete
     */
    public void deleteNodeWithoutCorrection(int key) {
        // TODO deletion with BST delete algorithm
        System.out.println(key);
        updateListeners();
        isBinaryTreeSaved = false;
    }

    /**
     * Updates the listeners with the last version of the binary tree of the
     * model.
     */
    public void updateListeners() {
        BinaryTreeModelListener[] listenerTab =
                listeners.getListeners(BinaryTreeModelListener.class);
        for (BinaryTreeModelListener listener : listenerTab) {
            listener.binaryTreeHasChanged(new BinaryTreeModelEvent(this,
                    binaryTree.buildHeapFromBinaryTree()));
        }
    }
}
