/*
 * FastTreeCreationPanel.java v1.00 16/06/08
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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.tree.IBinaryNode;
import controller.BinaryTreeTabController;

/**
 * Definition of the fast tree creation panel.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
class FastTreeCreationPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private BinaryTreeTabController binaryTreeTabController;

    private FastTreeVisualization fastTreeVisualization;

    /**
     * Builds the fast tree creation panel.
     * 
     * @param c the binary tree tab controller
     * @param width the width of the tree visualization
     * @param height the height of the tree visualization
     */
    FastTreeCreationPanel(BinaryTreeTabController c, int width, int height) {
        fastTreeVisualization = new FastTreeVisualization(c, width - 23,
                height - 148);
        binaryTreeTabController = c;

        setLayout(new BorderLayout(4, 4));
        add(new TreeVisualizationZoom(fastTreeVisualization),
            BorderLayout.CENTER);
        add(createControls(), BorderLayout.SOUTH);
    }

    private JPanel createControls() {
        JPanel controls = new JPanel();
        JButton insert = new JButton("Insert");
        JButton delete = new JButton("Delete");
        final JTextField insertValue = new JTextField();
        final JTextField deleteValue = new JTextField();
        insertValue.setHorizontalAlignment(SwingConstants.CENTER);
        deleteValue.setHorizontalAlignment(SwingConstants.CENTER);

        insert.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                String value = insertValue.getText();
                String empty = "";
                if (!empty.equals(value) && (value != null)) {
                    if (value.length() == 2) {
                        if ((value.charAt(0) >= '0')
                                && (value.charAt(0) <= '9')) {
                            if ((value.charAt(1) >= '0')
                                    && (value.charAt(1) <= '9')) {
                                binaryTreeTabController.addNode(Integer
                                        .parseInt(value));
                            }
                        }
                    } else if (value.length() == 1) {
                        if ((value.charAt(0) >= '0')
                                && (value.charAt(0) <= '9')) {
                            binaryTreeTabController.addNode(Integer
                                    .parseInt(value));
                        }
                    }
                    insertValue.setText(null);
                } else {
                    binaryTreeTabController.addNode((int) Math.round(Math
                            .random() * 99));
                }
            }
        });
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                String value = deleteValue.getText();
                String empty = "";
                if (!empty.equals(value) && (value != null)) {
                    if (value.length() == 2) {
                        if ((value.charAt(0) >= '0')
                                && (value.charAt(0) <= '9')) {
                            if ((value.charAt(1) >= '0')
                                    && (value.charAt(1) <= '9')) {
                                binaryTreeTabController.deleteNode(Integer
                                        .parseInt(value));
                            }
                        }
                    } else if (value.length() == 1) {
                        if ((value.charAt(0) >= '0')
                                && (value.charAt(0) <= '9')) {
                            binaryTreeTabController.deleteNode(Integer
                                    .parseInt(value));
                        }
                    }
                    deleteValue.setText(null);
                } else {
                    fastTreeVisualization.launchDeleteMode();
                }
            }
        });
        controls.setLayout(new GridLayout(2, 2, 4, 4));
        insert.setToolTipText("Button Only : Random Insert");
        delete.setToolTipText("Button Only : "
                + "Multiple Suppression With Cursor");
        controls.add(insertValue);
        controls.add(insert);
        controls.add(deleteValue);
        controls.add(delete);
        return controls;
    }

    /**
     * Returns the fast tree visualization.
     * 
     * @return the fast tree visualization
     */
    AbstractTreeVisualization getFastTreeVisualization() {
        return fastTreeVisualization;
    }

    /**
     * Updates the visualization of the tree.
     * 
     * @param data the data
     */
    <N extends IBinaryNode> void updateTree(List<N> data) {
        fastTreeVisualization.calculatePositions(data);
    }
}
