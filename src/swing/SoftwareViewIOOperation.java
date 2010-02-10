/*
 * SoftwareViewIOOperation.java v1.00 27/09/08
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
import javax.swing.filechooser.FileFilter;
import controller.ISoftwareController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import model.UnknownDataStructureException;
import swing.tree.BinaryTreeFileFilter;

/**
 * This class defines the software view IO operation. The software view IO
 * operation is composed by a file chooser and every data structure file
 * filters. This class is not designed for inheritance. The class
 * <tt>SoftwareViewIOOperation</tt> is implemented to manage IO operations
 * between users and the software. If you would like to add other data structure
 * file filters, do not forget to register your data structure file filters in
 * the data structure {@code dataStructureFileFilters} in this class.
 * 
 * @author Julien Hannier
 * @version 1.00 27/09/08
 */
final class SoftwareViewIOOperation {

    private ISoftwareController softwareController;

    private JFrame softwareFileChooserParent;

    private JFileChooser softwareFileChooser;

    /**
     * The data structure file filters contains all the data structure file
     * filters of the software.
     */
    private static List<FileFilter> dataStructureFileFilters = new ArrayList<FileFilter>();

    /**
     * Registration of the data structure file filters according to the types of
     * data structures. You have to register new data structure file filters here.
     */
    static {
        dataStructureFileFilters.add(new BinaryTreeFileFilter());
    }

    /**
     * Builds the software view IO operation. The software view IO operation is
     * composed by a file chooser.
     * 
     * @param c the software controller
     */
    SoftwareViewIOOperation(ISoftwareController c) {
        softwareController = c;
        softwareFileChooserParent = (JFrame) softwareController.getView();

        softwareFileChooser = createFileChooser();
    }

    private JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();

        for (FileFilter fileFilter: dataStructureFileFilters) {
            fileChooser.addChoosableFileFilter(fileFilter);
        }
        fileChooser.setAcceptAllFileFilterUsed(false);

        return fileChooser;
    }

    /**
     * Shows an error message indicating that a problem occurs.
     *
     * @param message the message to show
     * @param title the title of the dialog
     */
    void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(softwareFileChooserParent, message,
                title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Asks the user to open a file containing a data structure and adds it to
     * the software.
     */
    void openFileOperation() {
        int userChoice = softwareFileChooser.showOpenDialog(softwareFileChooserParent);
        
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            try {
                softwareController.addDataStructure(softwareFileChooser.getSelectedFile());
            } catch (FileNotFoundException ex) {
                showErrorMessage("The file you indicated does not exist.", "Open Error");
            } catch (IllegalArgumentException ex) {
                showErrorMessage("An irrecoverable error occurs and the" +
                        " software is about\nto shut down. Sorry for the" +
                        " inconvenience.", "Software Error");
                System.exit(1);
            } catch (ParseException ex) {
                showErrorMessage("The file you indicated contains an error on" +
                        " the line : " + ex.getErrorOffset() + ". The error" +
                        " message is :\n" + ex.getMessage(), "Open Error");
            } catch (IOException ex) {
                showErrorMessage("A problem occurs with the file you indicated.",
                        "Open Error");
            } catch (UnknownDataStructureException ex) {
                showErrorMessage(ex.getMessage(), "Open Error");
            }
        }
        softwareFileChooser.setSelectedFile(null);
    }

    /**
     * Saves the data structure indicated by {@code selectedIndex} by asking
     * users where to save it. If the operation is successfull then the name of
     * the file where the data structure was saved is returned, or else an empty
     * string.
     *
     * @param selectedIndex the index of the selected tab
     * @return the name of the file where the data structure was saved
     */
    String saveDataStructureOperation(int selectedIndex) {
        String fileName = "";
        int userFileChoice = softwareFileChooser.showSaveDialog(softwareFileChooserParent);

        if (userFileChoice == JFileChooser.APPROVE_OPTION) {
            File file = softwareFileChooser.getSelectedFile();
            int userChoice = JOptionPane.NO_OPTION;

            if (file.exists()) {
                userChoice = JOptionPane.showConfirmDialog(softwareFileChooserParent,
                    "This file already exists. Do you want to replace it?",
                    "Save Operation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            }

            if ((!file.exists()) || (userChoice == JOptionPane.YES_OPTION)) {
                try {
                    softwareController.saveDataStructure(file, selectedIndex);
                    fileName = file.getName();
                } catch (IOException ex) {
                    showErrorMessage("A problem occurs with the file you indicated.",
                    "Save Error");
                } catch (IndexOutOfBoundsException ex) {
                    showErrorMessage("An irrecoverable error occurs and the" +
                        " software is about\nto shut down. Sorry for the" +
                        " inconvenience.", "Software Error");
                    System.exit(1);
                }
            }
        }
        softwareFileChooser.setSelectedFile(null);

        return fileName;
    }

    /**
     * Closes the tab indicated by {@code selectedIndex} by asking users to save
     * the data structure if it has not already been saved.
     * 
     * @param selectedIndex the index of the selected tab
     */
    void closeTabOperation(int selectedIndex) {
        try {
            if (softwareController.getDataStructureController(selectedIndex)
                    .isDataStructureModelSaved()) {
                softwareController.deleteDataStructure(selectedIndex);
            } else {
                Object[] dialogOptions = {"Save", "Discard", "Cancel"};
                int userChoice = JOptionPane.showOptionDialog(softwareFileChooserParent,
                    "Do you want to save your changes?", "Close Operation",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, dialogOptions, dialogOptions[2]);

                if (userChoice == JOptionPane.YES_OPTION) {
                    saveDataStructureOperation(selectedIndex);
                    softwareController.deleteDataStructure(selectedIndex);
                } else if (userChoice == JOptionPane.NO_OPTION) {
                    softwareController.deleteDataStructure(selectedIndex);
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            showErrorMessage("An irrecoverable error occurs and the" +
                " software is about\nto shut down. Sorry for the" +
                " inconvenience.", "Software Error");
            System.exit(1);
        }
    }

    /**
     * Exits the software by asking users to save non-saved data structures. If
     * the operation is successfull then true is returned, or else false.
     *
     * @param tabCount the number of tabs of the software
     * @return true if the operation ends successfully, or else false
     */
    boolean exitSoftwareOperation(int tabCount) {
        if (tabCount == 0) {
            return true;
        } else if (tabCount == 1) {
            try {
                int selectedIndex = 0;

                if (softwareController.getDataStructureController(selectedIndex)
                        .isDataStructureModelSaved()) {
                    softwareController.removeAllDataStructureModelsAndControllers();
                    return true;
                } else {
                    Object[] dialogOptions = {"Save", "Discard", "Cancel"};
                    int userChoice = JOptionPane.showOptionDialog(softwareFileChooserParent,
                        "Do you want to save your changes?", "Exit Operation",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, dialogOptions, dialogOptions[2]);

                    if (userChoice == JOptionPane.YES_OPTION) {
                        saveDataStructureOperation(selectedIndex);
                        softwareController.removeAllDataStructureModelsAndControllers();
                        return true;
                    } else if (userChoice == JOptionPane.NO_OPTION) {
                        softwareController.removeAllDataStructureModelsAndControllers();
                        return true;
                    }
                }
            } catch (IndexOutOfBoundsException ex) {
                showErrorMessage("An irrecoverable error occurs and the" +
                    " software is about\nto shut down. Sorry for the" +
                    " inconvenience.", "Software Error");
                System.exit(1);
            }
        } else {
            int userChoice = JOptionPane.showConfirmDialog(softwareFileChooserParent,
                "You are about to close " + tabCount + " tabs. Do you really want to" +
                " continue?", "Exit Operation", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (userChoice == JOptionPane.YES_OPTION) {
                softwareController.removeAllDataStructureModelsAndControllers();
                return true;
            }
        }
        return false;
    }
}
