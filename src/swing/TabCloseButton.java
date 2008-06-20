/*
 * TabCloseButton.java v1.00 16/06/08
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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

import controller.PrincipalController;

/**
 * Definition of the tab close button.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 */
public class TabCloseButton extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JTabbedPane tabbedPane = null;
    
    private PrincipalController controller = null;

    /**
     * Builds the tab close button.
     * 
     * @param p the tabbed pane
     * @param c the principal controller
     */
    public TabCloseButton(JTabbedPane p, PrincipalController c) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabbedPane = p;
        controller = c;
        JButton closeButton = createCloseButton();
        JLabel tabName = new JLabel() {
            private static final long serialVersionUID = 1L;

            public String getText() {
                int i = tabbedPane.indexOfTabComponent(TabCloseButton.this);
                if (i != -1) {
                    return tabbedPane.getTitleAt(i);
                }
                return null;
            }
        };
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    int i = tabbedPane.indexOfTabComponent(TabCloseButton.this);
                    tabbedPane.setSelectedIndex(i);
                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    int i = tabbedPane.indexOfTabComponent(TabCloseButton.this);
                    if (i != -1) {
                        controller.closeTab(i);
                    }
                }
            }
        });
        tabName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        add(tabName);
        add(closeButton);
    }

    private JButton createCloseButton() {
        JButton closeButton = new JButton() {
            private static final long serialVersionUID = 1L;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g.create();
                g2.setStroke(new BasicStroke(2));
                g2.setColor(Color.RED);
                int delta = 6;
                g2.drawLine(delta, delta, getWidth()-delta-1,
                    getHeight()-delta-1);
                g2.drawLine(getWidth()-delta-1, delta, delta,
                    getHeight()-delta-1);
                g2.dispose();
            }
        };
        int size = 17;
        closeButton.setPreferredSize(new Dimension(size, size));
        closeButton.setToolTipText("Close Tab");
        closeButton.setUI(new BasicButtonUI());
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusable(false);
        closeButton.setBorderPainted(false);
        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                Component component = e.getComponent();
                if (component instanceof AbstractButton) {
                    AbstractButton button = (AbstractButton)component;
                    button.setBorderPainted(true);
                }
            }

            public void mouseExited(MouseEvent e) {
                Component component = e.getComponent();
                if (component instanceof AbstractButton) {
                    AbstractButton button = (AbstractButton)component;
                    button.setBorderPainted(false);
                }
            }
        });
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = tabbedPane.indexOfTabComponent(TabCloseButton.this);
                if (i != -1) {
                    controller.closeTab(i);
                }
            }
        });
        return closeButton;
    }
}