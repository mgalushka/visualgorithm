/*
 * SoftwareIO.java v1.00 27/09/08
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

package swing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileFilter;
import model.UnknownDataStructureException;
import controller.ITabController;
import controller.SoftwareController;

/**
 * Definition of the software IO.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 27/09/08
 */
public class SoftwareIO {

    /**
     * Enumeration of the save event type.
     * 
     * @author Julien Hannier
     * @author Pierre Pironin
     * @author Damien Rigoni
     * @version 1.00 26/09/08
     */
    public enum SaveEventType {
        CLOSE, EXIT, SAVE
    };

    private SoftwareController softwareController;

    private SoftwareView softwareView;

    private JTabbedPane tabbedPane;

    private JFileChooser fileChooser;

    /**
     * Builds the software IO.
     * 
     * @param v the software view
     * @param tp the tabbed pane
     * @param c the software controller
     */
    SoftwareIO(SoftwareView v, JTabbedPane tp, SoftwareController c) {
        softwareController = c;
        softwareView = v;
        tabbedPane = tp;
        createFileChooser();
    }

    private void createFileChooser() {
        fileChooser = new JFileChooser();
        addFileFilters(fileChooser);
        fileChooser.setAcceptAllFileFilterUsed(false);
    }

    private void addFileFilters(JFileChooser fileChooser) {
        File currentDirectory = new File("src/swing");
        String[] directories = SoftwareController
                .listOfDirectoriesInDirectory(currentDirectory);
        for (String each : directories) {
            File directory = new File("src/swing/" + each);
            String[] filterFile = SoftwareController.listOfFilesInDirectory(
                directory, "FileFilter.java");
            if (filterFile.length > 0) {
                String className = SoftwareController.wellFormedClassName(
                    filterFile[0], directory);
                try {
                    FileFilter fileFilter = (FileFilter) Class.forName(
                        className).newInstance();
                    fileChooser.addChoosableFileFilter(fileFilter);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Opens a file.
     */
    void openOperation() {
        int returnVal = fileChooser.showOpenDialog(softwareView);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                softwareController.openFile(fileChooser.getSelectedFile(),
                    tabbedPane.getWidth(), tabbedPane.getHeight());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(softwareView, e.getMessage(),
                    "Open Failed", JOptionPane.WARNING_MESSAGE);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(softwareView, e.getMessage(),
                    "Open Failed", JOptionPane.WARNING_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnknownDataStructureException e) {
                JOptionPane.showMessageDialog(softwareView, e.getMessage(),
                    "Open Failed", JOptionPane.WARNING_MESSAGE);
            }
            fileChooser.setSelectedFile(null);
        }
    }

    /**
     * Saves a tab data structure into a file.
     * 
     * @param type the type of the save event
     */
    void saveOperation(SaveEventType type) {
        int count = tabbedPane.getTabCount();
        if (count > 0) {
            int returnVal = fileChooser.showSaveDialog(softwareView);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                int index = tabbedPane.getSelectedIndex();
                File file = fileChooser.getSelectedFile();
                int choice = JOptionPane.NO_OPTION;
                if (file.exists()) {
                    choice = JOptionPane.showConfirmDialog(softwareView,
                        "This file already exists."
                                + " Do you want to replace it?",
                        "Save Operation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                }
                if ((!file.exists()) || (choice == JOptionPane.YES_OPTION)) {
                    try {
                        softwareController.saveTabModel(file, index);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (type == SaveEventType.CLOSE) {
                        softwareController.closeTab(index);
                    } else if (type == SaveEventType.EXIT) {
                        softwareController.exitSoftware();
                    } else if (type == SaveEventType.SAVE) {
                        tabbedPane.setTitleAt(index, file.getName());
                        ((TabCloseButton) tabbedPane.getTabComponentAt(index))
                                .revalidate();
                    }
                }
            }
            fileChooser.setSelectedFile(null);
        }
    }

    /**
     * Asks to save the indicated tab and closes it.
     * 
     * @param index the index of the tab
     */
    void closeTabOperation(int index) {
        if (((ITabController) softwareController.getTabController(index))
                .isTabModelSaved()) {
            softwareController.closeTab(index);
        } else {
            Object[] options = { "Save", "Discard", "Cancel" };
            int choice = JOptionPane.showOptionDialog(softwareView,
                "Do you want to save your changes?", "Close Operation",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[2]);
            if (choice == JOptionPane.YES_OPTION) {
                saveOperation(SaveEventType.CLOSE);
            } else if (choice == JOptionPane.NO_OPTION) {
                softwareController.closeTab(index);
            }
        }
    }

    /**
     * Asks to save the current tab and exits the software.
     */
    void exitSoftwareOperation() {
        int count = tabbedPane.getTabCount();
        if (count == 0) {
            softwareController.exitSoftware();
        } else if (count == 1) {
            if (((ITabController) softwareController.getTabController(0))
                    .isTabModelSaved()) {
                softwareController.exitSoftware();
            } else {
                Object[] options = { "Save", "Discard", "Cancel" };
                int choice = JOptionPane.showOptionDialog(softwareView,
                    "Do you want to save your changes?", "Exit Operation",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, options, options[2]);
                if (choice == JOptionPane.YES_OPTION) {
                    saveOperation(SaveEventType.EXIT);
                } else if (choice == JOptionPane.NO_OPTION) {
                    softwareController.exitSoftware();
                }
            }
        } else {
            int choice = JOptionPane.showConfirmDialog(softwareView,
                "You are about to close " + count + " tabs."
                        + " Do you really want to continue?", "Exit Operation",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                softwareController.exitSoftware();
            }
        }
    }
}
