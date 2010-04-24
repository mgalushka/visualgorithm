/*
 * TabCloseButton.java v0.10 16/06/08
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

/**
 * This class defines the tab close button. The tab close button is composed by
 * a label containing the name of the tab, and a button to close the tab. This
 * class is not designed for inheritance.
 * 
 * @author Julien Hannier
 * @version 0.10 16/06/08
 */
final class TabCloseButton extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final String TAB_CLOSE_BUTTON_TOOLTIP = "Close Tab";

    private static final Color CROSS_STROKE_COLOR = Color.RED;

    private JTabbedPane softwareTabbedPane;

    private SoftwareViewIOOperation softwareViewIOOperation;

    /**
     * Builds the tab close button. The tab close button is composed by a label
     * and a button.
     * 
     * @param tp the software tabbed pane
     * @param io the software view IO operation
     */
    TabCloseButton(JTabbedPane tp, SoftwareViewIOOperation io) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        softwareTabbedPane = tp;
        softwareViewIOOperation = io;
        JButton tabCloseButton = createTabCloseButton();
        JLabel tabName = createTabName();
        
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent event) {
                int tabIndex = softwareTabbedPane.indexOfTabComponent(TabCloseButton.this);

                if (event.getButton() == MouseEvent.BUTTON1) {
                    softwareTabbedPane.setSelectedIndex(tabIndex);
                } else if (event.getButton() == MouseEvent.BUTTON2) {
                    softwareViewIOOperation.closeTabOperation(tabIndex);
                }
            }
        });
        add(tabName);
        add(tabCloseButton);
    }

    private JLabel createTabName() {
        JLabel tabName = new JLabel() {

            private static final long serialVersionUID = 1L;

            @Override
            public String getText() {
                int tabIndex = softwareTabbedPane.indexOfTabComponent(TabCloseButton.this);

                if (tabIndex != -1) {
                    return softwareTabbedPane.getTitleAt(tabIndex);
                }

                return null;
            }
        };
        tabName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

        return tabName;
    }

    private JButton createTabCloseButton() {
        JButton tabCloseButton = new JButton() {

            private static final long serialVersionUID = 1L;

            @Override
            public void updateUI() {
            }

            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                
                Graphics2D graphics2D = (Graphics2D) graphics.create();
                
                graphics2D.setStroke(new BasicStroke(2));
                graphics2D.setColor(CROSS_STROKE_COLOR);
                graphics2D.drawLine(6, 6, (getWidth() - 1) - 6, (getHeight() - 1) - 6);
                graphics2D.drawLine((getWidth() - 1) - 6, 6, 6, (getHeight() - 1) - 6);
                graphics2D.dispose();
            }
        };
        
        tabCloseButton.setPreferredSize(new Dimension(17, 17));
        tabCloseButton.setToolTipText(TAB_CLOSE_BUTTON_TOOLTIP);
        tabCloseButton.setUI(new BasicButtonUI());
        tabCloseButton.setContentAreaFilled(false);
        tabCloseButton.setFocusable(false);
        tabCloseButton.setBorderPainted(false);
        tabCloseButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent event) {
                Component component = event.getComponent();
                
                if (component instanceof AbstractButton) {
                    AbstractButton button = (AbstractButton) component;
                    button.setBorderPainted(true);
                }
            }

            @Override
            public void mouseExited(MouseEvent event) {
                Component component = event.getComponent();
                
                if (component instanceof AbstractButton) {
                    AbstractButton button = (AbstractButton) component;
                    button.setBorderPainted(false);
                }
            }
        });
        tabCloseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                softwareViewIOOperation.closeTabOperation(softwareTabbedPane
                        .indexOfTabComponent(TabCloseButton.this));
            }
        });
        
        return tabCloseButton;
    }
}
