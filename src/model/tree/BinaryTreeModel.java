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

import algorithm.tree.AVLTreeAlgorithmStrategy;
import algorithm.tree.BinarySearchTreeAlgorithmStrategy;
import algorithm.tree.IBinaryTreeAlgorithmStrategy;
import algorithm.tree.RedBlackTreeAlgorithmStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.event.EventListenerList;
import io.tree.TreeFile;
import java.util.EventListener;
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
 * @see IBinaryTreeModel
 */
public final class BinaryTreeModel implements IBinaryTreeModel {

    private IBinaryTree binaryTree;

    private IBinaryTreeAlgorithmStrategy algorithmStrategy;

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

        setBinaryTreeAlgorithmStrategy();
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

        setBinaryTreeAlgorithmStrategy();
    }

    /**
     * Sets the binary tree algorithm strategy in order to call the algorithms
     * corresponding to the type of the binary tree.
     */
    public void setBinaryTreeAlgorithmStrategy() {
        if (binaryTree instanceof BinarySearchTree) {
            algorithmStrategy = new BinarySearchTreeAlgorithmStrategy((BinarySearchTree) binaryTree);
        } else if (binaryTree instanceof AVLTree) {
            algorithmStrategy = new AVLTreeAlgorithmStrategy((AVLTree) binaryTree);
        } else if (binaryTree instanceof RedBlackTree) {
            algorithmStrategy = new RedBlackTreeAlgorithmStrategy((RedBlackTree) binaryTree);
        }
    }

    @Override
    public String getDataStructureType() {
        return binaryTree.getType();
    }

    @Override
    public boolean isDataStructureSaved() {
        return isBinaryTreeSaved;
    }

    @Override
    public void saveDataStructure(File file) throws IOException {
        String path = file.getAbsolutePath();

        if (path.endsWith("." + TreeFile.FILE_EXTENSION)) {
            TreeFile.save(binaryTree, path);
        } else {
            TreeFile.save(binaryTree, path.concat("." + TreeFile.FILE_EXTENSION));
        }
        isBinaryTreeSaved = true;
    }

    @Override
    public void addModelListener(EventListener listener) throws IllegalArgumentException {
        if (!(listener instanceof BinaryTreeModelListener)) {
            throw new IllegalArgumentException("You have to pass a BinaryTreeModelListener");
        }

        BinaryTreeModelListener binaryTreeModelListener = (BinaryTreeModelListener)listener;

        listeners.add(BinaryTreeModelListener.class, binaryTreeModelListener);
        binaryTreeModelListener.binaryTreeHasChanged(new BinaryTreeModelEvent(this,
                    binaryTree.buildHeapFromBinaryTree()));
    }

    @Override
    public void removeModelListener(EventListener listener) throws IllegalArgumentException {
        if (!(listener instanceof BinaryTreeModelListener)) {
            throw new IllegalArgumentException("You have to pass a BinaryTreeModelListener");
        }
        listeners.remove(BinaryTreeModelListener.class, (BinaryTreeModelListener)listener);
    }

    @Override
    public void insertRandomNodes(int nbNode) {
        for (int i = 0; i < nbNode; i++) {
            int key = (int) Math.round(Math.random() * 99);
            
            algorithmStrategy.insertNode(key);
        }
        updateListeners();
        isBinaryTreeSaved = false;
    }

    @Override
    public void insertNode(int key) throws IllegalArgumentException {
        algorithmStrategy.insertNode(key);
        
        updateListeners();
        isBinaryTreeSaved = false;
    }

    @Override
    public void deleteNode(int key) {
        algorithmStrategy.deleteNode(key);

        updateListeners();
        isBinaryTreeSaved = false;
    }

    private void updateListeners() {
        BinaryTreeModelListener[] listenerTab =
                listeners.getListeners(BinaryTreeModelListener.class);
        for (BinaryTreeModelListener listener : listenerTab) {
            listener.binaryTreeHasChanged(new BinaryTreeModelEvent(this,
                    binaryTree.buildHeapFromBinaryTree()));
        }
    }
}
