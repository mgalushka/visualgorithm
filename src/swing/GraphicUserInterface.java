/*
 * GraphicUserInterface.java v1.00 16/06/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (bx1gl@googlegroups.com)
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

import model.DataStructureType;
import model.Model;
import model.ModelEvent;
import model.ModelEvent.ModelEventType;
import model.tree.UnknownTreeTypeException;
import view.IModelView;
import controller.PrincipalController;
import controller.TabController;

/**
 * Definition of the principal view.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see IModelView
 */
public class GraphicUserInterface extends JFrame implements IModelView {
    
    private static final long serialVersionUID = 1L;

    private PrincipalController controller;
    
    private JMenuBar menuBar;
    
    private JFileChooser fileChooser;
    
    private JTabbedPane tabbedPane;

    /**
     * Builds the principal view.
     * 
     * @param model the model
     * @param c the controller
     */
    public GraphicUserInterface(Model model, PrincipalController c) {
        super("Visualgorithm");
        controller = c;
        menuBar = createMenuBar(model);
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        fileChooser = new JFileChooser();
        menuBar.setBorder(
            BorderFactory.createEmptyBorder(2, 0, 2, 0));
        setJMenuBar(menuBar);
        add(tabbedPane);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            
            @Override
            public void windowClosing(WindowEvent evt) {
                exit();
            }
        });
        Dimension screenSize = 
            Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width * 8/10, screenSize.height * 8/10);
    }
    
    private JComponent getTabView(int index) {
        return (JComponent)
            controller.getTabController(index).getView();
    }

    private JMenuBar createMenuBar(Model model) {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = createFileMenu(model);
        JMenu edit = createEditMenu(model);
        JMenu trees = createTreesMenu(model);
        JMenu algorithms = createAlgorithmsMenu(model);
        
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(trees);
        menuBar.add(algorithms);
        return menuBar;
    }
    
    private JMenu createFileMenu(Model model) {
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");
        
        open.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int returnVal = 
                    fileChooser.showOpenDialog(
                    GraphicUserInterface.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    int index = tabbedPane.getTabCount();
                    try {
                        controller.openFile(
                            fileChooser.getSelectedFile(), index);
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(tabbedPane,
                            e.getMessage(),
                            "Open Failed",
                            JOptionPane.WARNING_MESSAGE);
                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(tabbedPane,
                            e.getMessage(),
                            "Open Failed",
                            JOptionPane.WARNING_MESSAGE);
                    } catch (IOException e) {
                        System.out.println("File could not be read");
                        System.exit(1);
                    } catch (UnknownTreeTypeException e) {
                        JOptionPane.showMessageDialog(tabbedPane,
                            e.getMessage(),
                            "Open Failed",
                            JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        save.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_S, ActionEvent.CTRL_MASK));   
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int count = tabbedPane.getTabCount();
                if (count > 0) {
                    int returnVal = fileChooser.showSaveDialog(
                        GraphicUserInterface.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        int index = tabbedPane.getSelectedIndex();
                        File file = fileChooser.getSelectedFile();
                        if (file.exists()) {
                            int choice = JOptionPane.showConfirmDialog(
                                tabbedPane,"This file already exists." +
                                " Do you want to replace it?",
                                "Save Operation",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                            if (choice == JOptionPane.YES_OPTION) {
                                saveFile(index, file);
                                tabbedPane.setTitleAt(index, file.getName());
                                //TODO repaint close button
                            }
                        } else {
                            saveFile(index, file);
                            tabbedPane.setTitleAt(index, file.getName());
                            //TODO repaint close button
                        }
                    }
                }
            }
        });
        exit.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                exit();
            }
        });
        file.add(open);
        file.add(save);
        file.add(exit);
        return file;
    }
    
    private void exit() {
        int count = tabbedPane.getTabCount();
        if (count == 0) {
            controller.exitSoftware();
        } else if (count == 1) {
            if (((TabController)controller.getTabController(0)).
                    isSaved()) {
                controller.exitSoftware();
            } else {
                Object[] options = {"Save", "Discard", "Cancel"};
                int choice = JOptionPane.showOptionDialog(tabbedPane,
                    "Do you want to save your changes?",
                    "Exit Operation",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE, null,
                    options, options[2]);
                if (choice == JOptionPane.YES_OPTION) {
                    int returnVal = fileChooser.showSaveDialog(
                        tabbedPane);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        int index = tabbedPane.getSelectedIndex();
                        File file = fileChooser.getSelectedFile();
                        if (file.exists()) {
                            int response = JOptionPane.showConfirmDialog(
                                tabbedPane,"This file already exists." +
                                " Do you want to replace it?",
                                "Save Operation",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                            if (response == JOptionPane.YES_OPTION) {
                                saveFile(index, file);
                                controller.exitSoftware();
                            }
                        } else {
                            saveFile(index, file);
                            controller.exitSoftware();
                        }
                    }
                } else if (choice == JOptionPane.NO_OPTION) {
                    controller.exitSoftware();
                }
            }
        } else {
            int choice = JOptionPane.showConfirmDialog(tabbedPane,
                "You are about to close "+count+" tabs." +
                        " Do you really want to continue?",
                "Exit Operation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                controller.exitSoftware();
            }
        }
    }
    
    /**
     * Saves a tab into a file.
     * 
     * @param index the index of the tab
     * @param file the file
     */
    void saveFile(int index, File file) {
        try {
            controller.saveFile(
                file, index);
        } catch (IOException e) {
            System.out.println("File could not be saved");
            System.exit(1);
        }
    }
    
    private JMenu createEditMenu(Model model) {
        JMenu tools = new JMenu("Edit");
        JMenu language = new JMenu("Language");
        JMenuItem preferences = new JMenuItem("Preferences");
        
        tools.add(language);
        tools.add(preferences);
        return tools;
    }

    private JMenu createTreesMenu(Model model) {
        JMenu trees = new JMenu("Trees");
        JMenu newTree = new JMenu("New");
        JMenu notes = new JMenu("Notes");
        JMenuItem randomTree = new JMenuItem("Random Tree");
        JMenuItem avlTree = new JMenuItem("AVL Tree");
        JMenuItem binarySearchTree = new JMenuItem(
            "Binary Search Tree");
        JMenuItem redBlackTree = new JMenuItem("Red " +
                "Black Tree");
        
        avlTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int index = tabbedPane.getTabCount();
                controller.addTabDataStructure(
                    DataStructureType.AVLTREE, index);
            }
        });
        binarySearchTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int index = tabbedPane.getTabCount();
                controller.addTabDataStructure(
                    DataStructureType.BINARYSEARCHTREE, index);
            }
        });
        redBlackTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int index = tabbedPane.getTabCount();
                controller.addTabDataStructure(
                    DataStructureType.REDBLACKTREE, index);
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
    
    private JMenu createAlgorithmsMenu(Model model) {
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
    public void modelChanged(ModelEvent event) {
        if (event.getType() == ModelEventType.ADD) {
            int numTab = tabbedPane.getTabCount();
            tabbedPane.addTab(event.getName(),
                getTabView(numTab));
            tabbedPane.setTabComponentAt(numTab,
                new TabCloseButton(tabbedPane, fileChooser, controller));
            tabbedPane.setSelectedIndex(numTab);
        } else if (event.getType() == ModelEventType.EXIT) {
            System.exit(0);
        } else if (event.getType() == ModelEventType.DELETE) {
            int i = event.getIndex();
            if (i != -1) {
                tabbedPane.remove(i);
            }
        }
    }
}