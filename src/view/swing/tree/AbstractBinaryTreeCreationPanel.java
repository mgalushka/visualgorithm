/*
 * AbstractBinaryTreeCreationPanel.java v0.10 08/01/10
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

package view.swing.tree;

import controller.IBinaryTreeController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.tree.IBinaryNode;

/**
 * This class defines the binary tree creation panel. It is composed by a binary
 * tree visualization, an insert text field and an insert button, a delete text
 * field and a delete button. These components are instanciated in this abstract
 * class and just have to be placed on the panel in concrete classes. This class
 * is designed for inheritance in order to create specific binary tree creation
 * panels thanks to the design pattern template method.
 *
 * @author Julien Hannier
 * @version 0.10 08/01/10
 */
abstract class AbstractBinaryTreeCreationPanel extends JPanel {

    /**
     * The binary tree visualization.
     */
    protected AbstractBinaryTreeVisualization binaryTreeVisualization;

    /**
     * The insert node text field.
     */
    protected JTextField insertNodeText;

    /**
     * The delete node text field.
     */
    protected JTextField deleteNodeText;

    /**
     * The insert node button.
     */
    protected JButton insertNodeButton;

    /**
     * The delete node button.
     */
    protected JButton deleteNodeButton;

    /**
     * The binary tree controller.
     */
    protected IBinaryTreeController binaryTreeController;

    /**
     * Builds the binary tree creation panel. The binary tree creation panel is
     * composed by a binary tree visualization, an insert text field and an
     * insert button, a delete text field and a delete button.
     *
     * @param c the binary tree controller
     */
    protected AbstractBinaryTreeCreationPanel(IBinaryTreeController c) {
        super();

        binaryTreeController = c;
        insertNodeText = new JTextField();
        deleteNodeText = new JTextField();
        insertNodeButton = createInsertNodeButton();
        deleteNodeButton = createDeleteNodeButton();

        insertNodeText.setHorizontalAlignment(SwingConstants.CENTER);
        deleteNodeText.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private JButton createInsertNodeButton() {
        JButton insertButton = new JButton("Insert");

        insertButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                String emptyString = "";
                String nodeValue = insertNodeText.getText();

                if ((nodeValue != null) && !emptyString.equals(nodeValue)) {
                    if (nodeValue.length() == 2) {
                        if ((nodeValue.charAt(0) >= '0')
                                && (nodeValue.charAt(0) <= '9')) {
                            if ((nodeValue.charAt(1) >= '0')
                                    && (nodeValue.charAt(1) <= '9')) {
                                insertNodeActionWithText(nodeValue);
                            }
                        }
                    } else if (nodeValue.length() == 1) {
                        if ((nodeValue.charAt(0) >= '0')
                                && (nodeValue.charAt(0) <= '9')) {
                            insertNodeActionWithText(nodeValue);
                        }
                    }
                    insertNodeText.setText(null);
                } else {
                    insertNodeActionWithButtonOnly();
                }
            }
        });

        return insertButton;
    }

    private JButton createDeleteNodeButton() {
        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                String emptyString = "";
                String nodeValue = deleteNodeText.getText();

                if ((nodeValue != null) && !emptyString.equals(nodeValue)) {
                    if (nodeValue.length() == 2) {
                        if ((nodeValue.charAt(0) >= '0')
                                && (nodeValue.charAt(0) <= '9')) {
                            if ((nodeValue.charAt(1) >= '0')
                                    && (nodeValue.charAt(1) <= '9')) {
                                deleteNodeActionWithText(nodeValue);
                            }
                        }
                    } else if (nodeValue.length() == 1) {
                        if ((nodeValue.charAt(0) >= '0')
                                && (nodeValue.charAt(0) <= '9')) {
                            deleteNodeActionWithText(nodeValue);
                        }
                    }
                    deleteNodeText.setText(null);
                } else {
                    deleteNodeActionWithButtonOnly();
                }
            }
        });

        return deleteButton;
    }

    /**
     * Inserts a node to the binary tree with an action on the insert button.
     * This action occurs when the insert text field is not empty.
     *
     * @param nodeValue the value of the insert text field
     */
    protected abstract void insertNodeActionWithText(String nodeValue);

    /**
     * Deletes a node from the binary tree with an action on the delete button.
     * This action occurs when the delete text field is not empty.
     *
     * @param nodeValue the value of the delete text field
     */
    protected abstract void deleteNodeActionWithText(String nodeValue);

    /**
     * Inserts a node to the binary tree with an action on the insert button
     * only. This action occurs when the insert text field is empty.
     */
    protected abstract void insertNodeActionWithButtonOnly();

    /**
     * Deletes a node from the binary tree with an action on the delete button
     * only. This action occurs when the delete text field is empty.
     */
    protected abstract void deleteNodeActionWithButtonOnly();

    /**
     * Updates the binary tree visualization with the last version of the tree
     * as heap representation.
     *
     * @param heapRepresentation the last version of the tree as heap
     */
    <N extends IBinaryNode> void updateTreeVisualization(List<N> heapRepresentation) {
        binaryTreeVisualization.updateTreeVisualization(heapRepresentation);
    }
}
