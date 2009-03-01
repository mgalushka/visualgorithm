/*
 * BinaryTreeTabView.java v1.00 16/06/08
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
import controller.BinaryTreeController;
import view.IBinaryTreeView;
import model.tree.BinaryTreeModelEvent;
import model.tree.IBinaryNode;

/**
 * Definition of the binary tree tab view.
 * 
 * @author Julien Hannier
 * @author Pierre Pironin
 * @author Damien Rigoni
 * @version 1.00 16/06/08
 * @see IBinaryTreeView
 */
public class BinaryTreeTabView extends JPanel implements IBinaryTreeView {

    private static final long serialVersionUID = 1L;

    private BinaryTreeController binaryTreeTabController;

    private FastTreeCreationPanel fastTreeCreationPanel = null;

    private PedagogicalTreeCreationPanel pedagogicalTreeCreationPanel = null;

    private boolean isFastTreeCreation = true;

    /**
     * Builds the binary tree tab view.
     * 
     * @param c the binary tree tab controller
     * @param type the type of the binary tree
     * @param width the width of the tree visualization
     * @param height the height of the tree visualization
     */
    public BinaryTreeTabView(String type, BinaryTreeController c, int width,
            int height) {
        fastTreeCreationPanel = new FastTreeCreationPanel(c, width, height);
        JPanel titlePane = new JPanel();
        JLabel title = new JLabel(type);

        binaryTreeTabController = c;
        titlePane.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 4));
        titlePane.add(title);

        setLayout(new BorderLayout(4, 4));
        add(titlePane, BorderLayout.NORTH);
        add(fastTreeCreationPanel, BorderLayout.CENTER);
        add(createButtonBetweenViews(), BorderLayout.SOUTH);
    }

    private JButton createButtonBetweenViews() {
        final JButton button = new JButton("Pedagogical Creation Mode");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                if (isFastTreeCreation) {
                    if (pedagogicalTreeCreationPanel == null) {
                        pedagogicalTreeCreationPanel = new PedagogicalTreeCreationPanel(
                                binaryTreeTabController);
                    }
                    pedagogicalTreeCreationPanel
                            .getPedagogicalTreeVisualization()
                            .copyGraphicNodes(
                                fastTreeCreationPanel
                                        .getFastTreeVisualization());
                    button.setText("Fast Creation Mode");
                    remove(fastTreeCreationPanel);
                    add(pedagogicalTreeCreationPanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                    isFastTreeCreation = false;
                } else {
                    fastTreeCreationPanel.getFastTreeVisualization()
                            .copyGraphicNodes(
                                pedagogicalTreeCreationPanel
                                        .getPedagogicalTreeVisualization());
                    button.setText("Pedagogical Creation Mode");
                    remove(pedagogicalTreeCreationPanel);
                    add(fastTreeCreationPanel, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                    isFastTreeCreation = true;
                }
            }
        });
        return button;
    }

    @Override
    public void binaryTreeHasChanged(BinaryTreeModelEvent event) {
        if (isFastTreeCreation) {
            fastTreeCreationPanel.updateTree(event.getHeapCorrespondingToBinaryTree());
        } else {
            pedagogicalTreeCreationPanel.updateTree(event.getHeapCorrespondingToBinaryTree());
        }
    }
}
