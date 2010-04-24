/*
 * FastBinaryTreeCreationPanel.java v0.10 16/06/08
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
import javax.swing.JPanel;
import controller.IBinaryTreeController;

/**
 * This class defines the fast binary tree creation panel. It is composed by a
 * binary tree visualization, an insert text field and an insert button, a
 * delete text field and a delete button. All of these are defined in the
 * abstract class <tt>AbstractBinaryTreeCreationPanel</tt>. This class is not
 * designed for inheritance.
 * 
 * @author Julien Hannier
 * @version 0.10 16/06/08
 * @see AbstractBinaryTreeCreationPanel
 */
final class FastBinaryTreeCreationPanel extends AbstractBinaryTreeCreationPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Builds the fast binary tree creation panel and places the components of
     * the abstract class on the panel.
     * 
     * @param c the binary tree controller
     */
    FastBinaryTreeCreationPanel(IBinaryTreeController c) {
        super(c);
        binaryTreeVisualization = new FastBinaryTreeVisualization(binaryTreeController);

        setLayout(new BorderLayout(4, 4));
        add(binaryTreeVisualization.buildVisualizationPanelWithZoom(), BorderLayout.CENTER);
        add(createControls(), BorderLayout.SOUTH);
    }

    private JPanel createControls() {
        JPanel controls = new JPanel();

        controls.setLayout(new GridLayout(2, 2, 4, 4));
        insertNodeButton.setToolTipText("Button Only : Random Insert");
        deleteNodeButton.setToolTipText("Button Only : Multiple Suppression With Cursor");
        controls.add(insertNodeText);
        controls.add(insertNodeButton);
        controls.add(deleteNodeText);
        controls.add(deleteNodeButton);

        return controls;
    }

    @Override
    protected void insertNodeActionWithText(String nodeValue) {
        binaryTreeController.addNodeToBinaryTreeModel(Integer.parseInt(nodeValue));
    }

    @Override
    protected void deleteNodeActionWithText(String nodeValue) {
        binaryTreeController.deleteNodeFromBinaryTreeModel(Integer.parseInt(nodeValue));
    }

    @Override
    protected void insertNodeActionWithButtonOnly() {
        binaryTreeController.addNodeToBinaryTreeModel((int) Math.round(Math.random() * 99));
    }

    @Override
    protected void deleteNodeActionWithButtonOnly() {
        ((FastBinaryTreeVisualization) binaryTreeVisualization).activateDeleteMode();
    }
}
