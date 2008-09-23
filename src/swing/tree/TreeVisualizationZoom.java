/*
 * TreeVisualizationZoom.java v1.00 16/06/08
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
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Zoom panel for all tree visualization.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
class TreeVisualizationZoom extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int zoomMin = 0;

    private static final int zoomMax = 3;

    /**
     * Builds a panel including a zoom of the tree visualization thanks to
     * JScrollPane.
     * 
     * @param treeVisualization the tree visualization
     */
    TreeVisualizationZoom(final TreeVisualization treeVisualization) {
        super(new BorderLayout(4, 4));
        JScrollPane scrollPane = new JScrollPane(treeVisualization);

        scrollPane.addMouseWheelListener(new MouseWheelListener() {

            int zoomValue = zoomMin;

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK) {
                    boolean changed = false;
                    if (e.getWheelRotation() == -1) {
                        if (zoomValue > zoomMin) {
                            --zoomValue;
                            changed = true;
                        }
                    } else {
                        if (zoomValue < zoomMax) {
                            ++zoomValue;
                            changed = true;
                        }
                    }
                    if (changed) {
                        treeVisualization.changeSize(zoomValue);
                        treeVisualization.revalidate();
                        treeVisualization.repaint();
                    }
                }
            }
        });
        scrollPane
                .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane
                .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setWheelScrollingEnabled(false);
        scrollPane.setPreferredSize(new Dimension(treeVisualization.getWidth(),
                treeVisualization.getHeight()));
        add(scrollPane, BorderLayout.CENTER);
    }
}
