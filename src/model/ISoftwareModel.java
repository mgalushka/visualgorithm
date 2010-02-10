/*
 * ISoftwareModel.java v1.00 06/12/09
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

/**
 * This interface defines the software model. It must be implemented by the
 * software model. It is principally used in the software controller.
 *
 * @author Julien Hannier
 * @version 1.00 06/12/09
 * @see IModel
 */
public interface ISoftwareModel extends IModel {

    /**
     * Returns the wanted data structure model. The parameter {@code index}
     * indicates the place of the data structure model among the others. If the
     * index is out of bounds, then an IndexOutOfBoundsException is thrown.
     *
     * @param index the index of the data structure model
     * @return the wanted data structure model
     * @throws IndexOutOfBoundsException
     */
    public IDataStructureModel getDataStructureModel(int index) throws IndexOutOfBoundsException;

    /**
     * Adds a data structure model to the software model. The data structure is
     * already created into the data structure model {@code dataStructureModel}.
     *
     * @param dataStructureModel the data structure model to add
     */
    public void addDataStructureModel(IDataStructureModel dataStructureModel);

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
            IDataStructureModel dataStructureModel, String fileName);

    /**
     * Deletes the data structure model from the software model. The parameter
     * {@code index} indicates the place of the data structure model among the
     * others. If the index is out of bounds, then an IndexOutOfBoundsException
     * is thrown.
     *
     * @param index the index of the data structure model to delete
     * @throws IndexOutOfBoundsException
     */
    public void deleteDataStructureModel(int index) throws IndexOutOfBoundsException;

    /**
     * Removes all the data structure models from the software model.
     */
    public void removeAllDataStructureModels();
}
