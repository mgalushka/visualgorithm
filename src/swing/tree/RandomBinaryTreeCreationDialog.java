/*
 * RandomBinaryTreeCreationDialog.java v1.00 12/07/08
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
import controller.ISoftwareController;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import model.tree.AbstractBinaryTree.BinaryTreeType;

/**
 * This class defines the random binary tree creation dialog. It is composed by
 * a combo box to select the type of tree, and a spinner to select the number of
 * nodes of the tree. This class is not designed for inheritance.
 * 
 * @author Julien Hannier
 * @version 1.00 12/07/08
 */
final class RandomBinaryTreeCreationDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private static final String AVL_TREE = "AVL Tree";

    private static final String BINARY_SEARCH_TREE = "Binary Search Tree";

    private static final String RED_BLACK_TREE = "Red Black Tree";

    private ISoftwareController softwareController;

    private JComboBox treeListComboBox;

    private JSpinner numberOfNodesSpinner;

    /**
     * Builds the random binary tree creation dialog. It is composed by a combo
     * box and a spinner.
     *
     * @param p the parent frame of the random tree creation dialog
     * @param c the software controller
     */
    RandomBinaryTreeCreationDialog(JFrame parent, ISoftwareController c) {
        super(parent, "Random Tree", true);

        String[] treeListStrings = {AVL_TREE, BINARY_SEARCH_TREE, RED_BLACK_TREE};
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(new Integer(15),
                new Integer(1), new Integer(30), new Integer(1));
        softwareController = c;
        treeListComboBox = new JComboBox(treeListStrings);
        numberOfNodesSpinner = new JSpinner(spinnerModel);

        setContentPane(createDialogPanel());
        setResizable(false);
        setSize(new Dimension(370, 150));
        int xParentFrameCenter = parent.getX() + parent.getWidth() / 2;
        int yParentFrameCenter = parent.getY() + parent.getHeight() / 2;
        setLocation(xParentFrameCenter - getWidth() / 2,
                yParentFrameCenter - getHeight() / 2);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private JPanel createDialogPanel() {
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints contentPaneConstraints = new GridBagConstraints();
        JLabel treeListLabel = new JLabel("Select the type of tree : ");
        JLabel numberOfNodesLabel = new JLabel("Select the number of nodes : ");

        contentPaneConstraints.insets = new Insets(5, 0, 0, 0);
        contentPaneConstraints.fill = GridBagConstraints.HORIZONTAL;
        contentPaneConstraints.gridx = 0;
        contentPaneConstraints.gridy = 0;
        contentPane.add(treeListLabel, contentPaneConstraints);
        contentPaneConstraints.insets = new Insets(5, 10, 0, 0);
        contentPaneConstraints.fill = GridBagConstraints.NONE;
        contentPaneConstraints.gridx = 1;
        contentPaneConstraints.gridy = 0;
        contentPane.add(treeListComboBox, contentPaneConstraints);
        contentPaneConstraints.insets = new Insets(12, 0, 0, 0);
        contentPaneConstraints.gridx = 0;
        contentPaneConstraints.gridy = 1;
        contentPane.add(numberOfNodesLabel, contentPaneConstraints);
        contentPaneConstraints.insets = new Insets(12, 10, 0, 0);
        contentPaneConstraints.anchor = GridBagConstraints.WEST;
        contentPaneConstraints.gridx = 1;
        contentPaneConstraints.gridy = 1;
        contentPaneConstraints.ipady = 3;
        contentPane.add(numberOfNodesSpinner, contentPaneConstraints);
        contentPaneConstraints.insets = new Insets(12, 0, 0, 0);
        contentPaneConstraints.anchor = GridBagConstraints.CENTER;
        contentPaneConstraints.gridx = 0;
        contentPaneConstraints.gridy = 2;
        contentPaneConstraints.ipady = 0;
        contentPaneConstraints.gridwidth = 2;
        contentPane.add(createButtonsPanel(), contentPaneConstraints);
        
        return contentPane;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton okButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                String treeType = (String) treeListComboBox.getSelectedItem();
                SpinnerNumberModel spinnerModel =
                        (SpinnerNumberModel) numberOfNodesSpinner.getModel();
                int nbNode = spinnerModel.getNumber().intValue();

                if (treeType.equals(AVL_TREE)) {
                    softwareController.addDataStructure(
                            BinaryTreeController.BINARY_TREE_FILE_EXTENSION,
                            BinaryTreeType.AVLTREE, nbNode);
                } else if (treeType.equals(BINARY_SEARCH_TREE)) {
                    softwareController.addDataStructure(
                            BinaryTreeController.BINARY_TREE_FILE_EXTENSION,
                            BinaryTreeType.BINARYSEARCHTREE, nbNode);
                } else if (treeType.equals(RED_BLACK_TREE)) {
                    softwareController.addDataStructure(
                            BinaryTreeController.BINARY_TREE_FILE_EXTENSION,
                            BinaryTreeType.REDBLACKTREE, nbNode);
                }

                setVisible(false);
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
                dispose();
            }
        });
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        
        return buttonsPanel;
    }
}
