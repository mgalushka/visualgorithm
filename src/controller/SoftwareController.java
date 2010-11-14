/*
 * SoftwareController.java v0.10 16/06/08
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.ISoftwareModel;
import model.UnknownDataStructureException;
import view.AbstractViewFactory;
import view.ISoftwareView;

/**
 * This class defines the controller of the software. The software controller is
 * composed by the software model and data structure controllers. This class is
 * not designed for inheritance. The class <tt>SoftwareController</tt> is
 * implemented to manage all types of data structure controllers. In fact, all
 * the data structure controllers are added thanks to the design pattern
 * prototype. If you would like to add other data structure controllers, do not
 * forget to register your data structure controllers in the data structure
 * {@code dataStructureControllerPrototypes} in this class.
 * 
 * @author Julien Hannier
 * @version 0.10 16/06/08
 * @see ISoftwareController
 */
public final class SoftwareController implements ISoftwareController {

    private ISoftwareModel softwareModel;

    private AbstractViewFactory softwareViewFactory;

    private ISoftwareView softwareView;

    private List<IDataStructureController> dataStructureControllers;

    /**
     * The data structure controller prototypes associates each type of data
     * structure with the good type of data structure controller.
     */
    private static final HashMap<String, IDataStructureController> dataStructureControllerPrototypes =
            new HashMap<String, IDataStructureController>();

    /**
     * Registration of the data structure controllers according to the types of
     * data structures. You have to register new data structure controllers here.
     */
    static {
        dataStructureControllerPrototypes.put(
                BinaryTreeController.BINARY_TREE_FILE_EXTENSION, new BinaryTreeController());
    }

    /**
     * Builds the software controller. The software controller is composed by a
     * software model and data structure controllers. It also contains the
     * software view.
     * 
     * @param model the software model
     * @param viewFactory the view factory
     */
    public SoftwareController(ISoftwareModel model, AbstractViewFactory viewFactory) {
        dataStructureControllers = new ArrayList<IDataStructureController>();
        softwareModel = model;
        softwareViewFactory = viewFactory;
        softwareView = softwareViewFactory.createSoftwareView(this);
        
        softwareView.displayView();
    }

    @Override
    public ISoftwareView getView() {
        return softwareView;
    }

    @Override
    public IDataStructureController getDataStructureController(int index)
            throws IndexOutOfBoundsException {
        if (index >= dataStructureControllers.size()) {
            throw new IndexOutOfBoundsException("You have given an index out" +
                " of bounds");
        }
        return dataStructureControllers.get(index);
    }

    @Override
    public void addDataStructure(String id, Object type)
            throws IllegalArgumentException {
        if (!dataStructureControllerPrototypes.containsKey(id)) {
            throw new IllegalArgumentException("The identifiant (id) does not" +
                    "correspond to a data structure controller");
        }
        IDataStructureController dataStructureControllerPrototype =
                dataStructureControllerPrototypes.get(id);
        IDataStructureController dataStructureController =
                dataStructureControllerPrototype.clone();

        dataStructureController.initializeDataStructureController(type,
                softwareViewFactory);
        dataStructureControllers.add(dataStructureController);
        dataStructureController.addDataStructureModelToSoftwareModel(softwareModel);
        softwareView.addDataStructureView("New " + type.toString(), dataStructureController.getView());
    }

    @Override
    public void addDataStructure(String id, Object type, int nb)
            throws IllegalArgumentException {
        if (!dataStructureControllerPrototypes.containsKey(id)) {
            throw new IllegalArgumentException("The identifiant (id) does not" +
                    "correspond to a data structure controller");
        }
        IDataStructureController dataStructureControllerPrototype =
                dataStructureControllerPrototypes.get(id);
        IDataStructureController dataStructureController =
                dataStructureControllerPrototype.clone();

        dataStructureController.initializeDataStructureController(type,
                softwareViewFactory, nb);
        dataStructureControllers.add(dataStructureController);
        dataStructureController.addDataStructureModelToSoftwareModel(softwareModel);
        softwareView.addDataStructureView("New " + type.toString(), dataStructureController.getView());
    }

    @Override
    public void addDataStructure(File file)
            throws IllegalArgumentException, IOException, FileNotFoundException,
            ParseException, UnknownDataStructureException {
        String fileName = file.getName();
        String extension = fileName.substring(
                fileName.lastIndexOf('.') + 1).toLowerCase();

        if (!dataStructureControllerPrototypes.containsKey(extension)) {
            throw new IllegalArgumentException("The file extension does not" +
                    "correspond to a data structure controller");
        }
        IDataStructureController dataStructureControllerPrototype =
                dataStructureControllerPrototypes.get(extension);
        IDataStructureController dataStructureController =
                dataStructureControllerPrototype.clone();
        
        dataStructureController.initializeDataStructureController(file,
                softwareViewFactory);
        dataStructureControllers.add(dataStructureController);
        dataStructureController.addDataStructureModelToSoftwareModel(softwareModel);
        softwareView.addDataStructureView(fileName, dataStructureController.getView());
    }

    @Override
    public void deleteDataStructure(int index) throws IndexOutOfBoundsException {
        if (index >= dataStructureControllers.size()) {
            throw new IndexOutOfBoundsException("You have given an index out" +
                " of bounds");
        }
        softwareModel.deleteDataStructureModel(index);
        dataStructureControllers.remove(index);
        softwareView.deleteDataStructureView(index);
    }

    @Override
    public void saveDataStructure(File file, int index)
            throws IndexOutOfBoundsException, IOException {
        getDataStructureController(index).saveDataStructureModel(file);
    }

    @Override
    public void removeAllDataStructureModelsAndControllers() {
        softwareModel.removeAllDataStructureModels();
        dataStructureControllers.clear();
    }
}
