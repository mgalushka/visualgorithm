/*
 * DataStructureEvent.java v1.00 16/06/08
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
 * Definition of the data structure event.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class DataStructureEvent extends EventObject {

    /**
     * Enumeration of the data structure event type.
     * 
     * @author Julien Hannier
     * @author Pierre Pironin
     * @author Damien Rigoni
     * @version 1.00 16/06/08
     */
    public enum DataStructureEventType {TREE};
    
    private static final long serialVersionUID = 1L;
    
    private DataStructureEventType type;

    private Object data;

    /**
     * Builds a data structure event.
     * 
     * @param source the source of the data structure event
     * @param t the type of the data structure event
     * @param d the data of the data structure event
     */
    public DataStructureEvent(Object source, DataStructureEventType t, Object d) {
        super(source);
        type = t;
        data = d;
    }
    
    /**
     * Returns the type of the data structure event.
     * 
     * @return the type of the data structure event
     */
    public DataStructureEventType getType() {
        return type;
    }
    
    /**
     * Returns the data of the data structure event.
     * 
     * @return the data of the data structure event
     */
    public Object getData() {
        return data;
    }
}