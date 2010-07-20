/*
 * SoftwareModelEvent.java v0.10 16/06/08
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
 * This class defines the software model event. It is used to indicate the
 * changes of the software model to the software model listeners. In order to do
 * so, there is three different types of software model event : <tt>CLEAR</tt>,
 * <tt>DELETE</tt> and <tt>INSERT</tt>. It is not designed for inheritance.
 *
 * @author Julien Hannier
 * @version 0.10 16/06/08
 */
public class SoftwareModelEvent extends EventObject {

    /**
     * Enumeration that defines the software model event type. There is
     * <tt>CLEAR</tt> that is for the exit of the software and so for the
     * deletion of the content of the software model, <tt>DELETE</tt> that is
     * for the deletion of a data structure model, and finally <tt>INSERT</tt>
     * that is for the insertion of a data structure model.
     * 
     * @author Julien Hannier
     * @version 0.10 16/06/08
     */
    public enum SoftwareModelEventType {
        CLEAR, DELETE, INSERT
    }

    private static final long serialVersionUID = 1L;

    private SoftwareModelEventType eventType;

    private String dataStructureModelName;

    private int dataStructureModelIndex;

    private SoftwareModelEvent(Object source, SoftwareModelEventType type) {
    	this(source, type, "", -1);
    }

    private SoftwareModelEvent(Object source, SoftwareModelEventType type,
            int index) {
    	this(source, type, "", index);
    }

    private SoftwareModelEvent(Object source, SoftwareModelEventType type,
            String name) {
    	this(source, type, name, -1);
    }

    public SoftwareModelEvent(Object source, SoftwareModelEventType type,
    		String name, int index) {
		super(source);
		eventType = type;
		dataStructureModelName = name;
		dataStructureModelIndex = -1;
	}

	/**
     * Builds a software model event for the event type CLEAR. In this case,
     * there is no need for a data structure model name or a data structure
     * model index because the event type CLEAR is for the exit of the software
     * and so for the deletion of the content of the software model event.
     *
     * @param source the source of the software model event
     * @param type the type of the software model event
     */
    public static SoftwareModelEvent buildClearEvent(Object source,
            SoftwareModelEventType type) {
        return new SoftwareModelEvent(source, type);
    }

    /**
     * Builds a software model event for the event type DELETE. In this case,
     * there is no need for a data structure model name because the event type
     * DELETE is for the deletion of a data structure model and so just the
     * index is needed.
     * 
     * @param source the source of the software model event
     * @param type the type of the software model event
     * @param index the index of the data structure model
     */
    public static SoftwareModelEvent buildDeleteEvent(Object source,
            SoftwareModelEventType type, int index) {
        return new SoftwareModelEvent(source, type, index);
    }

    /**
     * Builds a software model event for the event type INSERT. In this case,
     * there is no need for a data structure model index because the event type
     * INSERT is for the insertion of a data structure model and so just the
     * name is needed.
     *
     * @param source the source of the software model event
     * @param type the type of the software model event
     * @param name the name of the data structure model
     */
    public static SoftwareModelEvent buildInsertEvent(Object source,
            SoftwareModelEventType type, String name) {
        return new SoftwareModelEvent(source, type, name);
    }

    /**
     * Returns the type of the software model event that is to say CLEAR,
     * DELETE or INSERT.
     * 
     * @return the type of the software model event
     * @see SoftwareModelEventType
     */
    public SoftwareModelEventType getEventType() {
        return eventType;
    }

    /**
     * Returns the name of the data structure model. If the data structure model
     * is saved into a file, it is the name of the file or else it is the type
     * of the data structure model.
     * 
     * @return the name of the data structure model
     */
    public String getDataStructureModelName() {
        return dataStructureModelName;
    }

    /**
     * Returns the index of the data structure model. This index indicates where
     * is the data structure model among the others.
     * 
     * @return the index of the data structure model
     */
    public int getDataStructureModelIndex() {
        return dataStructureModelIndex;
    }
}
