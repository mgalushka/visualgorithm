/*
 * DataStructure.java v1.00 16/06/08
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

package model.tree;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.swing.event.EventListenerList;

import io.TreeFile;
import model.BinaryTreeFactory;
import model.IDataStructure;
import model.ISubModel;
import model.tree.AbstractBinaryTree.BinaryTreeType;

/**
 * Definition of the binary tree.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class BinaryTreeSubModel implements ISubModel{

    private IBinaryTree<?> dataStructure;
    
    private EventListenerList listeners;
    
    private boolean isBinaryTreeSaved;
    
    /**
     * Builds the binary tree.
     * 
     * @param type the type of the binary tree
     */
    public BinaryTreeSubModel(BinaryTreeType type) {
        dataStructure = 
            BinaryTreeFactory.createBinaryTree(type);
        listeners = new EventListenerList();
        isBinaryTreeSaved = false;
    }
    
    /**
     * Builds the binary tree.
     * 
     * @param file the file containing the binary tree
     * @throws UnknownTreeTypeException 
     * @throws IOException 
     * @throws ParseException 
     * @throws FileNotFoundException 
     */
    public BinaryTreeSubModel(String file) throws FileNotFoundException,
            ParseException, IOException, UnknownTreeTypeException {
        dataStructure = TreeFile.load(file);
        listeners = new EventListenerList();
        isBinaryTreeSaved = true;
    }
    
    /**
     * Returns true if the binary tree has been saved.
     * 
     * @return true if the binary tree has been saved
     */
    public boolean isBinaryTreeSaved() {
        return isBinaryTreeSaved;
    }
    
    @Override
    public IDataStructure getDataStructure() {
        return dataStructure;
    }
    
    /**
     * Adds a binary tree listener to the binary tree.
     * 
     * @param listener the listener to add
     */
    public void addBinaryTreeListener(BinaryTreeListener
            listener) {
        listeners.add(BinaryTreeListener.class, listener);
    }
    
    /**
     * Removes a binary tree listener from the binary tree.
     * 
     * @param listener the listener to remove
     */
    public void removeBinaryTreeListener(BinaryTreeListener
            listener) {
        listeners.remove(BinaryTreeListener.class, listener);
    }
    
    /**
     * Adds a node to the binary tree.
     * 
     * @param key the key of the node
     */
    public void addNode(int key) {
        //TODO insertion
        fireBinaryTreeChanged(dataStructure.treeToArrayList());
        isBinaryTreeSaved = false;
    }
    
    /**
     * Deletes a node from the binary tree.
     * 
     * @param key the key of the node
     */
    public void deleteNode(int key) {
        //TODO deletion
        fireBinaryTreeChanged(dataStructure.treeToArrayList());
        isBinaryTreeSaved = false;
    }
    
    /**
     * Updates the view.
     */
    public void updateBinaryTreeView() {
        fireBinaryTreeChanged(dataStructure.treeToArrayList());
    }
    
    /**
     * Saves the binary tree into the selected file.
     * 
     * @param file the file
     * @throws IOException 
     */
    public void saveBinaryTree(String file) throws IOException {
        TreeFile.save(dataStructure, file);
        isBinaryTreeSaved = true;
    }
    
    private <N extends IBinaryNode<N>> void fireBinaryTreeChanged(List<N> data) {
        BinaryTreeListener[] listenerTab = 
            (BinaryTreeListener[])listeners.getListeners(
            BinaryTreeListener.class);
        for(BinaryTreeListener listener : listenerTab) {
            listener.binaryTreeChanged(
                new BinaryTreeEvent<N>(this, data)); 
        }
    }
}
