/*
 * SoftwareController.java v1.00 16/06/08
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
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.SoftwareModel;
import model.UnknownDataStructureException;
import view.AbstractViewFactory;
import view.ISoftwareView;

/**
 * This class defines the controller of the software that is to say all the
 * methods for the software view to communicate with the software model. The
 * software controller is composed by the software model and data structure
 * controllers. This class is not designed for inheritance. The class
 * <tt>SoftwareController</tt> is implemented to manage all types of data
 * structure controllers. In fact, all the data structure controllers are added
 * thanks to reflection. If you would like to add other data structure
 * controllers, do not forget to register your data structure controller classes
 * in the data structure {@code dataStructureControllerClasses} in this class.
 * 
 * @author Julien Hannier
 * @version 1.00 16/06/08
 * @see IController
 */
public final class SoftwareController implements IController {

    private SoftwareModel softwareModel;

    private AbstractViewFactory softwareViewFactory;

    private ISoftwareView softwareView;

    /**
     * The data structure controller classes associates each type of data
     * structure with the good type of data structure controller.
     */
    private static HashMap<String, Class<?>> dataStructureControllerClasses =
            new HashMap<String, Class<?>>();

    private List<IDataStructureController> dataStructureControllers;

    /**
     * Registration of the data structure controller classes according to the
     * types of data structures. You have to register new data structure
     * controllers here.
     */
    static {
        dataStructureControllerClasses.put(
                BinaryTreeController.binaryTreeFileExtension,
                BinaryTreeController.class);
    }

    /**
     * Builds the software controller. The software controller is composed by a
     * software model and data structure controllers. It also contains the
     * software view.
     * 
     * @param model the software model
     * @param viewFactory the view factory
     */
    public SoftwareController(SoftwareModel model, AbstractViewFactory viewFactory) {
        dataStructureControllers = new ArrayList<IDataStructureController>();
        softwareModel = model;
        softwareViewFactory = viewFactory;
        softwareView = softwareViewFactory.createSoftwareView(this);
        
        softwareView.displayView();
        addListener();
    }

    @Override
    public ISoftwareView getView() {
        return softwareView;
    }

    /**
     * Returns the data structure controller indicated by {@code index}. If the
     * index is out of bounds, then an IndexOutOfBoundsException is thrown.
     *
     * @param index the index of the wanted data structure controller
     * @return the wanted data structure controller
     * @throws IndexOutOfBoundsException
     */
    public IDataStructureController getDataStructureController(int index)
            throws IndexOutOfBoundsException {
        if (index >= dataStructureControllers.size()) {
            throw new IndexOutOfBoundsException("You have given an index out" +
                " of bounds");
        }
        return dataStructureControllers.get(index);
    }

    private void addListener() {
        softwareModel.addSoftwareModelListener(softwareView);
    }

    /**
     * Adds a data structure model and controller with an empty data structure.
     * If the identifiant of the data structure controller does not correspond
     * to a type of data structure controller, then an IllegalArgumentException
     * is thrown.
     *
     * @param id the identifiant of a type of data structure controller
     * @param type the type of the data structure
     * @param width the width of the data structure visualization
     * @param height the height of the data structure visualization
     * @throws IllegalArgumentException
     */
    public void addDataStructure(String id, Object type, int width, int height)
            throws IllegalArgumentException {
        if (!dataStructureControllerClasses.containsKey(id)) {
            throw new IllegalArgumentException("The identifiant (id) does not" +
                    "correspond to a data structure controller");
        }
        int index = dataStructureControllers.size();
        Class<?> dataStructureControllerClass =
                dataStructureControllerClasses.get(id);
        try {
            IDataStructureController dataStructureController =
                    (IDataStructureController) dataStructureControllerClass.newInstance();
            dataStructureControllerClass.getMethod(
                    "initializeDataStructureController",
                    Object.class, AbstractViewFactory.class, int.class,
                    int.class).invoke(dataStructureController, type,
                    softwareViewFactory, width, height);
            dataStructureControllers.add(dataStructureController);
            softwareModel.addDataStructureModel(
                    getDataStructureController(index).getDataStructureModel());
        } catch (InstantiationException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        } catch (IllegalAccessException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        } catch (NoSuchMethodException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        } catch (SecurityException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        } catch (InvocationTargetException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        }
    }

    /**
     * Adds a data structure model and controller with a data structure created
     * with random elements. If the identifiant of the data structure controller
     * does not correspond to a type of data structure controller, then an
     * IllegalArgumentException is thrown.
     *
     * @param id the identifiant of a type of data structure controller
     * @param type the type of the data structure
     * @param nb the number of random elements
     * @param width the width of the data structure visualization
     * @param height the height of the data structure visualization
     * @throws IllegalArgumentException
     */
    public void addDataStructure(String id, Object type, int nb, int width,
            int height)
            throws IllegalArgumentException {
        if (!dataStructureControllerClasses.containsKey(id)) {
            throw new IllegalArgumentException("The identifiant (id) does not" +
                    "correspond to a data structure controller");
        }
        int index = dataStructureControllers.size();
        Class<?> dataStructureControllerClass =
                dataStructureControllerClasses.get(id);
        try {
            IDataStructureController dataStructureController =
                    (IDataStructureController) dataStructureControllerClass.newInstance();
            dataStructureControllerClass.getMethod(
                    "initializeDataStructureController",
                    Object.class, AbstractViewFactory.class, int.class,
                    int.class, int.class).invoke(dataStructureController, type,
                    softwareViewFactory, nb, width, height);
            dataStructureControllers.add(dataStructureController);
            softwareModel.addDataStructureModel(
                    getDataStructureController(index).getDataStructureModel());
        } catch (InstantiationException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        } catch (IllegalAccessException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        } catch (NoSuchMethodException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        } catch (SecurityException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        } catch (InvocationTargetException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        }
    }

    /**
     * Adds a data structure model and controller from a file containing the
     * data structure. If the extension of the file does not correspond to a
     * type of data structure controller, then an IllegalArgumentException is
     * thrown.
     * 
     * @param file the file containing the data structure
     * @param width the width of the data structure visualization
     * @param height the height of the data structure visualization
     * @throws IllegalArgumentException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws UnknownDataStructureException
     */
    public void addDataStructure(File file, int width, int height)
            throws IllegalArgumentException, IOException, FileNotFoundException,
            ParseException, UnknownDataStructureException {
        int index = dataStructureControllers.size();
        String fileName = file.getName();
        String extension = fileName.substring(
                fileName.lastIndexOf('.') + 1).toLowerCase();

        if (!dataStructureControllerClasses.containsKey(extension)) {
            throw new IllegalArgumentException("The file extension does not" +
                    "correspond to a data structure controller");
        }
        Class<?> dataStructureControllerClass =
                dataStructureControllerClasses.get(extension);
        try {
            IDataStructureController dataStructureController = 
                    (IDataStructureController) dataStructureControllerClass.newInstance();
            dataStructureControllerClass.getMethod(
                    "initializeDataStructureController",
                    File.class, AbstractViewFactory.class, int.class,
                    int.class).invoke(dataStructureController, file,
                    softwareViewFactory, width, height);
            dataStructureControllers.add(dataStructureController);
            softwareModel.addDataStructureModelFromFile(
                    getDataStructureController(index).getDataStructureModel(), fileName);
        } catch (InstantiationException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        } catch (IllegalAccessException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        } catch (NoSuchMethodException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        } catch (SecurityException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        } catch (InvocationTargetException ex) {
            System.err.println("Error of introspection during the creation of" +
                    " a data structure controller");
            ex.printStackTrace();
            softwareView.displayErrorMessageAndExit();
        }
    }

    /**
     * Removes the data structure model and controller indicated by
     * {@code index}. If the index is out of bounds, then an
     * IndexOutOfBoundsException is thrown.
     * 
     * @param index the index of the data structure controller to delete
     * @throws IndexOutOfBoundsException
     */
    public void deleteDataStructure(int index) throws IndexOutOfBoundsException {
        if (index >= dataStructureControllers.size()) {
            throw new IndexOutOfBoundsException("You have given an index out" +
                " of bounds");
        }
        softwareModel.deleteDataStructureModel(index);
        dataStructureControllers.remove(index);
    }

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
            throws IndexOutOfBoundsException, IOException {
        if (index >= dataStructureControllers.size()) {
            throw new IndexOutOfBoundsException("You have given an index out" +
                " of bounds");
        }
        getDataStructureController(index).saveDataStructureModel(file);
    }

    /**
     * Closes the software view and removes all data structure models and
     * controllers.
     */
    public void closeViewAndDeleteAllDataStructures() {
        softwareView.closeView();
        softwareModel.removeAllDataStructureModels();
        dataStructureControllers.clear();
    }
}
