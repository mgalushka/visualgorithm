/*
 * SoftwareModelEvent.java v1.00 16/06/08
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

package model;

import java.util.EventObject;

/**
 * Definition of the software model event.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class SoftwareModelEvent extends EventObject {

    /**
     * Enumeration of the software model event type.
     * 
     * @author Julien Hannier
     * @author Pierre Pironin
     * @author Damien Rigoni
     * @version 1.00 16/06/08
     */
    public enum SoftwareModelEventType {
        ADD, DELETE, EXIT
    };

    private static final long serialVersionUID = 1L;

    private SoftwareModelEventType type;

    private String name;

    private int index;

    /**
     * Builds a software model event for the type ADD. If the data structure
     * is saved into a file, name is the file name or else name is the
     * type of the data structure.
     * 
     * @param source the source of the software model event
     * @param t the type of the software model event
     * @param n the name
     */
    public SoftwareModelEvent(Object source, SoftwareModelEventType t, String n) {
        super(source);
        type = t;
        name = n;
        index = -1;
    }

    /**
     * Builds a software model event for the type DELETE.
     * 
     * @param source the source of the software model event
     * @param t the type of the software model event
     * @param i the index of the data structure
     */
    public SoftwareModelEvent(Object source, SoftwareModelEventType t, int i) {
        super(source);
        type = t;
        name = null;
        index = i;
    }

    /**
     * Builds a software model event for the type EXIT.
     * 
     * @param source the source of the software model event
     * @param t the type of the model event
     */
    public SoftwareModelEvent(Object source, SoftwareModelEventType t) {
        super(source);
        type = t;
        name = null;
        index = -1;
    }

    /**
     * Returns the type of the software model event.
     * 
     * @return the type of the software model event
     */
    public SoftwareModelEventType getType() {
        return type;
    }

    /**
     * Returns the name of the data structure. If the data structure
     * is saved into a file, returns the name of the file or else returns
     * the type of the data structure.
     * 
     * @return the name
     */
    public String getName() {
        return name;
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
