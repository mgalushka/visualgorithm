/*
 * BinaryTreeTabPage.java v1.00 16/06/08
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
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.BinaryTreeTabController;
import view.IBinaryTreeView;
import model.tree.BinaryTreeEvent;
import model.tree.IBinaryNode;

/**
 * Definition of the tab view.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see IBinaryTreeView
 */
public class BinaryTreeTabPage extends JPanel implements IBinaryTreeView {

    private static final long serialVersionUID = 1L;

    private BinaryTreeTabController controller;

    private FastTreeCreation fastTreeCreation = null;

    private PedagogicalTreeCreation pedagogicalTreeCreation = null;

    private boolean isFastTreeCreation = true;

    /**
     * Builds the tab view. The model is a data structure.
     * 
     * @param c the controller
     * @param type the type of the data structure
     */
    public BinaryTreeTabPage(String type, BinaryTreeTabController c) {
        fastTreeCreation = new FastTreeCreation(c);
        final JButton pedagogicView = new JButton("Pedagogical"
                + "Creation Mode");
        JPanel titlePane = new JPanel();
        JLabel title = new JLabel(type);

        controller = c;
        titlePane.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 4));
        titlePane.add(title);
        pedagogicView.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                if (isFastTreeCreation) {
                    if (pedagogicalTreeCreation == null) {
                        pedagogicalTreeCreation = new PedagogicalTreeCreation(
                                controller);
                    }
                    pedagogicView.setText("Fast Creation Mode");
                    remove(fastTreeCreation);
                    add(pedagogicalTreeCreation, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                    isFastTreeCreation = false;
                } else {
                    pedagogicView.setText("Pedagogical Creation Mode");
                    remove(pedagogicalTreeCreation);
                    add(fastTreeCreation, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                    isFastTreeCreation = true;
                }
            }
        });
        setLayout(new BorderLayout(4, 4));
        add(titlePane, BorderLayout.NORTH);
        add(fastTreeCreation, BorderLayout.CENTER);
        add(pedagogicView, BorderLayout.SOUTH);
    }

    @Override
    public <N extends IBinaryNode<N>> void binaryTreeChanged(
            BinaryTreeEvent<N> event) {
        fastTreeCreation.updateTree(event.getData());
    }
}
