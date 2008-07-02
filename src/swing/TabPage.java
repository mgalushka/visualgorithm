/*
 * TabPage.java v1.00 16/06/08
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
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.TabController;
import swing.tree.FastTreeCreation;
import view.IDataStructureView;
import model.DataStructure;
import model.DataStructureEvent;
import model.DataStructureType;
import model.DataStructureEvent.DataStructureEventType;

/**
 * Definition of the tab view.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see IDataStructureView
 */
public class TabPage extends JPanel implements IDataStructureView {

    private static final long serialVersionUID = 1L;
    
    private TabController controller = null;
    
    private FastTreeCreation fastTreeCreation;
     
    /**
     * Builds the tab view. The model is a data structure.
     * 
     * @param dataStructure the model
     * @param c the controller
     * @param type the type of the data structure
     */
    public TabPage(DataStructure dataStructure, DataStructureType type,
            TabController c) {
        fastTreeCreation = new FastTreeCreation(c);
        JButton pedagogicView = new JButton("Pedagogical" +
                "Creation Mode");
        JPanel titlePane = new JPanel();
        JLabel title = new JLabel(type.toString());
        
        controller = c;
        titlePane.setLayout(new FlowLayout(
            FlowLayout.CENTER, 4, 4));
        titlePane.add(title);
        setLayout(new BorderLayout(4, 4));
        add(titlePane, BorderLayout.NORTH);
        addDataStructurePane(dataStructure);
        add(pedagogicView, BorderLayout.SOUTH);
    }

    private void addDataStructurePane(DataStructure dataStructure) {
        add(fastTreeCreation, BorderLayout.CENTER);
    }
    
    @Override
    public void dataStructureChanged(DataStructureEvent event) {
        if (event.getType() == DataStructureEventType.TREE) {
            fastTreeCreation.updateTree(event.getData());
        }
    }
}