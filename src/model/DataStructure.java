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

package model;

import io.TreeFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.event.EventListenerList;

import model.DataStructureEvent.DataStructureEventType;
import model.tree.IBinaryTree;
import model.tree.UnknownTreeTypeException;

/**
 * Definition of the data structure.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class DataStructure {

    private IDataStructure dataStructure;
    
    private EventListenerList listeners;
    
    /**
     * Builds the data structure.
     * 
     * @param type the type of the data structure
     */
    public DataStructure(DataStructureType type) {
        dataStructure = 
            DataStructureFactory.createDataStructure(type);
        listeners = new EventListenerList();
    }
    
    /**
     * Builds the data structure.
     * 
     * @param file the file containing the data structure
     * @throws UnknownTreeTypeException 
     * @throws IOException 
     * @throws ParseException 
     * @throws FileNotFoundException 
     */
    public DataStructure(File file) throws FileNotFoundException,
            ParseException, IOException, UnknownTreeTypeException {
        //TODO data structure file
        dataStructure = TreeFile.load(file.getAbsolutePath());
        listeners = new EventListenerList();
    }
    
    /**
     * Returns the data structure.
     * 
     * @return the data structure
     */
    public IDataStructure getDataStructure() {
        return dataStructure;
    }
    
    /**
     * Adds a data structure listener to the data structure.
     * 
     * @param listener the listener to add
     */
    public void addDataStructureListener(DataStructureListener
            listener) {
        listeners.add(DataStructureListener.class, listener);
    }
    
    /**
     * Removes a data structure listener from the data structure.
     * 
     * @param listener the listener to remove
     */
    public void removeDataStructureListener(DataStructureListener
            listener) {
        listeners.remove(DataStructureListener.class, listener);
    }
    
    /**
     * Adds a node to the tree data structure.
     * 
     * @param key the key of the node
     */
    public void addNode(int key) {
        //TODO insertion
        fireDataStructureChanged(DataStructureEventType.TREE,
            ((IBinaryTree<?>)dataStructure).treeToArrayList());
    }
    
    /**
     * Deletes a node from the tree data structure.
     * 
     * @param key the key of the node
     */
    public void deleteNode(int key) {
        //TODO deletion
        fireDataStructureChanged(DataStructureEventType.TREE,
            ((IBinaryTree<?>)dataStructure).treeToArrayList());
    }
    
    /**
     * Deletes a node from the tree data structure.
     */
    public void update() {
        //TODO type of the data structure
        fireDataStructureChanged(DataStructureEventType.TREE,
            ((IBinaryTree<?>)dataStructure).treeToArrayList());
    }
    
    /**
     * Saves the data structure into the selected file.
     * 
     * @param file the file
     * @throws IOException 
     */
    public void save(File file) throws IOException {
        //TODO data structure file
        TreeFile.save((IBinaryTree<?>)dataStructure, file.getAbsolutePath());
    }
    
    private void fireDataStructureChanged(
            DataStructureEventType type, Object data) {
        DataStructureListener[] listenerTab = 
            (DataStructureListener[])listeners.getListeners(
            DataStructureListener.class);
        for(DataStructureListener listener : listenerTab) {
            listener.dataStructureChanged(
                new DataStructureEvent(this, type, data)); 
        }
    }
}
