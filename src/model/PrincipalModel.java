/*
 * PrincipalModel.java v1.00 16/06/08
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

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.EventListenerList;
import model.PrincipalModelEvent.ModelEventType;

/**
 * Definition of the principal model.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class PrincipalModel {

    private List<ISubModel> model;

    private EventListenerList listeners;

    /**
     * Builds the principal model. The principal model is a list of data
     * structures.
     */
    public PrincipalModel() {
        model = new ArrayList<ISubModel>();
        listeners = new EventListenerList();
    }

    /**
     * Returns the data structure indicated with index.
     * 
     * @param index the index of the data structure
     * @return the data structure
     */
    public ISubModel getSubModel(int index) {
        return model.get(index);
    }

    /**
     * Adds a principal model listener to the principal model.
     * 
     * @param listener the listener to add
     */
    public void addModelListener(PrincipalModelListener listener) {
        listeners.add(PrincipalModelListener.class, listener);
    }

    /**
     * Removes a principal model listener from the principal model.
     * 
     * @param listener the listener to remove
     */
    public void removeModelListener(PrincipalModelListener listener) {
        listeners.remove(PrincipalModelListener.class, listener);
    }

    /**
     * Adds a data structure to the principal model.
     * 
     * @param subModel a data structure
     */
    public void addSubModel(ISubModel subModel) {
        model.add(subModel);
        fireModelChanged(ModelEventType.ADD, "New "
                + subModel.getDataStructure().getType().toString());
    }

    /**
     * Removes the data structure indicated with index.
     * 
     * @param index the index of the data structure
     */
    public void removeSubModel(int index) {
        model.remove(index);
        fireModelChanged(ModelEventType.DELETE, index);
    }

    /**
     * Removes all data structures in the principal model.
     */
    public void removeAllSubModels() {
        model.clear();
        fireModelChanged(ModelEventType.EXIT);
    }

    /**
     * Adds a loaded data structure to the principal model.
     * 
     * @param subModel a data structure
     * @param fileName the name of the file
     */
    public void openSubModelFile(ISubModel subModel, String fileName) {
        model.add(subModel);
        fireModelChanged(ModelEventType.ADD, fileName);
    }

    private void fireModelChanged(ModelEventType type, String name) {
        PrincipalModelListener[] listenerTab = (PrincipalModelListener[]) listeners
                .getListeners(PrincipalModelListener.class);
        for (PrincipalModelListener listener : listenerTab) {
            listener.modelChanged(new PrincipalModelEvent(this, type, name));
        }
    }

    private void fireModelChanged(ModelEventType type, int index) {
        PrincipalModelListener[] listenerTab = (PrincipalModelListener[]) listeners
                .getListeners(PrincipalModelListener.class);
        for (PrincipalModelListener listener : listenerTab) {
            listener.modelChanged(new PrincipalModelEvent(this, type, index));
        }
    }

    private void fireModelChanged(ModelEventType type) {
        PrincipalModelListener[] listenerTab = (PrincipalModelListener[]) listeners
                .getListeners(PrincipalModelListener.class);
        for (PrincipalModelListener listener : listenerTab) {
            listener.modelChanged(new PrincipalModelEvent(this, type));
        }
    }
}
