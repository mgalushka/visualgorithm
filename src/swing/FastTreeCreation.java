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

package swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    
    private JPanel controls;
    
    /**
     * Builds the fast tree creation pane.
     * 
     * @param c the controller
     */
    public FastTreeCreation(TabController c) {
        controller = c;
        controls = createControls();  
        setLayout(new BorderLayout());
        add(controls, BorderLayout.SOUTH);
    }
    
    private JPanel createControls() {
        JPanel controls = new JPanel();
        JButton insert = new JButton("Insert");
        JButton delete = new JButton("Delete");
        JTextField insertvalue = new JTextField();
        JTextField deletevalue = new JTextField();
        
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int key = 0;
                controller.addNode(key);
            }
        });
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int key = 0;
                controller.deleteNode(key);
            }
        });
        controls.setLayout(new GridLayout(2, 2, 4, 4));
        insert.setToolTipText ("Button Only : Random Insert");
        delete.setToolTipText ("Button Only : Multiple Suppression With Cursor");
        controls.add(insertvalue);
        controls.add(insert);
        controls.add(deletevalue);
        controls.add(delete);
        return controls;
    }
}
