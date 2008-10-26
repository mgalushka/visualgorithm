/*
 * RandomTreeCreationDialog.java v1.00 12/07/08
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
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import swing.SoftwareView;
import controller.SoftwareController;
import model.tree.BinaryTreeTabModel;
import model.tree.AbstractBinaryTree.BinaryTreeType;

/**
 * Dialog to create random trees.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 12/07/08
 */
public class RandomTreeCreationDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private SoftwareController softwareController;

    private JTabbedPane tabbedPane;

    private JComboBox treeList;

    private JSpinner numberOfNodes;

    /**
     * Builds a random tree creation dialog.
     * 
     * @param parent the parent component
     * @param tp the tabbed pane
     * @param c the software controller
     */
    public RandomTreeCreationDialog(SoftwareView parent, JTabbedPane tp,
            SoftwareController c) {
        super(parent, "Random Tree", true);
        softwareController = c;
        tabbedPane = tp;

        setContentPane(createPanel());
        setSize(new Dimension(370, 150));
        int xCenter = parent.getX() + parent.getWidth() / 2;
        int yCenter = parent.getY() + parent.getHeight() / 2;
        setLocation(xCenter - getWidth() / 2, yCenter - getHeight() / 2);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private JPanel createPanel() {
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel list = new JLabel("Select the type of tree : ");
        JLabel nb = new JLabel("Select the number of nodes : ");
        String[] treeStrings = { "AVL Tree", "Binary Search Tree",
                "Red Black Tree" };
        treeList = new JComboBox(treeStrings);
        SpinnerNumberModel nbModel = new SpinnerNumberModel(new Integer(15),
                new Integer(1), new Integer(30), new Integer(1));
        numberOfNodes = new JSpinner(nbModel);

        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        contentPane.add(list, constraints);
        constraints.insets = new Insets(5, 10, 0, 0);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 1;
        constraints.gridy = 0;
        contentPane.add(treeList, constraints);
        constraints.insets = new Insets(12, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 1;
        contentPane.add(nb, constraints);
        constraints.insets = new Insets(12, 10, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.ipady = 3;
        contentPane.add(numberOfNodes, constraints);
        constraints.insets = new Insets(12, 0, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.ipady = 0;
        constraints.gridwidth = 2;
        contentPane.add(createButtons(), constraints);
        return contentPane;
    }

    private JPanel createButtons() {
        JPanel buttons = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");

        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                int index = tabbedPane.getTabCount();
                String stringType = (String) treeList.getSelectedItem();
                BinaryTreeType type;
                if (stringType.equals("AVL Tree")) {
                    type = BinaryTreeType.AVLTREE;
                } else if (stringType.equals("Binary Search Tree")) {
                    type = BinaryTreeType.BINARYSEARCHTREE;
                } else {
                    type = BinaryTreeType.REDBLACKTREE;
                }
                int nbNode = (Integer) numberOfNodes.getModel().getValue();

                softwareController.addTabWithRandom(
                    BinaryTreeTabModel.DataStructureName, type, nbNode, index,
                    tabbedPane.getWidth(), tabbedPane.getHeight());
                setVisible(false);
                dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
                dispose();
            }
        });
        buttons.add(ok);
        buttons.add(cancel);
        return buttons;
    }
}
