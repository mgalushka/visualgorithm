/*
 * BinaryTreeTabModel.java v1.00 16/06/08
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
import java.util.List;
import javax.swing.event.EventListenerList;
import io.TreeFile;
import model.IDataStructure;
import model.ITabModel;
import model.UnknownDataStructureException;
import model.tree.AbstractBinaryTree.BinaryTreeType;

/**
 * Definition of the binary tree tab model.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class BinaryTreeTabModel implements ITabModel {

    public final static String DataStructureName = "TREE";
    
    private IBinaryTree binaryTree;

    private EventListenerList listeners;

    private boolean isBinaryTreeSaved;

    /**
     * Builds the binary tree tab model.
     * 
     * @param type the type of the binary tree
     */
    public BinaryTreeTabModel(BinaryTreeType type) {
        binaryTree = type.getBinaryTree();
        listeners = new EventListenerList();
        isBinaryTreeSaved = false;
    }

    /**
     * Builds the binary tree tab model.
     * 
     * @param file the file containing the binary tree
     * @throws UnknownDataStructureException
     * @throws IOException
     * @throws ParseException
     * @throws FileNotFoundException
     */
    public BinaryTreeTabModel(File file) throws FileNotFoundException,
            ParseException, IOException, UnknownDataStructureException {
        binaryTree = TreeFile.load(file.getAbsolutePath());
        listeners = new EventListenerList();
        isBinaryTreeSaved = true;
    }

    /**
     * Builds the binary tree tab model.
     * 
     * @param type the type of the binary tree
     * @param nbNode the number of nodes
     */
    public BinaryTreeTabModel(BinaryTreeType type, int nbNode) {
        binaryTree = type.getBinaryTree();
        for (int i = 0; i < nbNode; i++) {
            int key = (int) Math.round(Math.random() * 100);
            // TODO insertion
            System.out.println(key);
        }
        listeners = new EventListenerList();
        isBinaryTreeSaved = false;
    }

    @Override
    public boolean isTabModelSaved() {
        return isBinaryTreeSaved;
    }

    @Override
    public IDataStructure getTabModel() {
        return binaryTree;
    }

    /**
     * Adds a binary tree tab listener to the binary tree tab model.
     * 
     * @param listener the listener to add
     */
    public void addBinaryTreeListener(BinaryTreeTabListener listener) {
        listeners.add(BinaryTreeTabListener.class, listener);
    }

    /**
     * Removes a binary tree tab listener from the binary tree tab model.
     * 
     * @param listener the listener to remove
     */
    public void removeBinaryTreeListener(BinaryTreeTabListener listener) {
        listeners.remove(BinaryTreeTabListener.class, listener);
    }

    /**
     * Adds a node to the binary tree.
     * 
     * @param key the key of the node
     */
    public void addNode(int key) {
        // TODO insertion
        System.out.println(key);
        fireBinaryTreeChanged(binaryTree.treeToArrayList());
        isBinaryTreeSaved = false;
    }

    /**
     * Deletes a node from the binary tree.
     * 
     * @param key the key of the node
     */
    public void deleteNode(int key) {
        // TODO deletion
        System.out.println(key);
        fireBinaryTreeChanged(binaryTree.treeToArrayList());
        isBinaryTreeSaved = false;
    }

    /**
     * Deletes a node from the binary tree. It is a delete from the pedagogical
     * creation mode.
     * 
     * @param key the key of the node
     */
    public void pedagogicalDeleteNode(int key) {
        // TODO deletion with BST delete algorithm
        System.out.println(key);
        fireBinaryTreeChanged(binaryTree.treeToArrayList());
        isBinaryTreeSaved = false;
    }

    /**
     * Updates the view.
     */
    public void updateBinaryTreeView() {
        fireBinaryTreeChanged(binaryTree.treeToArrayList());
    }

    @Override
    public void saveTabModel(File file) throws IOException {
        String path = file.getAbsolutePath();

        if (path.endsWith(".bt")) {
            TreeFile.save(binaryTree, path);
        } else {
            TreeFile.save(binaryTree, path.concat(".bt"));
        }
        isBinaryTreeSaved = true;
    }

    private <N extends IBinaryNode> void fireBinaryTreeChanged(List<N> data) {
        BinaryTreeTabListener[] listenerTab = listeners.getListeners(BinaryTreeTabListener.class);
        for (BinaryTreeTabListener listener : listenerTab) {
            listener.binaryTreeChanged(new BinaryTreeTabEvent<N>(this, data));
        }
    }
}
