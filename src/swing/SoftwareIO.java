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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileFilter;
import controller.SoftwareController;
import model.UnknownDataStructureException;
import swing.tree.TreeFileFilter;

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
    }

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

    protected void addFileFilters(JFileChooser fileChooser) {
        FileFilter fileFilter = new TreeFileFilter();
        fileChooser.addChoosableFileFilter(fileFilter);
    }

    /**
     * Opens a file.
     */
    void openOperation() {
        int returnVal = fileChooser.showOpenDialog(softwareView);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                softwareController.addDataStructure(fileChooser.getSelectedFile(),
                    tabbedPane.getWidth(), tabbedPane.getHeight());
                fileChooser.setSelectedFile(null);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SoftwareIO.class.getName()).log(Level.SEVERE,
                    null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(SoftwareIO.class.getName()).log(Level.SEVERE,
                    null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SoftwareIO.class.getName()).log(Level.SEVERE,
                    null, ex);
            } catch (UnknownDataStructureException ex) {
                Logger.getLogger(SoftwareIO.class.getName()).log(Level.SEVERE,
                    null, ex);
            }
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
                        softwareController.saveDataStructure(file, index);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (type == SaveEventType.CLOSE) {
                        softwareController.deleteDataStructure(index);
                    } else if (type == SaveEventType.EXIT) {
                        softwareController.closeViewAndDeleteAllDataStructures();
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
        if (softwareController.getDataStructureController(index)
                .isDataStructureModelSaved()) {
            softwareController.deleteDataStructure(index);
        } else {
            Object[] options = { "Save", "Discard", "Cancel" };
            int choice = JOptionPane.showOptionDialog(softwareView,
                "Do you want to save your changes?", "Close Operation",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[2]);
            if (choice == JOptionPane.YES_OPTION) {
                saveOperation(SaveEventType.CLOSE);
            } else if (choice == JOptionPane.NO_OPTION) {
                softwareController.deleteDataStructure(index);
            }
        }
    }

    /**
     * Asks to save the current tab and exits the software.
     */
    void exitSoftwareOperation() {
        int count = tabbedPane.getTabCount();
        if (count == 0) {
            softwareController.closeViewAndDeleteAllDataStructures();
        } else if (count == 1) {
            if (softwareController.getDataStructureController(0)
                    .isDataStructureModelSaved()) {
                softwareController.closeViewAndDeleteAllDataStructures();
            } else {
                Object[] options = { "Save", "Discard", "Cancel" };
                int choice = JOptionPane.showOptionDialog(softwareView,
                    "Do you want to save your changes?", "Exit Operation",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, options, options[2]);
                if (choice == JOptionPane.YES_OPTION) {
                    saveOperation(SaveEventType.EXIT);
                } else if (choice == JOptionPane.NO_OPTION) {
                    softwareController.closeViewAndDeleteAllDataStructures();
                }
            }
        } else {
            int choice = JOptionPane.showConfirmDialog(softwareView,
                "You are about to close " + count + " tabs."
                        + " Do you really want to continue?", "Exit Operation",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                softwareController.closeViewAndDeleteAllDataStructures();
            }
        }
    }
}
