/*
 * BinaryTreeMenu.java v0.10 26/10/08
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

package swing.tree;

import controller.IBinaryTreeController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.tree.AbstractBinaryTree.BinaryTreeType;
import swing.AbstractDataStructureMenu;

/**
 * This class defines the binary tree menu. It is composed by the software
 * controller and two sub menus wich are <tt>New</tt> and <tt>Info</tt>. All of
 * these are defined in the abstract class <tt>AbstractDataStructureMenu</tt>.
 * This class is not designed for inheritance.
 * 
 * @author Julien Hannier
 * @version 0.10 26/10/08
 * @see AbstractDataStructureMenu
 */
public final class BinaryTreeMenu extends AbstractDataStructureMenu {

    private static final long serialVersionUID = 1L;

    /**
     * Builds the binary tree menu.
     */
    public BinaryTreeMenu() {
        super("Binary Tree");
        setMnemonic('T');
    }

    @Override
    protected List<JMenuItem> createNewMenuItems() {
        List<JMenuItem> newTreeMenuItems = new ArrayList<JMenuItem>();
        JMenuItem randomTreeMenuItem = new JMenuItem("Random Tree");
        JMenuItem avlTreeMenuItem = new JMenuItem("AVL Tree");
        JMenuItem binarySearchTreeMenuItem = new JMenuItem("Binary Search Tree");
        JMenuItem redBlackTreeMenuItem = new JMenuItem("Red Black Tree");

        randomTreeMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                RandomBinaryTreeCreationDialog dialog = new RandomBinaryTreeCreationDialog(
                        (JFrame) softwareController.getView(), softwareController);
                dialog.setVisible(true);
            }
        });
        avlTreeMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                softwareController.addDataStructure(
                        IBinaryTreeController.BINARY_TREE_FILE_EXTENSION,
                        BinaryTreeType.AVLTREE);
            }
        });
        binarySearchTreeMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                softwareController.addDataStructure(
                        IBinaryTreeController.BINARY_TREE_FILE_EXTENSION,
                        BinaryTreeType.BINARYSEARCHTREE);
            }
        });
        redBlackTreeMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                softwareController.addDataStructure(
                        IBinaryTreeController.BINARY_TREE_FILE_EXTENSION,
                        BinaryTreeType.REDBLACKTREE);
            }
        });
        newTreeMenuItems.add(randomTreeMenuItem);
        newTreeMenuItems.add(binarySearchTreeMenuItem);
        newTreeMenuItems.add(avlTreeMenuItem);
        newTreeMenuItems.add(redBlackTreeMenuItem);
        
        return newTreeMenuItems;
    }

    @Override
    protected List<JMenuItem> createInfoMenuItems() {
        List<JMenuItem> infoTreeMenuItems = new ArrayList<JMenuItem>();
        JMenuItem avlTreeMenuItem = new JMenuItem("AVL Tree");
        JMenuItem binarySearchTreeMenuItem = new JMenuItem("Binary Search Tree");
        JMenuItem redBlackTreeMenuItem = new JMenuItem("Red Black Tree");

        avlTreeMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO Display information
                JOptionPane.showMessageDialog((JFrame) softwareController.getView(),
                        "Operation not implemented yet!", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        binarySearchTreeMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO Display information
                JOptionPane.showMessageDialog((JFrame) softwareController.getView(),
                        "Operation not implemented yet!", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        redBlackTreeMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO Display information
                JOptionPane.showMessageDialog((JFrame) softwareController.getView(),
                        "Operation not implemented yet!", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        infoTreeMenuItems.add(binarySearchTreeMenuItem);
        infoTreeMenuItems.add(avlTreeMenuItem);
        infoTreeMenuItems.add(redBlackTreeMenuItem);

        return infoTreeMenuItems;
    }
}
