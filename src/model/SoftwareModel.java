/*
 * SoftwareModel.java v1.00 16/06/08
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
import model.SoftwareModelEvent.SoftwareModelEventType;

/**
 * Definition of the software model.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class SoftwareModel {

    private List<ITabModel> tabModels;

    private EventListenerList listeners;

    /**
     * Builds the software model. The software model is a list of
     * tab models.
     */
    public SoftwareModel() {
        tabModels = new ArrayList<ITabModel>();
        listeners = new EventListenerList();
    }

    /**
     * Returns the tab model indicated with index.
     * 
     * @param index the index of the tab model
     * @return the tab model
     */
    public ITabModel getTabModel(int index) {
        return tabModels.get(index);
    }

    /**
     * Adds a software model listener to the software model.
     * 
     * @param listener the listener to add
     */
    public void addModelListener(SoftwareModelListener listener) {
        listeners.add(SoftwareModelListener.class, listener);
    }

    /**
     * Removes a software model listener from the software model.
     * 
     * @param listener the listener to remove
     */
    public void removeModelListener(SoftwareModelListener listener) {
        listeners.remove(SoftwareModelListener.class, listener);
    }

    /**
     * Adds a tab model to the software model.
     * 
     * @param tabModel a tab model
     */
    public void addTabModel(ITabModel tabModel) {
        tabModels.add(tabModel);
        fireModelChanged(SoftwareModelEventType.ADD, "New "
                + tabModel.getTabModel().getType().toString());
    }

    /**
     * Removes the tab model indicated with index.
     * 
     * @param index the index of the tab model
     */
    public void removeTabModel(int index) {
        tabModels.remove(index);
        fireModelChanged(SoftwareModelEventType.DELETE, index);
    }

    /**
     * Removes all tab models in the software model.
     */
    public void removeAllTabModels() {
        tabModels.clear();
        fireModelChanged(SoftwareModelEventType.EXIT);
    }

    /**
     * Adds a tab model to the software model. The tab model
     * has a loaded data structure.
     * 
     * @param tabModel a tab model
     * @param fileName the name of the file
     */
    public void addTabModelFromDataStructureFile(ITabModel tabModel, String fileName) {
        tabModels.add(tabModel);
        fireModelChanged(SoftwareModelEventType.ADD, fileName);
    }

    private void fireModelChanged(SoftwareModelEventType type, String name) {
        SoftwareModelListener[] listenerTab = (SoftwareModelListener[]) listeners
                .getListeners(SoftwareModelListener.class);
        for (SoftwareModelListener listener : listenerTab) {
            listener.modelChanged(new SoftwareModelEvent(this, type, name));
        }
    }

    private void fireModelChanged(SoftwareModelEventType type, int index) {
        SoftwareModelListener[] listenerTab = (SoftwareModelListener[]) listeners
                .getListeners(SoftwareModelListener.class);
        for (SoftwareModelListener listener : listenerTab) {
            listener.modelChanged(new SoftwareModelEvent(this, type, index));
        }
    }

    private void fireModelChanged(SoftwareModelEventType type) {
        SoftwareModelListener[] listenerTab = (SoftwareModelListener[]) listeners
                .getListeners(SoftwareModelListener.class);
        for (SoftwareModelListener listener : listenerTab) {
            listener.modelChanged(new SoftwareModelEvent(this, type));
        }
    }
}
