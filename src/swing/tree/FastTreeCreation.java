/*
 * FastTreeCreation.java v1.00 16/06/08
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
import java.text.ParseException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import model.tree.IBinaryNode;
import controller.BinaryTreeTabController;

/**
 * Definition of the fast tree creation pane.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
class FastTreeCreation extends JPanel {

    private static final long serialVersionUID = 1L;

    private BinaryTreeTabController controller;

    private TreeVisualization treeVisualization;

    private JPanel controls;
    
    /**
     * Builds the fast tree creation pane.
     * 
     * @param c the controller
     */
    FastTreeCreation(BinaryTreeTabController c) {
        treeVisualization = new TreeVisualization(c);
        controller = c;
        controls = createControls();

        setLayout(new BorderLayout(4, 4));
        add(new TreeVisualizationZoom(treeVisualization), BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);
    }

    private JPanel createControls() {
        JPanel controls = new JPanel();
        JButton insert = new JButton("Insert");
        JButton delete = new JButton("Delete");
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("##");
        } catch (ParseException e) {
            System.out.println("Formatter is bad : " + e.getMessage());
            System.exit(-1);
        }
        final JFormattedTextField insertValue = new JFormattedTextField(mask);
        final JFormattedTextField deleteValue = new JFormattedTextField(mask);
        insertValue.setHorizontalAlignment(SwingConstants.CENTER);
        deleteValue.setHorizontalAlignment(SwingConstants.CENTER);

        insert.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                String value = insertValue.getText();
                String empty = "  ";
                if (!empty.equals(value) && (value != null)) {
                    controller.addNode(Integer.parseInt(value));
                    insertValue.setValue(null);
                } else {
                    controller.addNode((int) Math.round(Math.random() * 100));
                }
            }
        });
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                String value = deleteValue.getText();
                String empty = "  ";
                if (!empty.equals(value) && (value != null)) {
                    controller.deleteNode(Integer.parseInt(value));
                    deleteValue.setValue(null);
                } else {
                    treeVisualization.launchDeleteMode();
                }
            }
        });
        controls.setLayout(new GridLayout(2, 2, 4, 4));
        insert.setToolTipText("Button Only : Random Insert");
        delete.setToolTipText("Button Only : "
                + "Multiple Suppression With Cursor");
        insertValue.setToolTipText("Type Integers From 00 To 99");
        deleteValue.setToolTipText("Type Integers From 00 To 99");
        controls.add(insertValue);
        controls.add(insert);
        controls.add(deleteValue);
        controls.add(delete);
        return controls;
    }

    /**
     * Updates the visualization of the tree.
     * 
     * @param data the data
     */
    <N extends IBinaryNode<N>> void updateTree(List<N> data) {
        treeVisualization.calculatePositions(data);
    }
}
