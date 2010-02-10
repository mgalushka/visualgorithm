/*
 * ISoftwareController.java v1.00 06/12/09
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

package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import model.UnknownDataStructureException;
import view.ISoftwareView;

/**
 * This interface defines the controller of the software that is to say all the
 * methods for the software view to communicate with the software model. It must
 * be implemented by the software controller. It is principally used in the view
 * of the software.
 *
 * @author Julien Hannier
 * @version 1.00 06/12/09
 * @see IController
 */
public interface ISoftwareController extends IController {

    @Override
    public ISoftwareView getView();

    /**
     * Returns the data structure controller indicated by {@code index}. If the
     * index is out of bounds, then an IndexOutOfBoundsException is thrown.
     *
     * @param index the index of the wanted data structure controller
     * @return the wanted data structure controller
     * @throws IndexOutOfBoundsException
     */
    public IDataStructureController getDataStructureController(int index)
            throws IndexOutOfBoundsException;

    /**
     * Adds a data structure model and controller with an empty data structure.
     * If the identifiant of the data structure controller does not correspond
     * to a type of data structure controller, then an IllegalArgumentException
     * is thrown.
     *
     * @param id the identifiant of a type of data structure controller
     * @param type the type of the data structure
     * @throws IllegalArgumentException
     */
    public void addDataStructure(String id, Object type)
            throws IllegalArgumentException;

    /**
     * Adds a data structure model and controller with a data structure created
     * with random elements. If the identifiant of the data structure controller
     * does not correspond to a type of data structure controller, then an
     * IllegalArgumentException is thrown.
     *
     * @param id the identifiant of a type of data structure controller
     * @param type the type of the data structure
     * @param nb the number of random elements
     * @throws IllegalArgumentException
     */
    public void addDataStructure(String id, Object type, int nb)
            throws IllegalArgumentException;

    /**
     * Adds a data structure model and controller from a file containing the
     * data structure. If the extension of the file does not correspond to a
     * type of data structure controller, then an IllegalArgumentException is
     * thrown.
     *
     * @param file the file containing the data structure
     * @throws IllegalArgumentException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws UnknownDataStructureException
     */
    public void addDataStructure(File file)
            throws IllegalArgumentException, IOException, FileNotFoundException,
            ParseException, UnknownDataStructureException;

    /**
     * Removes the data structure model and controller indicated by
     * {@code index}. If the index is out of bounds, then an
     * IndexOutOfBoundsException is thrown.
     *
     * @param index the index of the data structure controller to delete
     * @throws IndexOutOfBoundsException
     */
    public void deleteDataStructure(int index) throws IndexOutOfBoundsException;

    /**
     * Saves the data structure model from the data structure controller
     * indicated by {@code index} into the selected file. If the index of the
     * data structure controller is out of bounds, then an
     * IndexOutOfBoundsException is thrown.
     *
     * @param file the file where to save
     * @param index the index of the data structure controller to save
     * @throws IndexOutOfBoundsException
     * @throws IOException
     */
    public void saveDataStructure(File file, int index)
            throws IndexOutOfBoundsException, IOException;
    
    /**
     * Removes all data structure models and controllers.
     */
    public void removeAllDataStructureModelsAndControllers();
}
