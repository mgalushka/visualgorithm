/*
 * ModelEvent.java v1.00 16/06/08
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

import java.util.EventObject;


/**
 * Definition of model event.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class ModelEvent extends EventObject {
    
    /**
     * Enumeration of the model event type.
     * 
     * @author Julien Hannier
     * @author Pierre Pironin
     * @author Damien Rigoni
     * @version 1.00 16/06/08
     */
    public enum ModelEventType {ADD, DELETE, EXIT};
    
    private static final long serialVersionUID = 1L;
    
    private ModelEventType type;
    
    private DataStructureType dataStructureType;
    
    private int index;
    
    /**
     * Builds a model event.
     * 
     * @param source the source of the model event
     * @param t the type of the model event
     * @param dst the type of the data structure
     */
    public ModelEvent(Object source, ModelEventType t, DataStructureType dst) {
        super(source);
        type = t;
        dataStructureType = dst;
        index = -1;
    }
    
    /**
     * Builds a model event.
     * 
     * @param source the source of the model event
     * @param t the type of the model event
     * @param i the index of the data structure
     */
    public ModelEvent(Object source, ModelEventType t, int i) {
        super(source);
        type = t;
        dataStructureType = null;
        index = i;
    }
    
    /**
     * Builds a model event.
     * 
     * @param source the source of the model event
     * @param t the type of the model event
     */
    public ModelEvent(Object source, ModelEventType t) {
        super(source);
        type = t;
        dataStructureType = null;
        index = -1;
    }
    
    /**
     * Returns the type of the model event.
     * 
     * @return the type of the model event
     */
    public ModelEventType getType() {
        return type;
    }
    
    /**
     * Returns the type of the data structure.
     * 
     * @return the type of the data structure
     */
    public DataStructureType getDataStructureType() {
        return dataStructureType;
    }
    
    /**
     * Returns the index of the data structure.
     * 
     * @return the index of the data structure
     */
    public int getIndex() {
        return index;
    }
}
