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
import java.util.List;
import utils.FileUtils;
import view.ISoftwareView;
import view.AbstractViewFactory;
import model.SoftwareModel;
import model.UnknownDataStructureException;

/**
 * Definition of the software controller.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see IController
 */
public class SoftwareController implements IController {

    private SoftwareModel softwareModel;

    private ISoftwareView softwareView;

    private AbstractViewFactory viewFactory;

    private List<ITabController> tabControllers;

    /**
     * Builds the software controller.
     * 
     * @param m the software model
     * @param v the view factory
     */
    public SoftwareController(SoftwareModel m, AbstractViewFactory v) {
        tabControllers = new ArrayList<ITabController>();

        softwareModel = m;
        viewFactory = v;
        softwareView = viewFactory.createSoftwareView(this);
        getView().displayView();
        addListener();
    }

    /**
     * Returns the wanted tab controller thanks to index.
     * 
     * @param index the index of the tab
     * @return the tab controller
     */
    public ITabController getTabController(int index) {
        return tabControllers.get(index);
    }

    @Override
    public ISoftwareView getView() {
        return softwareView;
    }

    private void addListener() {
        softwareModel.addModelListener(softwareView);
    }

    /**
     * Adds a data structure tab.
     * 
     * @param name the name of the data structure
     * @param type the type of the data structure
     * @param index the index of the tab
     * @param width the width of the visualization
     * @param height the height of the visualization
     */
    public void addTab(String name, Object type, int index, int width,
            int height) {
        File currentDirectory = new File("src/controller");
        String[] tabControllerFiles = FileUtils.listOfFilesInDirectory(
            currentDirectory, "TabController.java");

        for (String each : tabControllerFiles) {
            String className = FileUtils.wellFormedClassName(each,
                currentDirectory);
            if (className.toLowerCase().contains(name.toLowerCase())) {
                try {
                    ITabController tabController = (ITabController) Class
                            .forName(className).newInstance();
                    Class.forName(className).getMethod(
                        "initializeTabController", Object.class,
                        AbstractViewFactory.class, int.class, int.class)
                            .invoke(tabController, type, viewFactory, width,
                                height);
                    tabControllers.add(tabController);
                    softwareModel.addTabModel(getTabController(index)
                            .getTabModel());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Adds a data structure tab with a data structure created with random
     * elements.
     * 
     * @param name the name of the data structure
     * @param type the type of the data structure
     * @param random the number of elements
     * @param index the index of the tab
     * @param width the width of the visualization
     * @param height the height of the visualization
     */
    public void addTabWithRandom(String name, Object type, int random,
            int index, int width, int height) {
        File currentDirectory = new File("src/controller");
        String[] tabControllerFiles = FileUtils.listOfFilesInDirectory(
            currentDirectory, "TabController.java");

        for (String each : tabControllerFiles) {
            String className = FileUtils.wellFormedClassName(each,
                currentDirectory);
            if (className.toLowerCase().contains(name.toLowerCase())) {
                try {
                    ITabController tabController = (ITabController) Class
                            .forName(className).newInstance();
                    Class.forName(className).getMethod(
                        "initializeTabControllerWithRandom", Object.class,
                        AbstractViewFactory.class, int.class, int.class,
                        int.class).invoke(tabController, type, viewFactory,
                        random, width, height);
                    tabControllers.add(tabController);
                    softwareModel.addTabModel(getTabController(index)
                            .getTabModel());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Opens a file.
     * 
     * @param file the file
     * @param width the width of the tree visualization
     * @param height the height of the tree visualization
     */
    public void openFile(File file, int width, int height)
            throws FileNotFoundException, ParseException, IOException,
            UnknownDataStructureException {
        int index = tabControllers.size();
        String fileName = file.getName();
        int i = fileName.lastIndexOf('.');
        String extension = fileName.substring(i + 1).toLowerCase();
        File currentDirectory = new File("src/controller");
        String[] tabControllerFiles = FileUtils.listOfFilesInDirectory(
            currentDirectory, "TabController.java");

        for (String each : tabControllerFiles) {
            String className = FileUtils.wellFormedClassName(each,
                currentDirectory);
            try {
                ITabController tabController = (ITabController) Class.forName(
                    className).newInstance();
                if (extension.equals(tabController.getFileExtension())) {
                    Class.forName(className).getMethod(
                        "initializeTabControllerWithFile", File.class,
                        AbstractViewFactory.class, int.class, int.class)
                            .invoke(tabController, file, viewFactory, width,
                                height);
                    tabControllers.add(tabController);
                    softwareModel.addTabModelFromDataStructureFile(
                        getTabController(index).getTabModel(), fileName);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes the tab indicated with index.
     * 
     * @param index the index of the tab
     */
    public void closeTab(int index) {
        softwareModel.removeTabModel(index);
        tabControllers.remove(index);
    }

    /**
     * Saves the data structure from the tab into the selected file.
     * 
     * @param file the file
     * @param index the index of the tab
     * @throws IOException
     */
    public void saveTabModel(File file, int index) throws IOException {
        getTabController(index).saveTabModel(file);
    }

    /**
     * Removes all the tabs.
     */
    public void exitSoftware() {
        softwareView.closeView();
        softwareModel.removeAllTabModels();
        tabControllers.clear();
    }
}
