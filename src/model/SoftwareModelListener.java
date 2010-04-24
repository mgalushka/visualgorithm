/*
 * SoftwareModelListener.java v0.10 16/06/08
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

import java.util.EventListener;

/**
 * This interface contains all the methods for the software model listener. It
 * must be implemented by all the classes that want to listen to the software
 * model in order to be notified of a modification of the software model.
 * 
 * @author Julien Hannier
 * @version 0.10 16/06/08
 * @see SoftwareModelEvent
 */
public interface SoftwareModelListener extends EventListener {

    /**
     * This method is called when the software model has changed. The software
     * model changes when a data structure is added or deleted or when the
     * software is closed. The software model event will contain the type of
     * change and informations needed for the type of change.
     * 
     * @param event the software model event
     */
    public void modelHasChanged(SoftwareModelEvent event);
}
