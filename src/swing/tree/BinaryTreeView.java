/*
 * BinaryTreeView.java v1.00 16/06/08
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

import controller.IBinaryTreeController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.IBinaryTreeView;
import model.tree.BinaryTreeModelEvent;

/**
 * This class defines the binary tree view. The binary tree view is composed by
 * two different panels that can be interchanged, the fast creation panel and
 * the pedagogical creation panel. There is also a title which contains the type
 * of the binary tree. This class is not designed for inheritance.
 * 
 * @author Julien Hannier
 * @version 1.00 16/06/08
 * @see IBinaryTreeView
 */
public final class BinaryTreeView extends JPanel implements IBinaryTreeView {

    private static final long serialVersionUID = 1L;

    private IBinaryTreeController binaryTreeController;

    private AbstractBinaryTreeCreationPanel binaryTreeCreationPanel;

    private boolean isFastBinaryTreeCreationPanel;

    /**
     * Builds the binary tree view. The binary tree view is composed by the fast
     * binary tree creation panel and the pedagogical binary tree creation
     * panel. The parameter {@code type} is used to set the title of the view.
     * 
     * @param type the type of the binary tree
     * @param c the binary tree controller
     */
    public BinaryTreeView(String type, IBinaryTreeController c) {
        JPanel viewTitlePane = new JPanel();
        JLabel viewTitle = new JLabel(type);
        binaryTreeController = c;
        binaryTreeCreationPanel = new FastBinaryTreeCreationPanel(binaryTreeController);
        isFastBinaryTreeCreationPanel = true;
        
        viewTitlePane.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 4));
        viewTitlePane.add(viewTitle);
        
        setLayout(new BorderLayout(4, 4));
        add(viewTitlePane, BorderLayout.NORTH);
        add((JPanel) binaryTreeCreationPanel, BorderLayout.CENTER);
        add(createButtonBetweenPanels(), BorderLayout.SOUTH);
    }

    private JButton createButtonBetweenPanels() {
        final JButton buttonBetweenPanels = new JButton("Pedagogical Creation Mode");

        buttonBetweenPanels.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                if (isFastBinaryTreeCreationPanel) {
                    AbstractBinaryTreeCreationPanel pedagogicalBinaryTreeCreationPanel =
                            new PedagogicalBinaryTreeCreationPanel(binaryTreeController);

                    buttonBetweenPanels.setText("Fast Creation Mode");
                    remove(binaryTreeCreationPanel);
                    binaryTreeCreationPanel = pedagogicalBinaryTreeCreationPanel;
                    add((JPanel) binaryTreeCreationPanel, BorderLayout.CENTER);
                    isFastBinaryTreeCreationPanel = false;
                } else {
                    AbstractBinaryTreeCreationPanel fastBinaryTreeCreationPanel =
                            new FastBinaryTreeCreationPanel(binaryTreeController);

                    buttonBetweenPanels.setText("Pedagogical Creation Mode");
                    remove(binaryTreeCreationPanel);
                    binaryTreeCreationPanel = fastBinaryTreeCreationPanel;
                    add((JPanel) binaryTreeCreationPanel, BorderLayout.CENTER);
                    isFastBinaryTreeCreationPanel = true;
                }
                revalidate();
                repaint();
            }
        });
        
        return buttonBetweenPanels;
    }

    @Override
    public void binaryTreeHasChanged(BinaryTreeModelEvent event) {
        binaryTreeCreationPanel.updateTreeVisualization(event.getHeapCorrespondingToBinaryTree());
    }
}
