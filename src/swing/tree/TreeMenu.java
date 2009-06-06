/*
 * TreeMenu.java v1.00 26/10/08
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

import controller.BinaryTreeController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import controller.SoftwareController;
import model.tree.AbstractBinaryTree.BinaryTreeType;
import swing.SoftwareView;

/**
 * Declares the tree menu of the software.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 26/10/08
 */
public class TreeMenu extends JMenu {

    private static final long serialVersionUID = 1L;

    private SoftwareController softwareController;

    private SoftwareView softwareView;

    private JTabbedPane tabbedPane;

    /**
     * Builds the tree menu of the software.
     * 
     * @param v the software view
     * @param tp the tabbed pane
     * @param c the software controller
     */
    public TreeMenu(SoftwareView v, JTabbedPane tp, SoftwareController c) {
        super("Tree");
        softwareController = c;
        softwareView = v;
        tabbedPane = tp;

        setMnemonic('T');
        add(createNewTreeMenu());
    }

    private JMenu createNewTreeMenu() {
        JMenu newTree = new JMenu("New");
        JMenuItem randomTree = new JMenuItem("Random Tree");
        JMenuItem avlTree = new JMenuItem("AVL Tree");
        JMenuItem binarySearchTree = new JMenuItem("Binary Search Tree");
        JMenuItem redBlackTree = new JMenuItem("Red Black Tree");

        randomTree.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                RandomTreeCreationDialog dialog = new RandomTreeCreationDialog(
                        softwareView, tabbedPane, softwareController);
                dialog.setVisible(true);
            }
        });
        avlTree.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                int index = tabbedPane.getTabCount();
                softwareController.addDataStructure(BinaryTreeController.binaryTreeFileExtension,
                    BinaryTreeType.AVLTREE, tabbedPane.getWidth(),
                    tabbedPane.getHeight());
            }
        });
        binarySearchTree.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                int index = tabbedPane.getTabCount();
                softwareController.addDataStructure(BinaryTreeController.binaryTreeFileExtension,
                    BinaryTreeType.BINARYSEARCHTREE, tabbedPane
                            .getWidth(), tabbedPane.getHeight());
            }
        });
        redBlackTree.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                int index = tabbedPane.getTabCount();
                softwareController.addDataStructure(BinaryTreeController.binaryTreeFileExtension,
                    BinaryTreeType.REDBLACKTREE, tabbedPane.getWidth(),
                    tabbedPane.getHeight());
            }
        });
        newTree.add(randomTree);
        newTree.add(binarySearchTree);
        newTree.add(avlTree);
        newTree.add(redBlackTree);
        return newTree;
    }
}
