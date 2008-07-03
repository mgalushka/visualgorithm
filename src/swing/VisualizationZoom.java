/*
 * VisualizationZoom.java v1.00 16/06/08
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

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Zoom pane for all visualization panels.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class VisualizationZoom extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int zoomMin = 0;
    
    private static final int zoomMax = 20;

    /**
     * Builds a pane including a zoom of the component
     * thanks to JScrollPane.
     * 
     * @param component the component
     */
    public VisualizationZoom(final JComponent component) {
        JScrollPane scrollPane = new JScrollPane(component);
        //TODO zoom
        scrollPane.addMouseWheelListener(new MouseWheelListener() {
            int height = component.getHeight();
            int width = component.getWidth();
            int pheight = component.getPreferredSize().height;
            int pwidth = component.getPreferredSize().width;
            int scrollzoom = zoomMin;

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if ((e.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) == MouseEvent.CTRL_DOWN_MASK) {
                    if (e.getWheelRotation() == -1) {
                        scrollzoom = (scrollzoom >= zoomMax) ? zoomMax : ++scrollzoom;
                    } else {
                        scrollzoom = (scrollzoom == zoomMin) ? zoomMin : --scrollzoom;
                    }
                    if (scrollzoom == zoomMin) {
                        component.setPreferredSize(new Dimension(pwidth, pheight));
                    } else {
                        component.setPreferredSize(new Dimension(width*scrollzoom, height*scrollzoom));
                    }
                    System.out.println("h "+height);
                    System.out.println("w "+width);
                    System.out.println("ph "+pheight);
                    System.out.println("pw "+pwidth);
                    System.out.println("s "+scrollzoom);
                    System.out.println();
                    component.invalidate();
                }
            }
        });
        add(scrollPane);
    }
}
