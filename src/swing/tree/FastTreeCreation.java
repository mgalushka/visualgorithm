/*
 * FastTreeCreation.java v1.00 16/06/08
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

package swing.tree;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import model.tree.IBinaryNode;

import swing.VisualizationZoom;
import controller.TabController;

/**
 * Definition of the fast tree creation pane.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class FastTreeCreation extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private TabController controller = null;
    
    private TreeVisualization treeVisualization;
    
    private JPanel controls;
    
    /**
     * Builds the fast tree creation pane.
     * 
     * @param c the controller
     */
    public FastTreeCreation(TabController c) {
        treeVisualization = new TreeVisualization();
        controller = c;
        controls = createControls();  
        
        setLayout(new BorderLayout(4,4));
        add(new VisualizationZoom(treeVisualization), BorderLayout.CENTER);
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
        final JFormattedTextField insertvalue = new JFormattedTextField(mask);
        final JFormattedTextField deletevalue = new JFormattedTextField(mask);
        insertvalue.setHorizontalAlignment(JTextField.CENTER);
        deletevalue.setHorizontalAlignment(JTextField.CENTER);
        
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String value = insertvalue.getText();
                String empty = "  ";
                if (!empty.equals(value) && (value != null)) {
                    controller.addNode(Integer.parseInt(value));
                    insertvalue.setValue(null);
                } else {
                    //TODO random insertion
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String value = deletevalue.getText();
                String empty = "  ";
                if (!empty.equals(value) && (value != null)) {
                    controller.deleteNode(Integer.parseInt(value));
                    deletevalue.setValue(null);
                } else {
                    //TODO click deletion
                }
            }
        });
        controls.setLayout(new GridLayout(2, 2, 4, 4));
        insert.setToolTipText ("Button Only : Random Insert");
        delete.setToolTipText ("Button Only : " +
        		"Multiple Suppression With Cursor");
        controls.add(insertvalue);
        controls.add(insert);
        controls.add(deletevalue);
        controls.add(delete);
        return controls;
    }
    
    /**
     * Updates the visualization of the tree.
     *  
     * @param data the data
     */
    public void updateTree(Object data) {
        treeVisualization.calculatePositions((IBinaryNode<?>[])data);
    }
}
