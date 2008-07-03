/*
 * Model.java v1.00 16/06/08
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

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.EventListenerList;

import model.ModelEvent.ModelEventType;

/**
 * Definition of the model.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class Model {

    private List<DataStructure> model;
    
    private EventListenerList listeners;
    
    /**
     * Builds the model. The model is a list of
     * data structures.
     */
    public Model() {
        model = new ArrayList<DataStructure>();
        listeners = new EventListenerList();
    }
    
    /**
     * Returns the data structure indicated with index.
     * 
     * @param index the index of the data structure
     * @return the data structure
     */
    public DataStructure getDataStructure(int index) {
        return model.get(index);
    }
    
    /**
     * Adds a model listener to the model.
     * 
     * @param listener the listener to add
     */
    public void addModelListener(ModelListener listener) {
        listeners.add(ModelListener.class, listener);
    }
    
    /**
     * Removes a model listener from the model.
     * 
     * @param listener the listener to remove
     */
    public void removeModelListener(ModelListener listener) {
        listeners.remove(ModelListener.class, listener);
    }
    
    /**
     * Adds a data structure to the model.
     * 
     * @param dataStructure a data structure
     */
    public void addDataStructure(DataStructure dataStructure) {
        model.add(dataStructure);
        fireModelChanged(ModelEventType.ADD,
            dataStructure.getDataStructure().getType().toString());
    }
    
    /**
     * Removes the data structure indicated with index.
     * 
     * @param index the index of the data structure
     */
    public void removeDataStructure(int index) {
        model.remove(index);
        fireModelChanged(ModelEventType.DELETE, index);
    }
    
    /**
     * Removes all data structures in the model.
     */
    public void removeAllDataStructure() {
        model.clear();
        fireModelChanged(ModelEventType.EXIT);
    }
    
    /**
     * Adds a loaded data structure to the model.
     * 
     * @param dataStructure a data structure
     * @param fileName the name of the file
     */
    public void openDataStructureFile(DataStructure dataStructure,
            String fileName) {
        model.add(dataStructure);
        fireModelChanged(ModelEventType.OPEN, fileName);
    }
    
    /**
     * Launches a model event which the type is SAVE.
     * 
     * @param index the index of the data structure
     * @param fileName the name of the file
     */
    public void saveDataStructure(int index, String fileName) {
        fireModelChanged(ModelEventType.SAVE, index, fileName);
    }
    
    private void fireModelChanged(ModelEventType type,
            String name) {
        ModelListener[] listenerTab = (ModelListener[])
            listeners.getListeners(ModelListener.class);
        for(ModelListener listener : listenerTab) {
            listener.modelChanged(new ModelEvent(this,
                type, name)); 
        }
    }
    
    private void fireModelChanged(ModelEventType type,
            int index, String name) {
        ModelListener[] listenerTab = (ModelListener[])
            listeners.getListeners(ModelListener.class);
        for(ModelListener listener : listenerTab) {
            listener.modelChanged(new ModelEvent(
                this, type, index, name)); 
        }
    }
    
    private void fireModelChanged(ModelEventType type,
            int index) {
        ModelListener[] listenerTab = (ModelListener[])
            listeners.getListeners(ModelListener.class);
        for(ModelListener listener : listenerTab) {
            listener.modelChanged(new ModelEvent(
                this, type, index)); 
        }
    }
    
    private void fireModelChanged(ModelEventType type) {
        ModelListener[] listenerTab = (ModelListener[])
            listeners.getListeners(ModelListener.class);
        for(ModelListener listener : listenerTab) {
            listener.modelChanged(new ModelEvent(this, type)); 
        }
    }
}