/*
 * PedagogicalTreeCreation.java v1.00 07/07/08
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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.tree.IBinaryNode;
import controller.BinaryTreeTabController;

/**
 * Definition of the pedagogical tree creation pane.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 07/07/08
 */
class PedagogicalTreeCreation extends JPanel {

    private static final long serialVersionUID = 1L;

    private BinaryTreeTabController controller;

    private PedagogicalTreeVisualization pedagogicalTreeVisualization;

    private JTextArea informations;

    /**
     * Builds the pedagogical tree creation pane.
     * 
     * @param c the controller
     */
    PedagogicalTreeCreation(BinaryTreeTabController c) {
        pedagogicalTreeVisualization = new PedagogicalTreeVisualization(c, 0, 0);
        informations = new JTextArea("  Informations", 2, 1);
        JPanel components = new JPanel();
        controller = c;

        informations.setEditable(false);
        informations.setLineWrap(true);
        informations.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        components.setLayout(new BoxLayout(components, BoxLayout.PAGE_AXIS));
        components.add(informations);
        components.add(Box.createRigidArea(new Dimension(0, 4)));
        components.add(createControls());
        setLayout(new BorderLayout(4, 4));
        add(new TreeVisualizationZoom(pedagogicalTreeVisualization),
            BorderLayout.CENTER);
        add(components, BorderLayout.SOUTH);
    }

    private JPanel createControls() {
        JPanel controls = new JPanel();
        JButton help = new JButton("Help");
        JButton solution = new JButton("Solution");
        JButton insert = new JButton("Insert");
        JButton delete = new JButton("Delete");
        final JTextField insertValue = new JTextField();
        final JTextField deleteValue = new JTextField();
        insertValue.setHorizontalAlignment(SwingConstants.CENTER);
        deleteValue.setHorizontalAlignment(SwingConstants.CENTER);

        help.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {

            }
        });
        solution.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {

            }
        });
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
                                if (pedagogicalTreeVisualization.isEmpty()) {
                                    controller.addNode(Integer.parseInt(value));
                                } else {
                                    pedagogicalTreeVisualization.createNode(Integer
                                        .parseInt(value));
                                }
                            }
                        }
                    } else if (value.length() == 1) {
                        if ((value.charAt(0) >= '0')
                                && (value.charAt(0) <= '9')) {
                            if (pedagogicalTreeVisualization.isEmpty()) {
                                controller.addNode(Integer.parseInt(value));
                            } else {
                                pedagogicalTreeVisualization.createNode(Integer
                                    .parseInt(value));
                            }
                        }
                    }
                    insertValue.setText(null);
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
                                controller.pedagogicalDeleteNode(Integer
                                        .parseInt(value));
                            }
                        }
                    } else if (value.length() == 1) {
                        if ((value.charAt(0) >= '0')
                                && (value.charAt(0) <= '9')) {
                            controller.pedagogicalDeleteNode(Integer
                                    .parseInt(value));
                        }
                    }
                    deleteValue.setText(null);
                }
            }
        });
        controls.setLayout(new GridLayout(3, 2, 4, 4));
        help.setToolTipText("An Help For The Action");
        solution.setToolTipText("The Solution Of The Action");
        controls.add(help);
        controls.add(solution);
        controls.add(insertValue);
        controls.add(insert);
        controls.add(deleteValue);
        controls.add(delete);
        return controls;
    }

    /**
     * Returns the pedagogical tree visualization.
     * 
     * @return the pedagogical tree visualization
     */
    TreeVisualization getPedagogicalTreeVisualization() {
        return pedagogicalTreeVisualization;
    }

    /**
     * Updates the visualization of the tree.
     * 
     * @param data the data
     */
    <N extends IBinaryNode<N>> void updateTree(List<N> data) {
        pedagogicalTreeVisualization.calculatePositions(data);
    }
}
