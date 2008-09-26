/*
 * SoftwareView.java v1.00 16/06/08
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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import model.SoftwareModelEvent;
import model.UnknownDataStructureException;
import model.SoftwareModelEvent.SoftwareModelEventType;
import model.tree.AbstractBinaryTree.BinaryTreeType;
import swing.tree.RandomTreeCreationDialog;
import view.ISoftwareView;
import controller.SoftwareController;
import controller.BinaryTreeTabController;

/**
 * Definition of the software view.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see ISoftwareView
 */
public class SoftwareView extends JFrame implements ISoftwareView {

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
	
	private static final long serialVersionUID = 1L;

	private SoftwareController softwareController;

	private JMenuBar menuBar;
	
	private JMenu algorithms;

	private JFileChooser fileChooser;

	private JTabbedPane tabbedPane;

	/**
	 * Builds the software view.
	 * 
	 * @param c the software controller
	 */
	public SoftwareView(SoftwareController c) {
		super("Visualgorithm");
		softwareController = c;
		createMenuBar();
		createFileChooser();
		tabbedPane = new JTabbedPane(SwingConstants.TOP,
				JTabbedPane.SCROLL_TAB_LAYOUT);
		
		menuBar.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
		setJMenuBar(menuBar);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent evt) {
				exitSoftware();
			}
		});
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width * 8 / 10, screenSize.height * 8 / 10);
	}

	private JComponent getTabView(int index) {
		return (JComponent) softwareController.getTabController(index)
				.getView();
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
	
	public void addFileFilter(FileFilter fileFilter) {
		fileChooser.addChoosableFileFilter(fileFilter);
	}
	
	public void addDataStructureMenu(JMenu newMenu) {
		menuBar.remove(algorithms);
		menuBar.add(newMenu);
		menuBar.add(algorithms);
	}

	private void createMenuBar() {
		menuBar = new JMenuBar();
		algorithms = createAlgorithmsMenu();

		menuBar.add(createFileMenu());
		menuBar.add(createEditMenu());
		menuBar.add(createTreesMenu());
		menuBar.add(createTreesMenu());
		
	}

	private JMenu createFileMenu() {
		JMenu file = new JMenu("File");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem exit = new JMenuItem("Exit");

		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				int returnVal = fileChooser.showOpenDialog(SoftwareView.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					int index = tabbedPane.getTabCount();
					try {
						softwareController.openDataStructureFile(fileChooser
								.getSelectedFile(), index, tabbedPane
								.getWidth(), tabbedPane.getHeight());
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(SoftwareView.this, e
								.getMessage(), "Open Failed",
								JOptionPane.WARNING_MESSAGE);
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(SoftwareView.this, e
								.getMessage(), "Open Failed",
								JOptionPane.WARNING_MESSAGE);
					} catch (IOException e) {
						System.out.println("File could not be read");
						System.exit(1);
					} catch (UnknownDataStructureException e) {
						JOptionPane.showMessageDialog(SoftwareView.this, e
								.getMessage(), "Open Failed",
								JOptionPane.WARNING_MESSAGE);
					}
					fileChooser.setSelectedFile(null);
				}
			}
		});
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				saveAction(SaveEventType.SAVE);
			}
		});
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				exitSoftware();
			}
		});
		file.add(open);
		file.add(save);
		file.add(exit);
		return file;
	}

	/**
     * Saves a tab into a file.
     * 
     * @param type the type of the save event
     */
	void saveAction(SaveEventType type) {
		int count = tabbedPane.getTabCount();
		if (count > 0) {
			int returnVal = fileChooser
					.showSaveDialog(SoftwareView.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				int index = tabbedPane.getSelectedIndex();
				File file = fileChooser.getSelectedFile();
				int choice = JOptionPane.NO_OPTION;
				if (file.exists()) {
					choice = JOptionPane.showConfirmDialog(
							SoftwareView.this,
							"This file already exists."
									+ " Do you want to replace it?",
							"Save Operation",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
				}
				if ((!file.exists())
						|| (choice == JOptionPane.YES_OPTION)) {
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
	
	private void exitSoftware() {
		int count = tabbedPane.getTabCount();
		if (count == 0) {
			softwareController.exitSoftware();
		} else if (count == 1) {
			if (((BinaryTreeTabController) softwareController
					.getTabController(0)).isTabModelSaved()) {
				softwareController.exitSoftware();
			} else {
				Object[] options = { "Save", "Discard", "Cancel" };
				int choice = JOptionPane.showOptionDialog(SoftwareView.this,
						"Do you want to save your changes?", "Exit Operation",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[2]);
				if (choice == JOptionPane.YES_OPTION) {
					saveAction(SaveEventType.EXIT);
				} else if (choice == JOptionPane.NO_OPTION) {
					softwareController.exitSoftware();
				}
			}
		} else {
			int choice = JOptionPane.showConfirmDialog(SoftwareView.this,
					"You are about to close " + count + " tabs."
							+ " Do you really want to continue?",
					"Exit Operation", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (choice == JOptionPane.YES_OPTION) {
				softwareController.exitSoftware();
			}
		}
	}

	private JMenu createEditMenu() {
		JMenu tools = new JMenu("Edit");
		JMenu language = new JMenu("Language");
		JMenuItem preferences = new JMenuItem("Preferences");

		tools.add(language);
		tools.add(preferences);
		return tools;
	}

	private JMenu createTreesMenu() {
		JMenu trees = new JMenu("Trees");
		JMenu newTree = new JMenu("New");
		JMenu notes = new JMenu("Notes");
		JMenuItem randomTree = new JMenuItem("Random Tree");
		JMenuItem avlTree = new JMenuItem("AVL Tree");
		JMenuItem binarySearchTree = new JMenuItem("Binary Search Tree");
		JMenuItem redBlackTree = new JMenuItem("Red Black Tree");

		randomTree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				RandomTreeCreationDialog dialog = new RandomTreeCreationDialog(
						SoftwareView.this, tabbedPane, softwareController);
				dialog.setVisible(true);
			}
		});
		avlTree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				int index = tabbedPane.getTabCount();
				softwareController.addBinaryTreeTab(BinaryTreeType.AVLTREE,
						index, tabbedPane.getWidth(), tabbedPane.getHeight());
			}
		});
		binarySearchTree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				int index = tabbedPane.getTabCount();
				softwareController.addBinaryTreeTab(
						BinaryTreeType.BINARYSEARCHTREE, index, tabbedPane
								.getWidth(), tabbedPane.getHeight());
			}
		});
		redBlackTree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				int index = tabbedPane.getTabCount();
				softwareController.addBinaryTreeTab(
						BinaryTreeType.REDBLACKTREE, index, tabbedPane
								.getWidth(), tabbedPane.getHeight());
			}
		});
		newTree.add(randomTree);
		newTree.add(binarySearchTree);
		newTree.add(avlTree);
		newTree.add(redBlackTree);
		trees.add(newTree);
		trees.add(notes);
		return trees;
	}

	private JMenu createAlgorithmsMenu() {
		JMenu algorithms = new JMenu("Algorithms");
		JMenu apply = new JMenu("Apply");
		JMenu notes = new JMenu("Notes");

		algorithms.add(apply);
		algorithms.add(notes);
		return algorithms;
	}

	@Override
	public void displayView() {
		setVisible(true);
	}

	@Override
	public void closeView() {
		setVisible(false);
		dispose();
	}

	@Override
	public void modelChanged(SoftwareModelEvent event) {
		if (event.getType() == SoftwareModelEventType.ADD) {
			int numTab = tabbedPane.getTabCount();
			tabbedPane.addTab(event.getName(), getTabView(numTab));
			tabbedPane.setTabComponentAt(numTab, new TabCloseButton(tabbedPane,
					softwareController));
			tabbedPane.setSelectedIndex(numTab);
		} else if (event.getType() == SoftwareModelEventType.EXIT) {
			System.exit(0);
		} else if (event.getType() == SoftwareModelEventType.DELETE) {
			int i = event.getIndex();
			if (i != -1) {
				tabbedPane.remove(i);
			}
		}
	}
}
