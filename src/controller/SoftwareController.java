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

import io.tree.TreeFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SoftwareModel;
import model.UnknownDataStructureException;
import view.AbstractViewFactory;
import view.ISoftwareView;

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

    private List<IDataStructureController> tabControllers;

    /**
     * Builds the software controller.
     * 
     * @param m the software model
     * @param v the view factory
     */
    public SoftwareController(SoftwareModel m, AbstractViewFactory v) {
        tabControllers = new ArrayList<IDataStructureController>();

        softwareModel = m;
        viewFactory = v;
        softwareView = viewFactory.createSoftwareView(this);
        getView().showView();
        addListener();
    }

    /**
     * Returns the wanted tab controller thanks to index.
     * 
     * @param index the index of the tab
     * @return the tab controller
     */
    public IDataStructureController getTabController(int index) {
        return tabControllers.get(index);
    }

    @Override
    public ISoftwareView getView() {
        return softwareView;
    }

    private void addListener() {
        softwareModel.addSoftwareModelListener(softwareView);
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
        IDataStructureController tabController = new BinaryTreeController();
        tabController.initializeTabController(type, viewFactory, width, height);
        tabControllers.add(tabController);
        softwareModel.addDataStructureModel(getTabController(index).getTabModel());
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
        IDataStructureController tabController = new BinaryTreeController();
        tabController.initializeTabControllerWithRandom(type, viewFactory, random,
            width, height);
        tabControllers.add(tabController);
        softwareModel.addDataStructureModel(getTabController(index).getTabModel());
    }

    /**
     * Opens a file.
     * 
     * @param file the file
     * @param width the width of the tree visualization
     * @param height the height of the tree visualization
     */
    public void openFile(File file, int width, int height) {
        int index = tabControllers.size();
        String fileName = file.getName();
        int i = fileName.lastIndexOf('.');
        String extension = fileName.substring(i + 1).toLowerCase();

        if (extension.equals(TreeFile.fileExtension)) {
            IDataStructureController tabController = new BinaryTreeController();
            try {
              tabController.initializeTabControllerWithFile(file, viewFactory, width,
                  height);
            } catch (FileNotFoundException ex) {
              Logger.getLogger(SoftwareController.class.getName()).log(Level.SEVERE,
                  null, ex);
            } catch (ParseException ex) {
              Logger.getLogger(SoftwareController.class.getName()).log(Level.SEVERE,
                  null, ex);
            } catch (IOException ex) {
              Logger.getLogger(SoftwareController.class.getName()).log(Level.SEVERE,
                  null, ex);
            } catch (UnknownDataStructureException ex) {
              Logger.getLogger(SoftwareController.class.getName()).log(Level.SEVERE,
                  null, ex);
            }
            tabControllers.add(tabController);
            softwareModel.addDataStructureModelFromFile(
                getTabController(index).getTabModel(), fileName);
        }
    }

    /**
     * Removes the tab indicated by index.
     * 
     * @param index the index of the tab
     */
    public void closeTab(int index) {
        softwareModel.deleteDataStructureModel(index);
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
        softwareView.hideView();
        softwareModel.removeAllDataStructureModels();
        tabControllers.clear();
    }
}
