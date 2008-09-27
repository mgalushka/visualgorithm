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

import controller.BinaryTreeTabController;
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

	private JTabbedPane tabbedPane;

	private JFileChooser fileChooser;

	/**
	 * Builds the software IO.
	 * 
	 * @param tp the tabbed pane
	 * @param c the software controller
	 */
	SoftwareIO(JTabbedPane tp, SoftwareController c) {
		softwareController = c;
		tabbedPane = tp;
		createFileChooser();
	}

	private void createFileChooser() {
		fileChooser = new JFileChooser();
		addFileFilter(new FileFilter() {

			@Override
			public boolean accept(File file) {
				if (file.isDirectory()) {
					return true;
				}
				String name = file.getName();
				int i = name.lastIndexOf('.');
				String extension = null;
				if ((i > 0) && (i < name.length() - 1)) {
					extension = name.substring(i + 1).toLowerCase();
				}
				if (extension != null) {
					if (extension.equals("bt")) {
						return true;
					} else {
						return false;
					}
				}
				return false;
			}

			@Override
			public String getDescription() {
				return "Binary Tree  ( .bt )";
			}
		});
		fileChooser.setAcceptAllFileFilterUsed(false);
	}

	/**
     * Adds a file filter to the file chooser.
     * 
     * @param fileFilter the filter to add
     */
	void addFileFilter(FileFilter fileFilter) {
		fileChooser.addChoosableFileFilter(fileFilter);
	}

	/**
	 * Opens a file.
	 */
	void openOperation() {
		int returnVal = fileChooser
				.showOpenDialog((SoftwareView) softwareController.getView());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			int index = tabbedPane.getTabCount();
			try {
				softwareController.openDataStructureFile(fileChooser
						.getSelectedFile(), index, tabbedPane.getWidth(),
						tabbedPane.getHeight());
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog((SoftwareView) softwareController
						.getView(), e.getMessage(), "Open Failed",
						JOptionPane.WARNING_MESSAGE);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog((SoftwareView) softwareController
						.getView(), e.getMessage(), "Open Failed",
						JOptionPane.WARNING_MESSAGE);
			} catch (IOException e) {
				System.out.println("File could not be read");
				System.exit(1);
			} catch (UnknownDataStructureException e) {
				JOptionPane.showMessageDialog((SoftwareView) softwareController
						.getView(), e.getMessage(), "Open Failed",
						JOptionPane.WARNING_MESSAGE);
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
			int returnVal = fileChooser
					.showSaveDialog((SoftwareView) softwareController.getView());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				int index = tabbedPane.getSelectedIndex();
				File file = fileChooser.getSelectedFile();
				int choice = JOptionPane.NO_OPTION;
				if (file.exists()) {
					choice = JOptionPane.showConfirmDialog(
							(SoftwareView) softwareController.getView(),
							"This file already exists."
									+ " Do you want to replace it?",
							"Save Operation", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
				if ((!file.exists()) || (choice == JOptionPane.YES_OPTION)) {
					try {
						softwareController.saveTabModel(file, index);
					} catch (IOException e) {
						System.out.println("File could not be saved");
						System.exit(1);
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
		if (((BinaryTreeTabController) softwareController
				.getTabController(index)).isTabModelSaved()) {
			softwareController.closeTab(index);
		} else {
			Object[] options = { "Save", "Discard", "Cancel" };
			int choice = JOptionPane.showOptionDialog(
					(SoftwareView) softwareController.getView(),
					"Do you want to save your changes?", "Close Operation",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[2]);
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
			if (((BinaryTreeTabController) softwareController
					.getTabController(0)).isTabModelSaved()) {
				softwareController.exitSoftware();
			} else {
				Object[] options = { "Save", "Discard", "Cancel" };
				int choice = JOptionPane.showOptionDialog(
						(SoftwareView) softwareController.getView(),
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
			int choice = JOptionPane.showConfirmDialog(
					(SoftwareView) softwareController.getView(),
					"You are about to close " + count + " tabs."
							+ " Do you really want to continue?",
					"Exit Operation", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (choice == JOptionPane.YES_OPTION) {
				softwareController.exitSoftware();
			}
		}
	}
}
