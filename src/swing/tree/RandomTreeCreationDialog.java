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
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import swing.GraphicUserInterface;
import controller.PrincipalController;
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

    private PrincipalController controller;

    private JTabbedPane tabbedPane;

    private JComboBox treeList;

    private JSpinner numberOfNodes;

    /**
     * Builds a random creation dialog.
     * 
     * @param parent the parent component
     * @param tp the tabbed pane
     * @param c the principal controller
     */
    public RandomTreeCreationDialog(GraphicUserInterface parent,
            JTabbedPane tp, PrincipalController c) {
        super(parent, "Random Tree", true);
        controller = c;
        tabbedPane = tp;
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        panel.add(createPanel());
        setContentPane(panel);
        setSize(new Dimension(400, 140));
        int xCenter = parent.getX() + parent.getWidth() / 2;
        int yCenter = parent.getY() + parent.getHeight() / 2;
        setLocation(xCenter - getWidth() / 2, yCenter - getHeight() / 2);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private JPanel createPanel() {
        JPanel contentPane = new JPanel(new GridLayout(3, 2, 8, 8));
        JLabel list = new JLabel("Select the type of tree : ");
        JLabel nb = new JLabel("Select the number of nodes : ");
        String[] treeStrings = { "AVL Tree", "Binary Search Tree",
                "Red Black Tree" };
        treeList = new JComboBox(treeStrings);
        SpinnerNumberModel nbModel = new SpinnerNumberModel(new Integer(15),
                new Integer(1), new Integer(30), new Integer(1));
        numberOfNodes = new JSpinner(nbModel);
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

                controller.addRandomBinaryTreeTab(type, nbNode, index);
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
        contentPane.add(list);
        contentPane.add(treeList);
        contentPane.add(nb);
        contentPane.add(numberOfNodes);
        contentPane.add(ok);
        contentPane.add(cancel);
        return contentPane;
    }
}
