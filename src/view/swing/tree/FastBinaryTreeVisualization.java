/*
 * FastBinaryTreeVisualization.java v0.10 01/08/08
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

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import controller.IBinaryTreeController;
import java.awt.Graphics;

/**
 * This class defines the fast binary tree visualization. The fast binary tree
 * visualization is composed by graphic nodes that represent a binary tree. This
 * visualization has the same functionalities than its parent class with also
 * a delete mode with the cursor. This class is not designed for inheritance.
 * 
 * @author Julien Hannier
 * @version 0.10 01/08/08
 * @see AbstractBinaryTreeVisualization
 */
final class FastBinaryTreeVisualization extends AbstractBinaryTreeVisualization {

    private static final long serialVersionUID = 1L;

    private boolean isDeleteModeActive;

    /**
     * Builds the fast binary tree visualization. It is composed by graphic
     * nodes that represent a binary tree.
     * 
     * @param c the binary tree controller
     */
    FastBinaryTreeVisualization(IBinaryTreeController c) {
        super(c);
        
        isDeleteModeActive = false;

        addDeleteModeListener();
    }

    private void addDeleteModeListener() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent event) {
                if ((event.getButton() == MouseEvent.BUTTON1) && isDeleteModeActive) {
                    int indexOfSelectedNode = findIndexOfSelectedNode(event.getX(),
                            event.getY());
                    
                    if (indexOfSelectedNode >= 0) {
                        int selectedNodeKey = findNodeKey(indexOfSelectedNode);
                        binaryTreeController.deleteNodeFromBinaryTreeModel(selectedNodeKey);
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent event) {
                setCursor(Cursor.getDefaultCursor());
                isDeleteModeActive = false;
            }
        });
    }

    @Override
    protected void paintExtraComponent(Graphics graphics) {
    }

    /**
     * Activates the delete mode by changing the mouse cursor. Then, all the
     * nodes that are clicked are deleted. The mode is automatically deactivated
     * when the cursor leaves the visualization.
     */
    void activateDeleteMode() {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        isDeleteModeActive = true;
    }
}
