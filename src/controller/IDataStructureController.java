/*
 * IDataStructureController.java v0.10 07/07/08
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
import model.ISoftwareModel;
import view.AbstractViewFactory;
import model.UnknownDataStructureException;
import view.IDataStructureView;

/**
 * This interface defines data structure controllers. It must be implemented by
 * controllers of each kind of data structure directly or through a more specific
 * interface. These controllers will be instanciated thanks to the design pattern
 * prototype so they must have a constructor without parameters. The
 * {@code void initializeDataStructureController()} methods will take care of
 * the initialization of the object. It is principally used in the software
 * controller.
 * 
 * @author Julien Hannier
 * @version 0.10 07/07/08
 * @see IController
 */
public interface IDataStructureController extends IController {

    @Override
    public IDataStructureView getView();

    /**
     * Clones the data structure controller object that is to say creates a new
     * one and returns it.
     *
     * @return the cloned data structure controller
     */
    public IDataStructureController clone();

    /**
     * Initializes the data structure controller with an empty data structure.
     * If {@code type} is not the type corresponding to the concrete data
     * structure controller then an IllegalArgumentException is thrown.
     * 
     * @param type the type of the data structure
     * @param viewFactory the view factory
     * @throws IllegalArgumentException
     */
    public void initializeDataStructureController(Object type,
            AbstractViewFactory viewFactory)
            throws IllegalArgumentException;

    /**
     * Initializes the data structure controller with a data structure created
     * with random elements. If {@code type} is not the type corresponding to
     * the concrete data structure controller then an IllegalArgumentException
     * is thrown.
     * 
     * @param type the type of the data structure
     * @param viewFactory the view factory
     * @param nb the number of random elements
     * @throws IllegalArgumentException
     */
    public void initializeDataStructureController(Object type,
            AbstractViewFactory viewFactory, int nb)
            throws IllegalArgumentException;

    /**
     * Initializes the data structure controller with a data structure from a
     * file.
     * 
     * @param file the file containing the data structure
     * @param viewFactory the view factory
     * @throws UnknownDataStructureException
     * @throws IOException
     * @throws ParseException
     * @throws FileNotFoundException
     */
    public void initializeDataStructureController(File file,
            AbstractViewFactory viewFactory)
            throws FileNotFoundException, ParseException, IOException,
            UnknownDataStructureException;

    /**
     * Adds the data structure model to the software model {@code softwareModel}.
     *
     * @param softwareModel the software model where to add
     */
    public void addDataStructureModelToSoftwareModel(ISoftwareModel softwareModel);

    /**
     * Saves the data structure model into the selected file.
     * 
     * @param file the file where to save
     * @throws IOException
     */
    public void saveDataStructureModel(File file) throws IOException;

    /**
     * Returns true if the data structure model corresponding to the controller
     * has been saved.
     * 
     * @return true if the data structure model has been saved
     */
    public boolean isDataStructureModelSaved();
}
