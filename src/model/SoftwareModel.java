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
 * This class defines all the methods in order to modify the software model. The
 * software model is composed by data structure models. It is possible to add a
 * software model listener to the model in order to listen the modifications of
 * the software model. This class is not designed for inheritance.
 *
 * This class is not designed for inheritance.
 * 
 * @author Julien Hannier
 * @version 1.00 16/06/08
 */
public final class SoftwareModel {

    private List<IDataStructureModel> dataStructureModels;

    private EventListenerList listeners;

    /**
     * Builds the software model. The software model is a list of data structure
     * models.
     */
    public SoftwareModel() {
        dataStructureModels = new ArrayList<IDataStructureModel>();
        listeners = new EventListenerList();
    }

    /**
     * Returns the wanted data structure model. The parameter {@code index}
     * indicates the place of the data structure model among the others.
     *
     * @param index the index of the data structure model
     * @return the wanted data structure model
     */
    public IDataStructureModel getDataStructureModel(int index) {
        return dataStructureModels.get(index);
    }

    /**
     * Adds a software model listener to the software model.
     * 
     * @param listener the listener to add
     */
    public void addSoftwareModelListener(SoftwareModelListener listener) {
        listeners.add(SoftwareModelListener.class, listener);
    }

    /**
     * Removes a software model listener from the software model.
     *
     * @param listener the listener to remove
     */
    public void removeSoftwareModelListener(SoftwareModelListener listener) {
        listeners.remove(SoftwareModelListener.class, listener);
    }

    /**
     * Adds a data structure model to the software model. The data structure is
     * already created into the data structure model {@code dataStructureModel}.
     * 
     * @param dataStructureModel the data structure model to add
     */
    public void addDataStructureModel(IDataStructureModel dataStructureModel) {
        dataStructureModels.add(dataStructureModel);
        fireModelHasChangedWithInsertEvent("New " +
                dataStructureModel.getDataStructure().getType());
    }

    /**
     * Adds a data structure model to the software model. The data structure is
     * already loaded from the file into the data structure model
     * {@code dataStructureModel}. The parameter {@code fileName} represents the
     * name of the data structure model.
     * 
     * @param dataStructureModel the data structure model to add
     * @param fileName the name of the file where the data structure model is
     */
    public void addDataStructureModelFromFile(
            IDataStructureModel dataStructureModel, String fileName) {
        dataStructureModels.add(dataStructureModel);
        fireModelHasChangedWithInsertEvent(fileName);
    }

    /**
     * Deletes the data structure model from the software model. The parameter
     * {@code index} indicates the place of the data structure model among the
     * others.
     * 
     * @param index the index of the data structure model to delete
     */
    public void deleteDataStructureModel(int index) {
        dataStructureModels.remove(index);
        fireModelHasChangedWithDeleteEvent(index);
    }

    /**
     * Removes all the data structure models from the software model.
     */
    public void removeAllDataStructureModels() {
        dataStructureModels.clear();
        fireModelHasChangedWithClearEvent();
    }

    private void fireModelHasChangedWithInsertEvent(String name) {
        SoftwareModelListener[] listenerTab = listeners.getListeners(
                SoftwareModelListener.class);
        for (SoftwareModelListener listener : listenerTab) {
            listener.modelHasChanged(SoftwareModelEvent.buildInsertEvent(this,
                    SoftwareModelEventType.INSERT, name));
        }
    }

    private void fireModelHasChangedWithDeleteEvent(int index) {
        SoftwareModelListener[] listenerTab = listeners.getListeners(
                SoftwareModelListener.class);
        for (SoftwareModelListener listener : listenerTab) {
            listener.modelHasChanged(SoftwareModelEvent.buildDeleteEvent(this,
                    SoftwareModelEventType.DELETE, index));
        }
    }

    private void fireModelHasChangedWithClearEvent() {
        SoftwareModelListener[] listenerTab = listeners.getListeners(
                SoftwareModelListener.class);
        for (SoftwareModelListener listener : listenerTab) {
            listener.modelHasChanged(SoftwareModelEvent.buildClearEvent(this,
                    SoftwareModelEventType.CLEAR));
        }
    }
}
