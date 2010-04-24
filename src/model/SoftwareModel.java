/*
 * SoftwareModel.java v0.10 16/06/08
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
import java.util.EventListener;
import java.util.List;
import javax.swing.event.EventListenerList;
import model.SoftwareModelEvent.SoftwareModelEventType;

/**
 * This class defines all the methods in order to modify the software model. The
 * software model is composed by data structure models. There is a data
 * structure model for each data structure in the software. This class is not
 * designed for inheritance.
 * 
 * @author Julien Hannier
 * @version 0.10 16/06/08
 * @see ISoftwareModel
 */
public final class SoftwareModel implements ISoftwareModel {

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

    @Override
    public IDataStructureModel getDataStructureModel(int index)
            throws IndexOutOfBoundsException {
        if (index >= dataStructureModels.size()) {
            throw new IndexOutOfBoundsException("You have given an index out" +
                " of bounds");
        }
        return dataStructureModels.get(index);
    }

    @Override
    public void addModelListener(EventListener listener) throws IllegalArgumentException {
        if (!(listener instanceof SoftwareModelListener)) {
            throw new IllegalArgumentException("You have to pass a SoftwareModelListener");
        }
        listeners.add(SoftwareModelListener.class, (SoftwareModelListener)listener);
    }

    @Override
    public void removeModelListener(EventListener listener) throws IllegalArgumentException {
        if (!(listener instanceof SoftwareModelListener)) {
            throw new IllegalArgumentException("You have to pass a SoftwareModelListener");
        }
        listeners.remove(SoftwareModelListener.class, (SoftwareModelListener)listener);
    }

    @Override
    public void addDataStructureModel(IDataStructureModel dataStructureModel) {
        dataStructureModels.add(dataStructureModel);
        fireModelHasChangedWithInsertEvent("New " +
                dataStructureModel.getDataStructureType());
    }

    @Override
    public void addDataStructureModelFromFile(
            IDataStructureModel dataStructureModel, String fileName) {
        dataStructureModels.add(dataStructureModel);
        fireModelHasChangedWithInsertEvent(fileName);
    }

    @Override
    public void deleteDataStructureModel(int index) throws IndexOutOfBoundsException {
        if (index >= dataStructureModels.size()) {
            throw new IndexOutOfBoundsException("You have given an index out" +
                " of bounds");
        }
        dataStructureModels.remove(index);
        fireModelHasChangedWithDeleteEvent(index);
    }

    @Override
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
